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
public class BulletinPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "code_matiere", nullable = false)
    private String codeMatiere;
    
    @Basic(optional = false)
    @Column(name = "code_professeur", nullable = false)
    private String codeProfesseur;

    public BulletinPK() {
    }

    public BulletinPK(String codeMatiere, String codeProfesseur) {
        this.codeMatiere = codeMatiere;
        this.codeProfesseur = codeProfesseur;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getCodeProfesseur() {
        return codeProfesseur;
    }

    public void setCodeProfesseur(String codeProfesseur) {
        this.codeProfesseur = codeProfesseur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codeMatiere);
        hash = 89 * hash + Objects.hashCode(this.codeProfesseur);
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
        final BulletinPK other = (BulletinPK) obj;
        if (!Objects.equals(this.codeMatiere, other.codeMatiere)) {
            return false;
        }
        if (!Objects.equals(this.codeProfesseur, other.codeProfesseur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BulletinPK{" + "codeMatiere=" + codeMatiere + ", codeProfesseur=" + codeProfesseur + '}';
    }
    
}
