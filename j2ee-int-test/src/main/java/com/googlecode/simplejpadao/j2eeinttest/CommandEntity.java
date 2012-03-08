package com.googlecode.simplejpadao.j2eeinttest;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CommandEntity implements Command {

    @Id
    @GeneratedValue(generator = "increment")
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Derby with Hibernate do not want this, I know
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    
    @Basic 
    private String name;
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

}
