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
import tg.codeart.geco.ng.dao.MatiereDAOBeanLocal;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Matiere;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class MatiereServiceBean extends GenericServiceBean<Matiere, String>
        implements MatiereServiceBeanRemote {

    @EJB
    private MatiereDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @Override
    protected GenericDAOBeanLocal<Matiere, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Matiere e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Matiere e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression de la " + e.toString() + ".", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Matiere e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout de la " + e.toString() + ".", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(Matiere e) {
        try {
            this.dao.updateOne(e);
            this.logService.info("Modification de la " + e.toString(), GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Classe.SEQUENCE_CODE);
        return GecoUtils.generateProjectCode("M/", count.intValue());
    }

}
