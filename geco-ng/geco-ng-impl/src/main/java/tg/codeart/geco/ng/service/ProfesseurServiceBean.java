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
import tg.codeart.geco.ng.dao.ProfesseurDAOBeanLocal;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Professeur;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class ProfesseurServiceBean extends GenericServiceBean<Professeur, String>
        implements ProfesseurServiceBeanRemote {

    @EJB
    private ProfesseurDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @Override
    protected GenericDAOBeanLocal<Professeur, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Professeur e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Professeur e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression du Professeur " + e.getNom() + e.getPrenom() + ".",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Professeur e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout du Professeur " + e.getNom() + e.getPrenom() + ".",
                    GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(Professeur p) {
        try {
            this.dao.updateOne(p);
            this.logService.info("Modification du " + p.toString(), GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Classe.SEQUENCE_CODE);
        return GecoUtils.generateProjectCode("P/", count.intValue());
    }

}
