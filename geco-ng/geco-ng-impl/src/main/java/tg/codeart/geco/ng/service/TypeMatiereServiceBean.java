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
import tg.codeart.geco.ng.dao.TypeMatiereDAOBeanLocal;
import tg.codeart.geco.ng.entites.TypeMatiere;

/**
 *
 * @author user pc
 */
@Stateless
public class TypeMatiereServiceBean extends GenericServiceBean<TypeMatiere, Long> 
        implements TypeMatiereServiceBeanRemote {

    @EJB
    private TypeMatiereDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeMatiere, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeMatiere e) {
        return e.getId();
    }

    @Override
    public void deleteOne(TypeMatiere tm) {
        try {
            super.deleteOne(tm);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(TypeMatiere tm) {
        try {
            super.addOne(tm);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(TypeMatiere tm) {
        try {
            this.dao.updateOne(tm);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
}
