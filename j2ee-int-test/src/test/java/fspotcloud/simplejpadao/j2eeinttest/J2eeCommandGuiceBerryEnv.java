package fspotcloud.simplejpadao.j2eeinttest;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class J2eeCommandGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        install(new CommandModelModule());
    }
}
class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandDAO.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(3));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby-command");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(SimpleDAOGenId.class).to(CommandManager.class);
    }
}