/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author steven
 */
public class DAOBase<T, U extends T> implements AbstractDAO<T> {

    protected final Class<U> entityType;
    protected final Provider<EntityManager> entityManagerProvider;

    public DAOBase(Class<U> entityType, Provider<EntityManager> emProvider) {
        this.entityType = entityType;
        this.entityManagerProvider = emProvider;
    }

    @Override
    public T find(Object key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        T entity = null;
        try {
            entity = em.find(entityType, key);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public List<T> findAll(int max) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select c from "
                    + entityType.getName() + " AS c");
            query.setMaxResults(max);
            @SuppressWarnings("unchecked")
            List<T> rs = (List<T>) query.getResultList();
            List<T> result = new ArrayList<T>();
            result.addAll(rs);
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object> findAllKeys(int max) {
        EntityManager em = entityManagerProvider.get();
        //em.getTransaction().begin();
        try {
            Query query = em.createQuery("select c.id from "
                    + entityType.getName() + " AS c");
            query.setMaxResults(max);
            @SuppressWarnings("unchecked")
            List<Object> rs = (List<Object>) query.getResultList();
            List<Object> result = new ArrayList<Object>();
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
            em.merge(entity);
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
        try {
           Method m = entity.getClass().getMethod("getId", new Class[] {});
           Object ret = m.invoke(entity, new Object[] {});
           
           Object id = ret;
           deleteByKey(id);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DAOBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteByKey(Object key) {
        EntityManager em = entityManagerProvider.get();
            em.getTransaction().begin();
        T entityRetrieved = em.find(entityType, key);
        try {
            System.out.println("Deleting " + key);
            em.remove(entityRetrieved);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
    }

    @Override
    public void deleteAll(List<T> entityList) {
        EntityManager em = entityManagerProvider.get();
        for (T entity : entityList) {
            delete(entity);
        }
    }

    @Override
    public boolean isEmpty() {
        List<Object> keys = findAllKeys(1);
        return keys.isEmpty();
    }
    
     @Override
    public int count(int max) {
        List<Object> keys = findAllKeys(max);
        return keys.size();
    }

    @Override
    public boolean deleteBulk(int max) {
        List<Object> entityList = findAllKeys(max);
        for (Object key: entityList) {
            deleteByKey(key);
        }
        return isEmpty();
    }
}
