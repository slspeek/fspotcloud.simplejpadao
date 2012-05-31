/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import java.util.List;

/**
 *
 * @author steven
 */
public interface AbstractDAO<T,K> {

    List<T> findAll(int max);
    
    List<T> findAllWhere(int max, String contraint, Object... parameters);

    List<K> findAllKeys(int max);

    void save(T entity);

    void saveAll(List<T> entityList);

    T find(K key);

    void delete(T entity);
    
    void deleteByKey(K key);

    void deleteAll(List<T> entityList);

    boolean deleteBulk(int max);

    boolean isEmpty();
    
    int count(int max);
}
