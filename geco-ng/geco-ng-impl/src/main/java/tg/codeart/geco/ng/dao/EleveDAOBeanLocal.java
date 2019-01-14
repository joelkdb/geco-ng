/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import java.util.List;
import javax.ejb.Local;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import tg.codeart.geco.ng.entites.Eleve;

/**
 *
 * @author user pc
 */
@Local
public interface EleveDAOBeanLocal extends GenericDAOBeanLocal<Eleve, String>{
    
    /**
     * Renvoi tous les élèves abonnés au système d'un établissement
     * @return
     */
    List<Eleve> getStudentsFromSchool(String code);
}
