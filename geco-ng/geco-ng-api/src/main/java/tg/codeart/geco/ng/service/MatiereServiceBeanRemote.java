/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.Remote;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import tg.codeart.geco.ng.entites.Matiere;

/**
 *
 * @author user pc
 */
@Remote
public interface MatiereServiceBeanRemote extends GenericServiceBeanRemote<Matiere, String> {

    /**
     * Méthode renvoyant le code généré pour une classe
     *
     * @return
     */
    String generateCode();

    /**
     * Méthode permet de mettre à jour un objet classe
     *
     * @param e
     */
    void update(Matiere e);
}
