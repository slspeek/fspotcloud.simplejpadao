/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.j2eeinttest;

import com.googlecode.simplejpadao.test.genid.GenIdDAOTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author steven
 */
public class CommandTest extends GenIdDAOTest<Command, Long> {

    @Test
    public void modification() {
        Command cmd = ((CommandDAO) getDao()).newEntity();
        getDao().save(cmd);
        Long rKey = cmd.getId();
        cmd.setName("Richard");
        getDao().save(cmd);
        assertEquals("Richard", getDao().find(rKey).getName());

    }

    @Test
    public void withConstraint() {
        Command cmd;
        cmd = ((CommandDAO) getDao()).newEntity();
        cmd.setValidated(false);
        cmd.setName("foo");
        getDao().save(cmd);

        cmd = ((CommandDAO) getDao()).newEntity();
        cmd.setValidated(true);
        cmd.setName("foo");
        getDao().save(cmd);

        cmd = ((CommandDAO) getDao()).newEntity();
        cmd.setValidated(true);
        cmd.setName("bar");
        getDao().save(cmd);


        cmd = null;

        List<Command> result = getDao().findAllWhere(1000, "name = 'foo'");
        assertEquals(2, result.size());

        result = getDao().findAllWhere(1000, "name = 'foo' AND validated = true");
        assertEquals(1, result.size());

    }

}
