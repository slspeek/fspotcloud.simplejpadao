package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.HasKey;

public interface Command extends HasKey<Long> {

    Long getId();

    void setName(String name);

    String getName();

    boolean getValidated();

    void setValidated(boolean valid);
}