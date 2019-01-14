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
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;

/**
 *
 * @author user pc
 */
@Stateless
public class BulletinDAOBean extends GenericDAOBean<Bulletin, BulletinPK>
        implements BulletinDAOBeanLocal {

    public BulletinDAOBean() {
        super(Bulletin.class);
    }

    @Override
    public List<Bulletin> getAllNul() {
        Query query = this.em.createQuery("SELECT b FROM Bulletin b, Matiere m, Professeur p "
                + "WHERE b.professeur.code = p.code AND b.matiere.code = m.code AND (b.moyenneClasse IS NULL "
                + "OR b.noteDevoir IS NULL OR b.noteCompo IS NULL)");
        return query.getResultList();
    }

    @Override
    public Long getCoefSum() {
        Query query  = this.em.createQuery("SELECT SUM(m.coefficient) "
                + "FROM Bulletin b, Matiere m "
                + "WHERE b.matiere.code = m.code");
        return (Long)query.getSingleResult();
    }

}
