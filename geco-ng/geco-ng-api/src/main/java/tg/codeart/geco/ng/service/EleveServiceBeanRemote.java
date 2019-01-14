/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import java.util.List;
import javax.ejb.Remote;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import tg.codeart.geco.ng.entites.Eleve;

/**
 *
 * @author user pc
 */
@Remote
public interface EleveServiceBeanRemote extends GenericServiceBeanRemote<Eleve, String> {

    /**
     * Méthode renvoyant le code généré pour un élève
     *
     * @return
     */
    String generateCode();

    /**
     * Méthode permet de mettre à jour un objet Eleve
     *
     * @param e
     */
    void update(Eleve e);
    
    /**
     * Renvoi tous les élèves abonnés au système d'un établissement
     * @param code
     * @return
     */
    List<Eleve> getStudentsFromSchool(String code);
}
