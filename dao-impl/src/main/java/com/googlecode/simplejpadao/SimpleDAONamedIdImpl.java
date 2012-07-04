/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steven
 */
public abstract class SimpleDAONamedIdImpl<T extends HasSetKey<K>, U extends T, K>
        extends DAOBase<T, U, K> implements SimpleDAONamedId<T,K> {

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
            Logger.getLogger(SimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
