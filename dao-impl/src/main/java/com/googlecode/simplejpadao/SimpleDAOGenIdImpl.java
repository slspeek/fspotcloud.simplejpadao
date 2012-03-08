/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import com.googlecode.simplejpadao.HasKey;
import com.googlecode.simplejpadao.SimpleDAOGenId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 *
 * @author steven
 */
public class SimpleDAOGenIdImpl<T extends HasKey<K>, U extends T, K>
        extends DAOBase<T, U, K> implements SimpleDAOGenId<T, K> {

    public SimpleDAOGenIdImpl(Class<U> entityType, Provider<EntityManager> emProvider) {
        super(entityType, emProvider);
    }

    @Override
    public T newEntity() {
        try {
            return entityType.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(SimpleDAOGenIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SimpleDAOGenIdImpl.class.getName()).log(Level.SEVERE, null, ex);
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
