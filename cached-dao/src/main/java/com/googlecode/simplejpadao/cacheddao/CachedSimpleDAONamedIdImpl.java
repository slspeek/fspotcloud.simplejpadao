/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import com.googlecode.simplejpadao.HasSetKey;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steven
 */
public abstract class CachedSimpleDAONamedIdImpl<T extends HasSetKey<K> & Serializable, U extends T, K>
        extends CachedBaseDao<T, U, K> implements SimpleDAONamedId<T, K> {

    @Override
    public T findOrNew(K key) {
        T entity = find(key);
        if (entity == null) {
            entity = newEntity(key);
        }
        return entity;
    }

    @Override
    public T newEntity(K key) {
        T entity;
        try {
            entity = getEntityType().newInstance();
            entity.setId(key);
            return entity;
        } catch (InstantiationException ex) {
            Logger.getLogger(CachedSimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CachedSimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
