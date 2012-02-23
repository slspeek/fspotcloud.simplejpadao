/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.gaeinttests;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class ModuleReusePOCTest {

    public static class Module extends FlexJukitoModule {

        protected void configureTest() {
            super.configureTest();
            
        }
    }
    
    @Inject
    String name;

    @Test
    public void echoName() {
        Logger.getLogger(ModuleReusePOCTest.class.getName()).info("Name " + name);
    }
}
