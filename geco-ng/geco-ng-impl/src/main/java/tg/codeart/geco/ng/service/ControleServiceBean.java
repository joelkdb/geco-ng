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
import net.mediasoft.commons.core.service.SequenceServiceBeanRemote;
import tg.codeart.geco.ng.dao.ControleDAOBeanLocal;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Controle;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class ControleServiceBean extends GenericServiceBean<Controle, String> 
        implements ControleServiceBeanRemote{
    
    @EJB
    private ControleDAOBeanLocal dao;
    
    @EJB
    private SequenceServiceBeanRemote sequenceService;
    
    @Override
    protected GenericDAOBeanLocal<Controle, String> getDAO(){
        return this.dao;
    }

    @Override
    public String getId(Controle e) {
        return e.getCode();
    }
    
    @Override
    public void deleteOne(Controle e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression d'un contrôle.",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Controle e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout d'un contrôle .",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
    
    @Override
    public void update(Controle c){
         try {
          this.dao.updateOne(c);
          this.logService.info("Modification d'un controle.", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Classe.SEQUENCE_CODE);
        return GecoUtils.generateProjectCode("C/", count.intValue());
    }
}
