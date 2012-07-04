package com.googlecode.simplejpadao.j2eeinttest;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommandEntity implements Command {

    @Id
    @GeneratedValue(generator = "increment")
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Derby with Hibernate do not want this, I know
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Basic
    private String name;
    @Basic
    private boolean valid;

    @Override
    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        String result = " : ";
        return result;
    }


    public CommandEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


}
