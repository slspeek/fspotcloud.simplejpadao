/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao.test.genid;

import fspotcloud.simplejpadao.AbstractDAO;
import fspotcloud.simplejpadao.HasId;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import fspotcloud.simplejpadao.test.AbstractDAOTestBase;
import javax.inject.Inject;

/**
 *
 * @author steven
 */
public class GenIdDAOTest<T extends HasId>  extends AbstractDAOTestBase<T> {

    @Inject SimpleDAOGenId<T> dao;
    
    @Override
    public AbstractDAO<T> getDao() {
        return dao;
    }

    @Override
    public T newUniqueInstance() {
        return dao.newEntity();
    }
    
}
