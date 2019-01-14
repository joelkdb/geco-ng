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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "eleves")
public class Eleve extends BaseEntity {

    public static final String SEQUENCE_CODE = "eleve";

    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;

    @Column(name = "nom", nullable = false, length = 25)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 35)
    private String prenom;

    @Column(name = "dateNaissance", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "sexe", nullable = false, length = 1)
    private char sexe;

    @Column(name = "adresse", nullable = true, length = 50)
    private String adresse;

    @Column(name = "nationalite", nullable = false, length = 14)
    private String nationalite;
    
    @Column(name = "nom_parent", nullable = false, length = 25)
    private String nomParent;

    @Column(name = "prenom_parent", nullable = false, length = 35)
    private String prenomParent;

    @Column(name = "adresse_parent", nullable = true, length = 50)
    private String adresseParent;

    @Column(name = "email_parent", nullable = false, length = 35)
    private String emailParent;

    @Column(name = "portable_parent_un", nullable = false, length = 14)
    private String portableParent1;
    
    @Column(name = "portable_parent_deux", nullable = true, length = 14)
    private String portableParent2;
    
    @Column(name = "indicatif_un", nullable = false, length = 3)
    private String indicatif1;
    
    @Column(name = "indicatif_deux", nullable = false, length = 3)
    private String indicatif2;

    @Column(name = "abonne", nullable = false)
    private boolean abonne;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "code_classe")
    private Classe classe;

    public Eleve() {
    }

    public Eleve(String code, String nom, String prenom, Date dateNaissance, char sexe, String adresse, 
            String nationalite, String prenomParent, String adresseParent, String emailParent, String portableParent, 
            boolean abonne, Classe classe, String nomParent, String portableParent2, String indicatif1, 
            String indicatif2) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.nationalite = nationalite;
        this.prenomParent = prenomParent;
        this.adresseParent = adresseParent;
        this.emailParent = emailParent;
        this.portableParent1 = portableParent;
        this.abonne = abonne;
        this.classe = classe;
        this.nomParent = nomParent;
        this.portableParent2 = portableParent2;
        this.indicatif1 = indicatif1;
        this.indicatif2 = indicatif2;
    }
    
    public String getAbonne1(){
        if(this.isAbonne()){
            return "Oui";
        }else{
            return "Non";
        }
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

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getPrenomParent() {
        return prenomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }

    public String getAdresseParent() {
        return adresseParent;
    }

    public void setAdresseParent(String adresseParent) {
        this.adresseParent = adresseParent;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public String getPortableParent1() {
        return portableParent1;
    }

    public void setPortableParent1(String portableParent1) {
        this.portableParent1 = portableParent1;
    }

    public boolean isAbonne() {
        return abonne;
    }

    public void setAbonne(boolean abonne) {
        this.abonne = abonne;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getPortableParent2() {
        return portableParent2;
    }

    public void setPortableParent2(String portableParent2) {
        this.portableParent2 = portableParent2;
    }

    public String getIndicatif1() {
        return indicatif1;
    }

    public void setIndicatif1(String indicatif1) {
        this.indicatif1 = indicatif1;
    }

    public String getIndicatif2() {
        return indicatif2;
    }

    public void setIndicatif2(String indicatif2) {
        this.indicatif2 = indicatif2;
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
        final Eleve other = (Eleve) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Eleve{" + "code=" + code + ", nom=" + nom + ", prenom=" + prenom 
                + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", adresse=" + adresse 
                + ", nationalite=" + nationalite + ", nomParent=" + nomParent + ", prenomParent=" + prenomParent 
                + ", adresseParent=" + adresseParent + ", emailParent=" + emailParent 
                + ", portableParent1=" + portableParent1 + ", portableParent2=" + portableParent2 
                + ", abonne=" + abonne + '}';
    }

}
