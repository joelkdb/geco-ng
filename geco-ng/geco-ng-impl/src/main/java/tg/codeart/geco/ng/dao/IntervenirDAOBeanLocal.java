/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import java.util.List;
import javax.ejb.Local;
import net.mediasoft.commons.core.dao.GenericDAOBeanLocal;
import tg.codeart.geco.ng.entites.Intervenir;
import tg.codeart.geco.ng.entites.IntervenirPK;

/**
 *
 * @author user pc
 */
@Local
public interface IntervenirDAOBeanLocal extends GenericDAOBeanLocal<Intervenir, IntervenirPK> {

    /**
     * Renvoie toutes occurences de la classe Intervenir dont la note est null
     *
     * @return
     */
    List<Intervenir> getALlNull();
}
