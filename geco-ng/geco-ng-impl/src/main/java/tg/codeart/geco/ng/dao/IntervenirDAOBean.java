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
import tg.codeart.geco.ng.entites.Intervenir;
import tg.codeart.geco.ng.entites.IntervenirPK;

/**
 *
 * @author user pc
 */
@Stateless
public class IntervenirDAOBean extends GenericDAOBean<Intervenir, IntervenirPK>
        implements IntervenirDAOBeanLocal {

    public IntervenirDAOBean() {
        super(Intervenir.class);
    }

    @Override
    public List<Intervenir> getALlNull() {
        Query query = this.em.createQuery("SELECT i FROM Intervenir i, Controle c, Classe cl, Matiere m, Professeur p, "
                + "Eleve e WHERE i.controle.code = c.code AND i.classe.code = cl.code AND i.professeur.code = p.code AND i.matiere.code = m.code AND i.eleve.code = e.code AND i.note IS NULL");
        return query.getResultList();
    }
}
