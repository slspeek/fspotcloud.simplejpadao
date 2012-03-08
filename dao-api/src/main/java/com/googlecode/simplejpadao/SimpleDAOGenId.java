/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao;

import com.googlecode.simplejpadao.HasKey;
import java.util.List;

/**
 *
 * @author steven
 */
public interface SimpleDAOGenId<T extends HasKey<K>, K> extends AbstractDAO<T,K> {
    
    T newEntity();
    
    T newPeristentEntity();
}
