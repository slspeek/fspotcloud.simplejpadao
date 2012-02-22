/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test;

import fspotcloud.simplejpadao.AbstractDAO;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author steven
 */
public abstract class AbstractDAOTestBase<T,K> {
    
    
    public abstract AbstractDAO<T,K> getDao();
    
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
        Assert.assertEquals(2, getDao().count(2));
    }
    
    @Test
    public void emptyList() {
        Assert.assertTrue(getDao().findAll(1).isEmpty());
    }
    
    @Test
    public void oneItemList() {
        createAndSaveNewEntity();
        Assert.assertEquals(1, getDao().findAll(1).size());
    }
    
    @Test
    public void twoItemList() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        Assert.assertEquals(2, getDao().findAll(2).size());
    }
    
    @Test
    public void emptyKeyList() {
        Assert.assertTrue(getDao().findAllKeys(1).isEmpty());
    }
    
    @Test
    public void oneKeyList() {
        createAndSaveNewEntity();
        Assert.assertEquals(1, getDao().findAllKeys(1).size());
    }
    
    @Test
    public void twoKeyList() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        Assert.assertEquals(2, getDao().findAllKeys(2).size());
    }
}
