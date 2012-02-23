/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test.genid;

import com.google.guiceberry.junit4.GuiceBerryRule;
import fspotcloud.simplejpadao.AbstractDAO;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import fspotcloud.simplejpadao.test.AbstractDAOTestBase;
import fspotcloud.simplejpadao.test.EmptyGuiceBerryEnv;
import javax.inject.Inject;
import org.junit.Rule;

/**
 *
 * @author steven
 */
public class GenIdDAOTest<T, K> extends AbstractDAOTestBase<T, K> {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    SimpleDAOGenId dao;

    @Override
    public AbstractDAO<T, K> getDao() {
        return dao;
    }

    @Override
    public T newUniqueInstance() {
        return (T) dao.newEntity();
    }
}
