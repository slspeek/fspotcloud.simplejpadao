package com.googlecode.simplejpadao.gaeinttest;

import com.googlecode.simplejpadao.HasSetKey;

public interface NamedId extends HasSetKey<String> {

    void setName(String name);

    String getName();
}