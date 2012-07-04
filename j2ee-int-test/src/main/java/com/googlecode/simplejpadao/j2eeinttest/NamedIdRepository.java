package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;

public class NamedIdRepository extends SimpleDAONamedIdImpl<NamedId, NamedIdEntity, String> implements NamedIdDAO {
    @Override
    public Class<NamedIdEntity> getEntityType() {
        return NamedIdEntity.class;
    }
}
