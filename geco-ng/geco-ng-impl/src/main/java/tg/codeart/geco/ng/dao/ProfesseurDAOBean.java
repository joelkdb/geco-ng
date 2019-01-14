/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import javax.ejb.Stateless;
import net.mediasoft.commons.core.dao.GenericDAOBean;
import tg.codeart.geco.ng.entites.Professeur;

/**
 *
 * @author user pc
 */
@Stateless
public class ProfesseurDAOBean extends GenericDAOBean<Professeur, String>
        implements ProfesseurDAOBeanLocal {

    public ProfesseurDAOBean() {
        super(Professeur.class);
    }
}
