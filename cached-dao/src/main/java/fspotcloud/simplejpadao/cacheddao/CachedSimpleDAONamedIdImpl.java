/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.cacheddao;

import fspotcloud.simplejpadao.HasSetKey;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsr107cache.Cache;

/**
 *
 * @author steven
 */
public class CachedSimpleDAONamedIdImpl<T extends HasSetKey<K> & Serializable, U extends T, K>
        extends CachedBaseDao<T, U, K> implements SimpleDAONamedId<T, K> {

    private final Class<U> entityType;

    public CachedSimpleDAONamedIdImpl(Class<U> entityType,
            Cache cache, SimpleDAONamedId<T, K> delegate) {
        super(delegate, cache, entityType);
        this.entityType = entityType;
    }

    @Override
    public T findOrNew(K key) {
        T entity = find(key);
        if (entity == null) {
            entity = newEntity(key);
        }
        return entity;
    }

    @Override
    public T newEntity(K key) {
        T entity;
        try {
            entity = entityType.newInstance();
            entity.setId(key);
            return entity;
        } catch (InstantiationException ex) {
            Logger.getLogger(CachedSimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CachedSimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
