/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.Remote;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import tg.codeart.geco.ng.entites.Etablissement;

/**
 *
 * @author user pc
 */
@Remote
public interface EtablissementServiceBeanRemote
        extends GenericServiceBeanRemote<Etablissement, String> {

    /**
     * Méthode renvoyant le code généré pour un établissement
     *
     * @return
     */
    String generateCode();

    /**
     * Méthode permet de mettre à jour un objet etablissement
     *
     * @param e
     */
    void update(Etablissement e);
}
