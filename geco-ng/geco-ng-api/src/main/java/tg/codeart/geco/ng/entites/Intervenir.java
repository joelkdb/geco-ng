/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.util.Objects;
import javax.persistence.CascadeType;
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
@Table(name = "intervenirs")
public class Intervenir extends BaseEntity{
    
    @EmbeddedId
    protected IntervenirPK intervenirPK;
    
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "code_professeur", insertable = false, updatable = false)
    private Professeur professeur;
    
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "code_eleve", insertable = false, updatable = false)
    private Eleve eleve;
    
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "code_classe", insertable = false, updatable = false)
    private Classe classe;
    
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "code_matiere", insertable = false, updatable = false)
    private Matiere matiere;
    
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH, 
        CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "code_controle", insertable = false, updatable = false)
    private Controle controle;
    
    @Column(name = "mention", nullable = true)
    private String mention;
    
    @Column(name = "note", nullable = true, length = 2)
    private Float note;
    
    @Column(name = "envoye", nullable = true)
    private boolean envoye;

    public Intervenir() {
    }

    public Intervenir(Professeur professeur, Eleve eleve, Classe classe, Matiere matiere, Controle controle, 
            String mention, boolean envoye, Float note) {
        this.professeur = professeur;
        this.eleve = eleve;
        this.classe = classe;
        this.matiere = matiere;
        this.controle = controle;
        this.mention = mention;
        this.note = note;
        this.envoye = envoye;
    }

    public Intervenir(IntervenirPK intervenirPK, Professeur professeur, Eleve eleve, Classe classe, Matiere matiere, 
            Controle controle, String mention, Float note) {
        this.intervenirPK = intervenirPK;
        this.professeur = professeur;
        this.eleve = eleve;
        this.classe = classe;
        this.matiere = matiere;
        this.controle = controle;
        this.mention = mention;
        this.note = note;
    }
    
    public Intervenir(String codeProfesseur, String codeEleve, String codeClasse, String codeMatiere, String codeControle) {
        this.intervenirPK = new IntervenirPK(codeProfesseur, codeEleve, codeClasse, codeMatiere, codeControle);
    }

    public IntervenirPK getIntervenirPK() {
        return intervenirPK;
    }

    public void setIntervenirPK(IntervenirPK intervenirPK) {
        this.intervenirPK = intervenirPK;
    }
    
    public String getEnvoye1(){
        if(this.isEnvoye()){
            return "Oui";
        }else{
            return "Non";
        }
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Controle getControle() {
        return controle;
    }

    public void setControle(Controle controle) {
        this.controle = controle;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public boolean isEnvoye() {
        return envoye;
    }

    public void setEnvoye(boolean envoye) {
        this.envoye = envoye;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.intervenirPK);
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
        final Intervenir other = (Intervenir) obj;
        if (!Objects.equals(this.intervenirPK, other.intervenirPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Intervenir{" + "intervenirPK=" + intervenirPK + '}';
    }
    
}
