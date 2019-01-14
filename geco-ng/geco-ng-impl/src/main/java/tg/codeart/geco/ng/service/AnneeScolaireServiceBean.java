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
import tg.codeart.geco.ng.dao.AnneeScolaireDAOBeanLocal;
import tg.codeart.geco.ng.entites.AnneeScolaire;

/**
 *
 * @author user pc
 */
@Stateless
public class AnneeScolaireServiceBean extends GenericServiceBean<AnneeScolaire, Long> 
        implements AnneeScolaireServiceBeanRemote {

    @EJB
    private AnneeScolaireDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<AnneeScolaire, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(AnneeScolaire e) {
        return e.getId();
    }

    @Override
    public void deleteOne(AnneeScolaire e) {
        try {
            super.deleteOne(e);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(AnneeScolaire e) {
        try {
            super.addOne(e);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
    
    @Override
    public void update(AnneeScolaire as){
         try {
          this.dao.updateOne(as);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
    

}
