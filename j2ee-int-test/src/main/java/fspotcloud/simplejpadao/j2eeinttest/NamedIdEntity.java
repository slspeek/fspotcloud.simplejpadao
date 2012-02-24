package fspotcloud.simplejpadao.j2eeinttest;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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
