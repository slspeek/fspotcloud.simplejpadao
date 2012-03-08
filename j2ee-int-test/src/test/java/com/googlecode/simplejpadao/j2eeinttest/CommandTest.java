/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.j2eeinttest.CommandDAO;
import com.googlecode.simplejpadao.j2eeinttest.Command;
import com.googlecode.simplejpadao.HasKey;
import com.googlecode.simplejpadao.test.genid.GenIdDAOTest;
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
