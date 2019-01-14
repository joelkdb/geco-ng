/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import net.mediasoft.commons.core.service.GenericServiceBean;
import tg.codeart.geco.ng.dao.BulletinDAOBeanLocal;
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;

/**
 *
 * @author user pc
 */
@Stateless
public class BulletinServiceBean extends GenericServiceBean<Bulletin, BulletinPK>
        implements BulletinServiceBeanRemote {

    @EJB
    private BulletinDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Bulletin, BulletinPK> getDAO() {
        return this.dao;
    }

    @Override
    public BulletinPK getId(Bulletin e) {
        return e.getBulletinPK();
    }

    @Override
    public List<Bulletin> getAllNul() {
        return this.dao.getAllNul();
    }

    @Override
    public Long getCoefSum() {
        return this.dao.getCoefSum();
    }

}
