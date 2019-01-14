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
import tg.codeart.geco.ng.dao.ClasseDAOBeanLocal;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.GecoUtils;

/**
 *
 * @author user pc
 */
@Stateless
public class ClasseServiceBean extends GenericServiceBean<Classe, String>
        implements ClasseServiceBeanRemote {

    @EJB
    private ClasseDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @Override
    protected GenericDAOBeanLocal<Classe, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Classe e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Classe e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression de la " + e.toString() + ".", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(Classe e) {
        try {
            super.addOne(e);
            this.logService.info("Ajout de la " + e.toString() + ".", GecoConstants.LOG_METIER);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(Classe c) {
        try {
            this.dao.updateOne(c);
            this.logService.info("Modification de la " + c.toString(), GecoConstants.LOG_METIER);
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
