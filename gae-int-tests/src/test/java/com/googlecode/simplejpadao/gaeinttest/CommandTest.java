/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.gaeinttest;


import com.googlecode.simplejpadao.gaeinttest.CommandDAO;
import com.googlecode.simplejpadao.gaeinttest.Command;
import com.googlecode.simplejpadao.test.genid.GenIdDAOTest;
import java.util.List;
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
    
    @Test
    public void withConstraint() {
        Command cmd;
        cmd = ((CommandDAO)getDao()).newEntity();
        cmd.setValid(false);
        cmd.setName("foo");
        getDao().save(cmd);
        
        cmd = ((CommandDAO)getDao()).newEntity();
        cmd.setValid(true);
        cmd.setName("foo");
        getDao().save(cmd);
        
        cmd = ((CommandDAO)getDao()).newEntity();
        cmd.setValid(true);
        cmd.setName("bar");
        getDao().save(cmd);
        
        
        cmd = null;
        
        //List<Command> result = getDao().findAllWhere(1000, "name = ? AND valid = ?", "foo", Boolean.TRUE);
        List<Command> result = getDao().findAllWhere(1000, "name = 'foo' AND valid = true");
        assertEquals(1, result.size());
        
        
    }
    
}
