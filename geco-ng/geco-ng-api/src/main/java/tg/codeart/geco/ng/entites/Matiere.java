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
import javax.persistence.Table;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "matieres")
public class Matiere extends BaseEntity{
    
    public static final String SEQUENCE_CODE = "matiere";
    
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    
    @Column(name = "libelle", nullable = false, length = 30)
    private String libelle;
    
    @Column(name = "coefficient", nullable = true)
    private Integer coefficient;
    
    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_serie", nullable = true)
    private Serie serie;
    
    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_type_matiere", nullable = false)
    private TypeMatiere typeMatiere;

    public Matiere() {
    }

    public Matiere(String code, String libelle, TypeMatiere typeMatiere, Integer coef, Serie serie) {
        this.code = code;
        this.libelle = libelle;
        this.typeMatiere = typeMatiere;
        this.coefficient = coef;
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

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public TypeMatiere getTypeMatiere() {
        return typeMatiere;
    }

    public void setTypeMatiere(TypeMatiere typeMatiere) {
        this.typeMatiere = typeMatiere;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Matiere other = (Matiere) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matiere{" + "code=" + code + ", libelle=" + libelle + '}';
    }
    
}
