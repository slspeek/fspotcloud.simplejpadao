package fspotcloud.simplejpadao.j2eeinttest;

import fspotcloud.simplejpadao.SimpleDAONamedIdImpl;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class NamedIdRepository extends SimpleDAONamedIdImpl<NamedId, NamedIdEntity, String> implements NamedIdDAO {

    @Inject
    public NamedIdRepository(Provider<EntityManager> entityManagerProvider) {
        super(NamedIdEntity.class, entityManagerProvider);
    }
}