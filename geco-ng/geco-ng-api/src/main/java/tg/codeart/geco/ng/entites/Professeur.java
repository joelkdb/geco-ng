/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "professeurs")
public class Professeur extends BaseEntity{
    
    public static final String SEQUENCE_CODE  = "professeur";
    
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    
    @Column(name = "nom", nullable = false, length = 15)
    private String nom;
    
    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;
    
    @Column(name = "date_naissance", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    
    @Column(name = "sexe", nullable = false, length = 1)
    private char sexe;
    
    @Column(name = "adresse", nullable = true, length = 50)
    private String adresse;
    
    @Column(name = "email", nullable = false, length = 35)
    private String email;
    
    @Column(name = "portable", nullable = false, length = 14)
    private String portable;
    
    @Column(name = "indicatif", nullable = false, length = 3)
    private String indicatif;
    
//    @ManyToOne(cascade = {})
//    @JoinColumn(name = "code_classe")
//    private Classe classe;

    public Professeur() {
    }

    public Professeur(String code, String nom, String prenom, Date dateNaissance, char sexe, String adresse, 
            String email, String portable, String indicatif) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.portable = portable;
        this.indicatif = indicatif;
//        this.classe = classe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.code);
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
        final Professeur other = (Professeur) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Professeur{" + "code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", adresse=" + adresse + ", email=" + email + ", portable=" + portable + '}';
    }
    
}
