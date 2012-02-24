/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.j2eeinttest;

import fspotcloud.simplejpadao.HasKey;
import fspotcloud.simplejpadao.test.genid.GenIdDAOTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steven
 */
public class CommandTest extends GenIdDAOTest<Command, Long> {
    
    @Test
    public void modification() {
        Command cmd = ((CommandDAO)getDao()).newEntity();
        getDao().save(cmd);
        Long rKey = cmd.getId();
        cmd.setName("Richard");
        getDao().save(cmd);
        assertEquals("Richard", getDao().find(rKey).getName());
                
    }
    
}
