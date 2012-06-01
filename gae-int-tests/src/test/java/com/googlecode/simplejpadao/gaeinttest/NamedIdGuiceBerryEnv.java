package com.googlecode.simplejpadao.gaeinttest;

import com.googlecode.simplejpadao.gaeinttest.CommandDAO;
import com.googlecode.simplejpadao.gaeinttest.CommandManager;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.simplejpadao.EMProvider;
import com.googlecode.simplejpadao.SimpleDAOGenId;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class NamedIdGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        install(new NamedIdModelModule());
    }
}
class NamedIdModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(3));
        bind(EntityManager.class).toProvider(EMProvider.class);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("gae-command");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        
        bind(NamedIdDAO.class).to(NamedIdRepository.class);
        bind(SimpleDAONamedId.class).to(NamedIdRepository.class);
    }
}
    