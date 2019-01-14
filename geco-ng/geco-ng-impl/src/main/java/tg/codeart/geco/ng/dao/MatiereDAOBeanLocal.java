/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import javax.ejb.Local;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import tg.codeart.geco.ng.entites.Matiere;

/**
 *
 * @author user pc
 */
@Local
public interface MatiereDAOBeanLocal extends GenericDAOBeanLocal<Matiere, String>{
    
}
