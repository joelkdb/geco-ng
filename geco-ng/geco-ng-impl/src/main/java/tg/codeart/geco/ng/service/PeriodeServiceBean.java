/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import net.mediasoft.commons.core.exception.BusinessException;
import net.mediasoft.commons.core.service.GenericServiceBean;
import tg.codeart.geco.ng.dao.PeriodeDAOBeanLocal;
import tg.codeart.geco.ng.entites.Periode;

/**
 *
 * @author user pc
 */
@Stateless
public class PeriodeServiceBean extends GenericServiceBean<Periode, Long> 
        implements PeriodeServiceBeanRemote {

    @EJB
    private PeriodeDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Periode, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Periode e) {
        return e.getId();
    }

    @Override
    public void deleteOne(Periode p) {
        try {
            super.deleteOne(p);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Periode p) {
        try {
            super.addOne(p);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(Periode p) {
        try {
            this.dao.updateOne(p);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
}
