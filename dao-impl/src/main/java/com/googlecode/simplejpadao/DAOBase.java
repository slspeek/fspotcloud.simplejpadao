/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author steven
 */
public abstract class DAOBase<T extends HasKey<K>, U extends T, K> implements AbstractDAO<T, K> {

    @Inject
    protected Provider<EntityManager> entityManagerProvider;
    @Inject
    private Logger log;

    DAOBase() {
        Logger.getAnonymousLogger().info("EntityType:--: " + getEntityType().getName());
    }

    @Override
    public T find(K key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        T entity = null;
        try {
            entity = em.find(getEntityType(), key);
            if (entity != null) {
                detach(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public List<T> findAll(int max) {
        return findAllWhere(max, null);
    }

    public List<T> findAllWhere(int max, String contraint) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        try {
            String queryString = "select c from "
                    + getEntityType().getName() + " AS c";
            if (contraint != null) {
                queryString += " WHERE " + contraint;
            }
            Query query = em.createQuery(queryString);
            query.setMaxResults(max);
            @SuppressWarnings("unchecked")
            List<T> rs = (List<T>) query.getResultList();
            List<T> result = new ArrayList<T>();
            result.addAll(rs);
            for (T entity: result) {
                detach(entity);
            }
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }
    
    protected void detach(T entity) {
        
    }
    
    @Override
    public List<K> findAllKeys(int max) {
        EntityManager em = entityManagerProvider.get();
        //em.getTransaction().begin();
        try {
            Query query = em.createQuery("select c.id from "
                    + getEntityType().getName() + " AS c");
            query.setMaxResults(max);
            @SuppressWarnings("unchecked")
            List<K> rs = (List<K>) query.getResultList();
            List<K> result = new ArrayList<K>();
            result.addAll(rs);
            //em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public void save(T entity) {
        EntityManager em = entityManagerProvider.get();
        try {
            em.getTransaction().begin();
            K key = entity.getId();
            if (key == null) {
                em.persist(entity);
            } else {
                T retrieved = em.find(getEntityType(), key);
                if (retrieved == null) {
                    em.persist(entity);
                } else {
                    em.merge(entity);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void saveAll(List<T> entityList) {
        for (T entity : entityList) {
            save(entity);
        }
    }

    @Override
    public void delete(T entity) {
        deleteByKey(entity.getId());
    }

    final public void deleteByKey(K key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        log.info("about to find for delete: " + key);
        T entityRetrieved = em.find(getEntityType(), key);
        preDelete(entityRetrieved);
        em.remove(entityRetrieved);
        em.getTransaction().commit();
        em.close();
    }

    protected void preDelete(T entity) {

    }

    @Override
    public void deleteAll(List<T> entityList) {
        for (T entity : entityList) {
            delete(entity);
        }
    }

    @Override
    public boolean isEmpty() {
        List<K> keys = findAllKeys(1);
        return keys.isEmpty();
    }

    @Override
    public int count(int max) {
        List<K> keys = findAllKeys(max);
        return keys.size();
    }

    @Override
    public boolean deleteBulk(int max) {
        List<K> entityList = findAllKeys(max);
        for (K key : entityList) {
            deleteByKey(key);
        }
        return isEmpty();
    }

    public abstract Class<U> getEntityType();
}
