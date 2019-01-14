/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import net.mediasoft.commons.core.service.GenericServiceBean;
import tg.codeart.geco.ng.dao.TypeEtablissementDAOBeanLocal;
import tg.codeart.geco.ng.entites.TypeEtablissement;

/**
 *
 * @author user pc
 */
@Stateless
public class TypeEtablissementServiceBean extends GenericServiceBean<TypeEtablissement, Long>
        implements TypeEtablissementServiceBeanRemote {

    @EJB
    private TypeEtablissementDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<TypeEtablissement, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeEtablissement e) {
        return e.getId();
    }

}
