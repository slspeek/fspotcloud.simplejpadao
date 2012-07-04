package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.SimpleDAOGenIdImpl;

public class CommandManager extends SimpleDAOGenIdImpl<Command, CommandEntity, Long> implements CommandDAO {

    @Override
    public Command newEntity() {
        return new CommandEntity();
    }

    @Override
    public Class<CommandEntity> getEntityType() {
        return CommandEntity.class;
    }
}
