package com.googlecode.simplejpadao.gaeinttest;

import java.util.Date;
import javax.persistence.*;

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
