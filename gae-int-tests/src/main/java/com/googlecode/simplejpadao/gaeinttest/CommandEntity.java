package com.googlecode.simplejpadao.gaeinttest;

import java.util.Date;
import javax.persistence.*;

@Entity
public class CommandEntity implements Command {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //Derby with Hibernate do not want this, I know
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
