/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.test;

import com.googlecode.simplejpadao.AbstractDAO;
import com.googlecode.simplejpadao.HasKey;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author steven
 */
public abstract class AbstractDAOTestBase<T extends HasKey<K>,K> {
    
    
    public abstract AbstractDAO<T,K> getDao();
    
    public abstract T newUniqueInstance();
        
    @Before
    public void setUp() {
        getDao().deleteBulk(1000);
    }
    
    protected T createAndSaveNewEntity() {
        T  entity = newUniqueInstance();
        getDao().save(entity);
        return entity;
    }
    @Test
    public void countZero() {
        assertEquals(0, getDao().count(1));
    } 
    
    @Test
    public void countOne() {
        createAndSaveNewEntity();
        assertEquals(1, getDao().count(1));
    }
    
    @Test
    public void countTwo() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        assertEquals(2, getDao().count(2));
    }
    
    @Test
    public void emptyList() {
        assertTrue(getDao().findAll(1).isEmpty());
    }
    
    @Test
    public void oneItemList() {
        createAndSaveNewEntity();
        assertEquals(1, getDao().findAll(1).size());
    }
    
    @Test
    public void twoItemList() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        assertEquals(2, getDao().findAll(2).size());
    }
    
    @Test
    public void emptyKeyList() {
        assertTrue(getDao().findAllKeys(1).isEmpty());
    }
    
    @Test
    public void oneKeyList() {
        createAndSaveNewEntity();
        assertEquals(1, getDao().findAllKeys(1).size());
    }
    
    @Test
    public void twoKeyList() {
        createAndSaveNewEntity();
        createAndSaveNewEntity();
        assertEquals(2, getDao().findAllKeys(2).size());
    }
    
    @Test
    public void deleteByKeyOne() {
        T ent = createAndSaveNewEntity();
        K key = ent.getId();
        getDao().deleteByKey(key);
        assertEquals(0, getDao().count(1));
    }
    
    @Test
    public void deleteOne() {
        T ent = createAndSaveNewEntity();
        getDao().delete(ent);
        assertEquals(0, getDao().count(1));
    }
}
