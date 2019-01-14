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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "classes")
public class Classe extends BaseEntity{
    
    public static final String SEQUENCE_CODE = "classe";
    @Id
    @Column(name = "code", nullable = false, length = 10)
    private String code;
    
    @Column(name = "libelle", nullable = false, length = 15)
    private String libelle;
    
    @ManyToOne
    @JoinColumn(name = "code_etablissement",nullable = false)
    private Etablissement etablissement;
    
    @ManyToOne
    @JoinColumn(name = "id_serie",nullable = true)
    private Serie serie;
    
//    @OneToMany(cascade = {}, mappedBy = "classe")
//    private List<Professeur> professeurs = new ArrayList<>();
    
    @OneToMany(cascade = {}, mappedBy = "classe")
    private List<Eleve> eleves = new ArrayList<>();
    
    public Classe() {
    }

    public Classe(String code, String libelle, Etablissement etablissement, Serie serie) {
        this.code = code;
        this.libelle = libelle;
        this.etablissement = etablissement;
        this.serie = serie;
    }
    
    public String getSerie1(){
        if(this.getSerie() == null){
            return "Pas de série";
        }else{
            return this.getSerie().getLibelle();
        }
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

//    public List<Professeur> getProfesseurs() {
//        return professeurs;
//    }
//
//    public void setProfesseurs(List<Professeur> professeurs) {
//        this.professeurs = professeurs;
//    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.code);
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
        final Classe other = (Classe) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classe{" + "code=" + code + ", libelle=" + libelle + '}';
    }
    
}
