/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.gaeinttests;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jukito.JukitoModule;

/**
 *
 * @author steven
 */
public abstract class FlexJukitoModule extends JukitoModule {

    protected void configureTest() {
        com.google.inject.Module module;
        try {
            String moduleName = System.getProperty("dao.test.module", "fspotcloud.simplejpadao.gaeinttests.ModA");
            Class moduleClass = Class.forName(moduleName);
            module = (com.google.inject.Module) moduleClass.newInstance();
            install(module);
        } catch (InstantiationException ex) {
            Logger.getLogger(ModuleReusePOCTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ModuleReusePOCTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModuleReusePOCTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
