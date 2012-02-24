/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test.genid;

import com.google.guiceberry.junit4.GuiceBerryRule;
import fspotcloud.simplejpadao.AbstractDAO;
import fspotcloud.simplejpadao.HasKey;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import fspotcloud.simplejpadao.test.AbstractDAOTestBase;
import fspotcloud.simplejpadao.test.EmptyGuiceBerryEnv;
import javax.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steven
 */
public class GenIdDAOTest<T extends HasKey<K>, K> extends AbstractDAOTestBase<T, K> {

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
    
    @Test
    public void keyNotNullAfterSaving() {
        T ent = newUniqueInstance();
        getDao().save(ent);
        assertNotNull(ent.getId());
    }
}
