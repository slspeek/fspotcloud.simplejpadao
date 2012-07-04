package com.googlecode.simplejpadao.j2eeinttest;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NamedIdEntity implements NamedId {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    private String name;

    @Override
    public String toString() {
        String result = " : ";
        return result;
    }


    public NamedIdEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
