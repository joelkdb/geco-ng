/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import java.util.List;
import javax.ejb.Remote;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;


/**
 *
 * @author user pc
 */
@Remote
public interface BulletinServiceBeanRemote extends GenericServiceBeanRemote<Bulletin, BulletinPK>{
    
    List<Bulletin> getAllNul();
    
    /**
     * Renvoie la somme des coefficients des matières concernés pour
     * l'établissement d'un bulletin
     *
     * @return
     */
    Long getCoefSum();
}
