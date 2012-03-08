/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

/**
 *
 * @author steven
 */
public interface SimpleDAONamedId<T extends HasSetKey<K>, K> extends AbstractDAO<T,K> {

    T findOrNew(K key);
    
    T newEntity(K key);
    
}
