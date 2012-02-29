package fspotcloud.simplejpadao.j2eeinttest;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;

import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class NamedIdGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(NamedIdDAO.class).to(NamedIdRepository.class);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby-command");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(SimpleDAONamedId.class).to(NamedIdRepository.class);
    }
}