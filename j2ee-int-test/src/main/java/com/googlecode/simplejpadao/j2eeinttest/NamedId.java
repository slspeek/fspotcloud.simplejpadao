package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.HasSetKey;

public interface NamedId extends HasSetKey<String> {

    void setName(String name);

    String getName();
}