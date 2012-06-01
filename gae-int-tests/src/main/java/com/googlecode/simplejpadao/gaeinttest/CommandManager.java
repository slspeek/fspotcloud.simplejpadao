package com.googlecode.simplejpadao.gaeinttest;

import com.googlecode.simplejpadao.SimpleDAOGenIdImpl;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class CommandManager extends SimpleDAOGenIdImpl<Command, CommandEntity, Long> implements CommandDAO {

    @Inject
    public CommandManager(Provider<EntityManager> entityManagerProvider) {
        super(CommandEntity.class, entityManagerProvider);

    }

    @Override
    public Command newEntity() {
        return new CommandEntity();
    }
}
