/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import com.googlecode.simplejpadao.HasKey;
import com.googlecode.simplejpadao.SimpleDAOGenId;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsr107cache.Cache;

/**
 *
 * @author steven
 */
public class CachedSimpleDAOGenIdImpl<T extends HasKey<K> & Serializable, U extends T, K>
        extends CachedBaseDao<T, U, K> implements SimpleDAOGenId<T, K> {

    private final SimpleDAOGenId<T, K> delegate;
    private final Class<U> entityType;

    public CachedSimpleDAOGenIdImpl(Cache cache, Class<U> entityType, SimpleDAOGenId<T, K> delegate) {
        super(delegate, cache, entityType);
        this.delegate = delegate;
        this.entityType = entityType;
    }

    @Override
    public T newEntity() {
        try {
            return entityType.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CachedSimpleDAOGenIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CachedSimpleDAOGenIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public T newPeristentEntity() {
        T entity = newEntity();
        save(entity);
        return entity;
    }
}
