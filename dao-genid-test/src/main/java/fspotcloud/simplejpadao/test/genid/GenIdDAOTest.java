/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test.genid;

import com.google.guiceberry.junit4.GuiceBerryRule;
import fspotcloud.simplejpadao.AbstractDAO;
import fspotcloud.simplejpadao.HasId;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import fspotcloud.simplejpadao.test.AbstractDAOTestBase;
import fspotcloud.simplejpadao.test.EmptyGuiceBerryEnv;
import javax.inject.Inject;
import org.junit.Rule;

/**
 *
 * @author steven
 */
public class GenIdDAOTest<T extends HasId> extends AbstractDAOTestBase<T> {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
   
    
    @Inject SimpleDAOGenId dao;

    @Override
    public AbstractDAO<T> getDao() {
        return dao;
    }

    @Override
    public T newUniqueInstance() {
        return (T) dao.newEntity();
    }
}
