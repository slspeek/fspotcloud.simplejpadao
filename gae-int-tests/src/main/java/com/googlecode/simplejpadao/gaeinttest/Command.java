package com.googlecode.simplejpadao.gaeinttest;

import com.googlecode.simplejpadao.HasKey;
import java.util.Date;

public interface Command extends HasKey<Long> {

    Long getId();

    void setName(String name);

    String getName();
    
    boolean getValid();
    
    void setValid(boolean valid);
}