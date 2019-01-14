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
import net.mediasoft.commons.core.exception.BusinessException;
import net.mediasoft.commons.core.service.GenericServiceBean;
import net.mediasoft.commons.core.service.SequenceServiceBeanRemote;
import tg.codeart.geco.ng.dao.EleveDAOBeanLocal;
import tg.codeart.geco.ng.entites.Eleve;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class EleveServiceBean extends GenericServiceBean<Eleve, String> 
        implements EleveServiceBeanRemote{
    
    @EJB
    private EleveDAOBeanLocal dao;
    
    @EJB
    private SequenceServiceBeanRemote sequenceService;
    
    @Override
    protected GenericDAOBeanLocal<Eleve, String> getDAO(){
        return this.dao;
    }

    @Override
    public String getId(Eleve e) {
        return e.getCode();
    }
    
    @Override
    public void deleteOne(Eleve e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression de l'élève " + e.getNom() + e.getPrenom() + "."
                    + "", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Eleve e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout de l'élève " + e.getNom() + e.getPrenom() + "."
                    + "", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }
    
    @Override
    public void update(Eleve c){
         try {
          this.dao.updateOne(c);
          this.logService.info("Modification de l'élève " + c.getNom() + c.getPrenom() + "."
                  + "", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Eleve.SEQUENCE_CODE);
        return GecoUtils.generateProjectCode("EL/", count.intValue());
    }

    @Override
    public List<Eleve> getStudentsFromSchool(String code) {
        return this.dao.getStudentsFromSchool(code);
    }
}
