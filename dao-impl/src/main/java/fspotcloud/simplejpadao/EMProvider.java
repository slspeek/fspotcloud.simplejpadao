/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author steven
 */
public class EMProvider implements Provider<EntityManager> {

    private final EntityManagerFactory emfInstance;

    @Inject
    public EMProvider(EntityManagerFactory emfInstance) {
        this.emfInstance = emfInstance;
    }

    @Override
    public EntityManager get() {
        return emfInstance.createEntityManager();
    }
}
