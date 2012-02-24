package fspotcloud.simplejpadao.j2eeinttest;

import fspotcloud.simplejpadao.HasSetKey;

public interface NamedId extends HasSetKey<String> {

    void setName(String name);

    String getName();
}