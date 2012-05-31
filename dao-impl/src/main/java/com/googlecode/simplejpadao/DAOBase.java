/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import com.googlecode.simplejpadao.HasKey;
import com.googlecode.simplejpadao.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author steven
 */
public class DAOBase<T extends HasKey<K>, U extends T, K> implements AbstractDAO<T, K> {

    protected final Class<U> entityType;
    protected final Provider<EntityManager> entityManagerProvider;
    private static final Logger log = Logger.getLogger(DAOBase.class.getName());

    public DAOBase(Class<U> entityType, Provider<EntityManager> emProvider) {
        this.entityType = entityType;
        this.entityManagerProvider = emProvider;
    }

    @Override
    public T find(K key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        T entity = null;
        try {
            entity = em.find(entityType, key);
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

    public List<T> findAllWhere(int max, String contraint, Object... parameters) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        try {
            String queryString = "select c from "
                    + entityType.getName() + " AS c";
            if (contraint != null) {
                queryString += " WHERE " + contraint;
            }
            Query query = em.createQuery(queryString);
            int i = 1; // position-parameters are 1-based
            for (Object param: parameters) {
                query.setParameter(i, param);
                i++;
            }
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
                    + entityType.getName() + " AS c");
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
                T retrieved = em.find(entityType, key);
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

    public void deleteByKey(K key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        log.info("about to find for delete: " + key);
        T entityRetrieved = em.find(entityType, key);
        em.remove(entityRetrieved);
        em.getTransaction().commit();
        em.close();
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
}
