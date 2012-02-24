package fspotcloud.simplejpadao.j2eeinttest;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;

import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;


public class NamedIdGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(NamedIdDAO.class).to(NamedIdRepository.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("derby-command");
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(SimpleDAONamedId.class).to(NamedIdRepository.class);
    }
}