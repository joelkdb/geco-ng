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
import tg.codeart.geco.ng.dao.TypeControleDAOBeanLocal;
import tg.codeart.geco.ng.entites.TypeControle;
import tg.codeart.geco.ng.utils.GecoConstants;

/**
 *
 * @author user pc
 */
@Stateless
public class TypeControleServiceBean extends GenericServiceBean<TypeControle, Long> 
        implements TypeControleServiceBeanRemote {

    @EJB
    private TypeControleDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeControle, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeControle e) {
        return e.getId();
    }

    @Override
    public void deleteOne(TypeControle tc) {
        try {
            super.deleteOne(tc);
            this.logService.info("Suppression du Type de Contrôle : " + tc.getLibelle(), 
                    GecoConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(TypeControle tc) {
        try {
            super.addOne(tc);
            this.logService.info("Ajout du Type de Contrôle : " + tc.getLibelle(), GecoConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(TypeControle tc) {
        try {
            this.dao.updateOne(tc);
            this.logService.info("Modification du Type de Contrôle : " + tc.getLibelle(), GecoConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
}
