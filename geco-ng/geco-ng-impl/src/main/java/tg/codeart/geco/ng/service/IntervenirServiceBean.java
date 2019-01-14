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
import net.mediasoft.commons.core.service.GenericServiceBean;
import tg.codeart.geco.ng.dao.IntervenirDAOBeanLocal;
import tg.codeart.geco.ng.entites.Intervenir;
import tg.codeart.geco.ng.entites.IntervenirPK;

/**
 *
 * @author user pc
 */
@Stateless
public class IntervenirServiceBean extends GenericServiceBean<Intervenir, IntervenirPK> 
        implements IntervenirServiceBeanRemote{
    
    @EJB
    private IntervenirDAOBeanLocal dao;
    
    @Override
    protected GenericDAOBeanLocal<Intervenir, IntervenirPK> getDAO(){
        return this.dao;
    }

    @Override
    public IntervenirPK getId(Intervenir e) {
        return e.getIntervenirPK();
    }

    @Override
    public List<Intervenir> getALlNull() {
        return this.dao.getALlNull();
    }
    
}
