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
public abstract class SimpleDAOGenIdImpl<T extends HasKey<K>, U extends T, K>
        extends DAOBase<T, U, K> implements SimpleDAOGenId<T, K> {

    @Override
    public T newEntity() {
        try {
            return getEntityType().newInstance();
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
