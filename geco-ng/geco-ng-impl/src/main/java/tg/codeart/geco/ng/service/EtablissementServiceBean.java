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
import tg.codeart.geco.ng.dao.EtablissementDAOBeanLocal;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class EtablissementServiceBean extends GenericServiceBean<Etablissement, String> 
        implements EtablissementServiceBeanRemote {

    @EJB
    private EtablissementDAOBeanLocal dao;
    
    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @Override
    protected GenericDAOBeanLocal<Etablissement, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Etablissement e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Etablissement e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression de l'établissement " + "{" + e.getCode() + e.getNom() + "}",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Etablissement e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout de l'établissement " + "{" + e.getCode() + e.getNom() + "}",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
    
    @Override
    public void update(Etablissement as){
         try {
          this.dao.updateOne(as);
          this.logService.info("Modification d'un établissement "+ "{" + as.getCode() + as.getNom() + "}", 
                  GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Etablissement.SEQUENCE_CODE);
        return GecoUtils.generateProjectCode("E/", count.intValue());
    }

}
