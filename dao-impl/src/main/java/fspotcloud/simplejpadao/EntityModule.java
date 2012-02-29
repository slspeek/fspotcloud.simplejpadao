/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import com.google.inject.AbstractModule;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author steven
 */
public class EntityModule extends AbstractModule{
    
    private final String persistenceUnit;

    public EntityModule(String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }

    @Override
    protected void configure() {
        bind(EntityManager.class).toProvider(EMProvider.class);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnit);
        bind(EntityManagerFactory.class).toInstance(factory);
    }
}
