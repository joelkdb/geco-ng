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
import tg.codeart.geco.ng.dao.SerieDAOBeanLocal;
import tg.codeart.geco.ng.entites.Serie;

/**
 *
 * @author user pc
 */
@Stateless
public class SerieServiceBean extends GenericServiceBean<Serie, Long> implements SerieServiceBeanRemote {

    @EJB
    private SerieDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Serie, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Serie e) {
        return e.getId();
    }

    @Override
    public void deleteOne(Serie s) {
        try {
            super.deleteOne(s);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Serie s) {
        try {
            super.addOne(s);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(Serie s) {
        try {
            this.dao.updateOne(s);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
}
