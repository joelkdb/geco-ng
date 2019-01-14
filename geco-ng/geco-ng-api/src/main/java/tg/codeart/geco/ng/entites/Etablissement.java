/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "etablissements")
public class Etablissement extends BaseEntity{
   
    public static final String SEQUENCE_CODE = "etablissement";
   @Id
   @Column(name = "code", nullable = false, length = 10)
    private String code;
   
   @Column(name = "nom", nullable = false)
    private String nom;
    
   @Column(name = "slogan", nullable = false, length = 50)
    private String slogan;
   
   @Column(name = "bp", nullable = true, length = 25)
    private String bp;
    
   @Column(name = "logo", nullable = true, length = 250)
    private String logo;
    
   @Column(name = "adresse", nullable = false, length = 100)
    private String adresse;
    
   @Column(name = "email", nullable = false, length = 40)
    private String email;
    
   @Column(name = "telephone", nullable = false, length = 14)
    private String telephone;
    
   @Column(name = "actif", nullable = false)
    private boolean actif;
   
   @ManyToOne
   @JoinColumn(name = "id_type_etablissement")
   private TypeEtablissement typeEtablissement;
   
   @OneToMany(cascade = {}, mappedBy = "etablissement")
   private List<Classe> classes  = new ArrayList<>();

    public Etablissement() {
    }

    public Etablissement(String code, String nom, String slogan, String logo, String adresse, String email, 
            String telephone, boolean actif, TypeEtablissement typeEtablissement, String bp) {
        this.code = code;
        this.nom = nom;
        this.slogan = slogan;
        this.logo = logo;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.actif = actif;
        this.typeEtablissement = typeEtablissement;
        this.bp = bp;
    }

    public String getActif1(){
        if(this.isActif()){
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public TypeEtablissement getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(TypeEtablissement typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.code);
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
        final Etablissement other = (Etablissement) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "code=" + code + ", nom=" + nom + ", slogan=" + slogan + ", logo=" + logo 
                + ", adresse=" + adresse + ", email=" + email + ", telephone=" + telephone + ", actif=" + actif + '}';
    }

    
   
}
