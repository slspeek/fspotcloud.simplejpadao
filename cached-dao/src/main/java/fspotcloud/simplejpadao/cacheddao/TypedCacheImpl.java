/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.cacheddao;

import fspotcloud.simplejpadao.HasKey;
import java.io.Serializable;
import net.sf.jsr107cache.Cache;
import org.apache.commons.lang.SerializationUtils;

/**
 *
 * @author steven
 */
public class TypedCacheImpl<T extends HasKey<K> & Serializable, K>
        implements TypedCache<T, K> {

    private final Cache cache;
    private final Class type;

    public TypedCacheImpl(Cache cache, Class type) {
        this.cache = cache;
        this.type = type;
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
        return type.getName() + String.valueOf(key);
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
}
