/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "bulletins")
public class Bulletin extends BaseEntity {

    @EmbeddedId
    protected BulletinPK bulletinPK;

    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_professeur", insertable = false, updatable = false)
    private Professeur professeur;

    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_matiere", insertable = false, updatable = false)
    private Matiere matiere;

    @Column(name = "moyenneClasse", nullable = true)
    private BigDecimal moyenneClasse;

    @Column(name = "noteDevoir", nullable = true)
    private BigDecimal noteDevoir;

    @Column(name = "noteCompo", nullable = true)
    private BigDecimal noteCompo;
    
//    @Column(name = "appreciation", nullable = true)
//    private String appreciation;
   
    public Bulletin() {
    }

    public Bulletin(Professeur professeur, Matiere matiere, BigDecimal moyenneClasse, BigDecimal noteDevoir, 
            BigDecimal noteCompo, String appreciation) {
        this.professeur = professeur;
        this.matiere = matiere;
        this.moyenneClasse = moyenneClasse;
        this.noteDevoir = noteDevoir;
        this.noteCompo = noteCompo;
        //this.appreciation = appreciation;
    }

    public Bulletin(BulletinPK bulletinPK, Professeur professeur, Matiere matiere, BigDecimal moyenneClasse, 
            BigDecimal noteDevoir, BigDecimal noteCompo, String appreciation) {
        this.bulletinPK = bulletinPK;
        this.professeur = professeur;
        this.matiere = matiere;
        this.moyenneClasse = moyenneClasse;
        this.noteDevoir = noteDevoir;
        this.noteCompo = noteCompo;
        //this.appreciation = appreciation;
    }

    public Bulletin(String matiere, String professeur) {
        this.bulletinPK = new BulletinPK(matiere, professeur);
    }

    public BulletinPK getBulletinPK() {
        return bulletinPK;
    }

    public void setBulletinPK(BulletinPK bulletinPK) {
        this.bulletinPK = bulletinPK;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public BigDecimal getMoyenneClasse() {
        return moyenneClasse;
    }

    public void setMoyenneClasse(BigDecimal moyenneClasse) {
        this.moyenneClasse = moyenneClasse;
    }

    public BigDecimal getNoteDevoir() {
        return noteDevoir;
    }

    public void setNoteDevoir(BigDecimal noteDevoir) {
        this.noteDevoir = noteDevoir;
    }

    public BigDecimal getNoteCompo() {
        return noteCompo;
    }

    public void setNoteCompo(BigDecimal noteCompo) {
        this.noteCompo = noteCompo;
    }

//    public String getAppreciation() {
//        return appreciation;
//    }
//
//    public void setAppreciation(String appreciation) {
//        this.appreciation = appreciation;
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.bulletinPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bulletin other = (Bulletin) obj;
        if (!Objects.equals(this.bulletinPK, other.bulletinPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bulletin{" + "moyenneClasse=" + moyenneClasse + ", noteDevoir=" + noteDevoir + ", noteCompo=" + noteCompo + '}';
    }

}
