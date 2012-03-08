package com.googlecode.simplejpadao.test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.guiceberry.junit4.GuiceBerryRule;
import com.googlecode.simplejpadao.*;
import javax.inject.Inject;
import org.junit.Rule;

/**
 *
 * @author steven
 */
public class NamedIdDAOTest<T extends HasSetKey<K>,K> extends AbstractDAOTestBase<T,K> {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
   
    
    @Inject SimpleDAONamedId dao;
    
    int counter;

    @Override
    public AbstractDAO<T,K> getDao() {
        return dao;
    }

    @Override
    public T newUniqueInstance() {
        String id = String.valueOf(counter++);
        return (T) dao.newEntity(id);
    }
}
