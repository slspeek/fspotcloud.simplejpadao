/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import com.googlecode.simplejpadao.AbstractDAO;
import com.googlecode.simplejpadao.HasKey;
import java.io.Serializable;
import java.util.List;
import net.sf.jsr107cache.Cache;

/**
 *
 * @author steven
 */
public class CachedBaseDao<T extends HasKey<K> & Serializable, U extends T, K>
        extends TypedCacheImpl<T, K>
        implements AbstractDAO<T, K> {

    private final AbstractDAO<T, K> delegate;

    CachedBaseDao(AbstractDAO<T,K> delegate, Cache cache, Class<U> type) {
        super(cache, type);
        this.delegate = delegate;
    }

    public void saveAll(List<T> entityList) {
        delegate.saveAll(entityList);
        putAll(entityList);
    }

    private void putAll(List<T> entityList) {
        for (T ent : entityList) {
            put(ent);
        }
    }

    public void save(T entity) {
        delegate.save(entity);
        put(entity);
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public List<K> findAllKeys(int max) {
        return delegate.findAllKeys(max);
    }

    public List<T> findAll(int max) {
        final List<T> result = delegate.findAll(max);
        putAll(result);
        return result;
    }

    public T find(K key) {
        final T result = delegate.find(key);
        if (result != null) {
            put(result);
        }
        return result;
    }

    public void deleteByKey(K key) {
        delegate.deleteByKey(key);
        removeByKey(key);
    }

    public boolean deleteBulk(int max) {
        //NO CACHE SUPPORT HERE YET
        return delegate.deleteBulk(max);
    }

    public void deleteAll(List<T> entityList) {
        delegate.deleteAll(entityList);
        for(T ent:entityList) {
            remove(ent);
        }
    }

    public void delete(T entity) {
        delegate.delete(entity);
        remove(entity);
    }

    public int count(int max) {
        return delegate.count(max);
    }

    public List<T> findAllWhere(int max, String contraint, Object... parameters) {
        List<T> result = delegate.findAllWhere(max, contraint, parameters);
        putAll(result);
        return result;
    }
}
