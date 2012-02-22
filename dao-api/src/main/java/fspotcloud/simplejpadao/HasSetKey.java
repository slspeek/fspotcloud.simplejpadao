/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

/**
 *
 * @author steven
 */
public interface HasSetKey<K> extends HasKey<K> {

    void setId(K id);
}
