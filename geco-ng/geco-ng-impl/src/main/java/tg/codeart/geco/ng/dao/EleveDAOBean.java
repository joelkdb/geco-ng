/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import net.mediasoft.commons.core.dao.GenericDAOBean;
import tg.codeart.geco.ng.entites.Eleve;

/**
 *
 * @author user pc
 */
@Stateless
public class EleveDAOBean extends GenericDAOBean<Eleve, String> implements EleveDAOBeanLocal {

    public EleveDAOBean() {
        super(Eleve.class);
    }

    @Override
    public List<Eleve> getStudentsFromSchool(String code) {
        String jpql = "SELECT e "
                + "FROM Eleve e, Classe c, Etablissement et "
                + "WHERE e.classe.code = c.code AND c.etablissement.code = et.code AND et.code = :code";
        Query query = this.em.createQuery(jpql);
        query.setParameter("code", code);
        return query.getResultList();
    }

}
