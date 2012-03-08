/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

/**
 *
 * @author steven
 */
public interface HasSetKey<K> extends HasKey<K> {

    void setId(K id);
}
