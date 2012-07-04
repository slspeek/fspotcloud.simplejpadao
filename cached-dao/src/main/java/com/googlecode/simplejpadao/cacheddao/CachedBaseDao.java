/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import com.googlecode.simplejpadao.AbstractDAO;
import com.googlecode.simplejpadao.HasKey;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 *
 * @author steven
 */
public abstract class CachedBaseDao<T extends HasKey<K> & Serializable, U extends T, K>
        extends TypedCacheImpl<T, K, U>
        implements AbstractDAO<T, K> {
    @Inject
    protected Provider<EntityManager> entityManagerProvider;

    public void saveAll(List<T> entityList) {
        getDelegate().saveAll(entityList);
        putAll(entityList);
    }

    private void putAll(List<T> entityList) {
        for (T ent : entityList) {
            put(ent);
        }
    }

    public void save(T entity) {
        getDelegate().save(entity);
        put(entity);
    }

    public boolean isEmpty() {
        return getDelegate().isEmpty();
    }

    public List<K> findAllKeys(int max) {
        return getDelegate().findAllKeys(max);
    }

    public List<T> findAll(int max) {
        final List<T> result = getDelegate().findAll(max);
        putAll(result);
        return result;
    }

    public T find(K key) {
        final T result = getDelegate().find(key);
        if (result != null) {
            put(result);
        }
        return result;
    }

    public void deleteByKey(K key) {
        getDelegate().deleteByKey(key);
        removeByKey(key);
    }

    public boolean deleteBulk(int max) {
        //TODO:NO CACHE SUPPORT HERE YET
        return getDelegate().deleteBulk(max);
    }

    public void deleteAll(List<T> entityList) {
        getDelegate().deleteAll(entityList);
        for(T ent:entityList) {
            remove(ent);
        }
    }

    public void delete(T entity) {
        getDelegate().delete(entity);
        remove(entity);
    }

    public int count(int max) {
        return getDelegate().count(max);
    }

    public List<T> findAllWhere(int max, String contraint) {
        List<T> result = getDelegate().findAllWhere(max, contraint);
        putAll(result);
        return result;
    }

    public abstract AbstractDAO<T, K> getDelegate();



}
