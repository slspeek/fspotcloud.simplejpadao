/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.simplejpadao.gaeinttests;

import com.google.inject.AbstractModule;

/**
 *
 * @author steven
 */
public class ModA extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).toInstance("Module A");
    }
    
}
