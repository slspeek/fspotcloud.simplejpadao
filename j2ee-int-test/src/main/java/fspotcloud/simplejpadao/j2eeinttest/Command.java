package fspotcloud.simplejpadao.j2eeinttest;

import fspotcloud.simplejpadao.HasKey;
import java.util.Date;

public interface Command extends HasKey<Long> {

    Long getId();

    void setName(String name);

    String getName();
}