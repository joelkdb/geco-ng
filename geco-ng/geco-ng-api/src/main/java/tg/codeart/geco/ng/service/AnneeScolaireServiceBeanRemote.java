/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.Remote;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import tg.codeart.geco.ng.entites.AnneeScolaire;

/**
 *
 * @author user pc
 */
@Remote
public interface AnneeScolaireServiceBeanRemote extends GenericServiceBeanRemote<AnneeScolaire, Long> {

    void update(AnneeScolaire as);
}
