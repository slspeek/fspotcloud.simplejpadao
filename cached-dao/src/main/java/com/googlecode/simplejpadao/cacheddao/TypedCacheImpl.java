/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.cacheddao;

import com.google.common.reflect.TypeToken;
import com.googlecode.simplejpadao.HasKey;
import net.sf.jsr107cache.Cache;
import org.apache.commons.lang.SerializationUtils;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author steven
 */
public abstract class TypedCacheImpl<T extends HasKey<K> & Serializable, K, U> {

    @Inject
    private Cache cache;


    TypedCacheImpl() {

    }


    public T get(K key) {
        String cacheId = keyString(key);
        byte[] serializedTag = (byte[]) cache.get(cacheId);
        if (serializedTag != null) {
            T object = (T) SerializationUtils.deserialize(serializedTag);
            return object;
        } else {
            return null;
        }
    }

    private String keyString(K key) {
        return getEntityType().getName() + String.valueOf(key);
    }

    public void put(T object) {
        String cacheId;
        K key = object.getId();
        byte[] serializedObject = SerializationUtils.serialize(object);
        cacheId = keyString(key);
        cache.put(cacheId, serializedObject);
    }

    public void remove(T object) {
        removeByKey(object.getId());
    }

    public void removeByKey(K key) {
        String cacheId = keyString(key);
        cache.remove(cacheId);
    }

    abstract public Class<U> getEntityType();
}
