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

/**
 *
 * @author steven
 */
public abstract class CachedSimpleDAOGenIdImpl<T extends HasKey<K> & Serializable, U extends T, K>
        extends CachedBaseDao<T, U, K> implements SimpleDAOGenId<T, K> {

    @Override
    public T newEntity() {
        try {
            return getEntityType().newInstance();
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
