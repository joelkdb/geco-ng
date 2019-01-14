/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 *
 * @author joelkdb
 */
@Embeddable
public class IntervenirPK implements Serializable{
    
    @Basic(optional = false)
    @Column(name = "code_professeur", nullable = false)
    private String codeProfesseur;
    
    @Basic(optional = false)
    @Column(name = "code_eleve", nullable = false)
    private String codeEleve;
    
    @Column(name = "code_classe", nullable = false)
    private String codeClasse;
    
    @Column(name = "code_matiere", nullable = false)
    private String codeMatiere;
    
    @Column(name = "code_controle", nullable = false)
    private String codeControle;

    public IntervenirPK() {
    }

    public IntervenirPK(String codeProfesseur, String codeEleve, String codeClasse, String codeMatiere, String codeControle) {
        this.codeProfesseur = codeProfesseur;
        this.codeEleve = codeEleve;
        this.codeClasse = codeClasse;
        this.codeMatiere = codeMatiere;
        this.codeControle = codeControle;
    }

    public String getCodeProfesseur() {
        return codeProfesseur;
    }

    public void setCodeProfesseur(String codeProfesseur) {
        this.codeProfesseur = codeProfesseur;
    }

    public String getCodeEleve() {
        return codeEleve;
    }

    public void setCodeEleve(String codeEleve) {
        this.codeEleve = codeEleve;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getCodeControle() {
        return codeControle;
    }

    public void setCodeControle(String codeControle) {
        this.codeControle = codeControle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codeProfesseur);
        hash = 59 * hash + Objects.hashCode(this.codeEleve);
        hash = 59 * hash + Objects.hashCode(this.codeClasse);
        hash = 59 * hash + Objects.hashCode(this.codeMatiere);
        hash = 59 * hash + Objects.hashCode(this.codeControle);
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
        final IntervenirPK other = (IntervenirPK) obj;
        if (!Objects.equals(this.codeProfesseur, other.codeProfesseur)) {
            return false;
        }
        if (!Objects.equals(this.codeEleve, other.codeEleve)) {
            return false;
        }
        if (!Objects.equals(this.codeClasse, other.codeClasse)) {
            return false;
        }
        if (!Objects.equals(this.codeMatiere, other.codeMatiere)) {
            return false;
        }
        if (!Objects.equals(this.codeControle, other.codeControle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IntervenirPK{" + "codeProfesseur=" + codeProfesseur + ", codeEleve=" + codeEleve + ", codeClasse=" + codeClasse + ", codeMatiere=" + codeMatiere + ", codeControle=" + codeControle + '}';
    }
    
}
