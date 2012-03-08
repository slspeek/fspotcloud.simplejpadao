/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import java.io.Serializable;

/**
 *
 * @author steven
 */
public interface TypedCache<T extends Serializable, K> {

    T get(K key);

    void put(T object);

    void remove(T object);
    
    void removeByKey(K key);
}
