/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test;

import fspotcloud.simplejpadao.AbstractDAO;
import fspotcloud.simplejpadao.HasId;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author steven
 */
public abstract class AbstractDAOTestBase<T extends HasId> {
    
    
    public abstract AbstractDAO<T> getDao();
    
    public abstract T newUniqueInstance();
        
    @Before
    public void setUp() {
        getDao().deleteBulk(1000);
    }
    
    protected void createAndSaveNewEntity() {
        T  entity = newUniqueInstance();
        getDao().save(entity);
    }
    @Test
    public void countZero() {
        Assert.assertEquals(0, getDao().count(1));
    } 
    
    @Test
    public void countOne() {
        createAndSaveNewEntity();
        Assert.assertEquals(1, getDao().count(1));
    }
    
    @Test
    public void countTwo() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        Assert.assertEquals(2, getDao().count(1));
    }
}
