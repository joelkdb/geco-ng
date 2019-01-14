/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import java.util.List;
import javax.ejb.Local;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;

/**
 *
 * @author user pc
 */
@Local
public interface BulletinDAOBeanLocal extends GenericDAOBeanLocal<Bulletin, BulletinPK> {

    /**
     * Renvoie toutes occurences de la classe bulletin dont un attribut est null
     *
     * @return
     */
    List<Bulletin> getAllNul();

    /**
     * Renvoie la somme des coefficients des matières concernés pour
     * l'établissement d'un bulletin
     *
     * @return
     */
    Long getCoefSum();
}
