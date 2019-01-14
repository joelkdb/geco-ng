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
@Table
public class Controle extends BaseEntity{
  
    public static final String SEQUENCE = "controle";
    
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    
    @Column(name = "libelle", nullable = false, length = 40)
    private String libelle;
    
//    @Column(name = "note", nullable = true, length = 2)
//    private Integer note;
    
    @Column(name = "date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRealisation;
    
    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_type_controle", nullable = false)
    private TypeControle typeControle;
    
    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_annee_scolaire", nullable = false)
    private AnneeScolaire anneeScolaire;

    public Controle() {
    }

    public Controle(String code, String libelle, Integer note, Date date, TypeControle typeControle, AnneeScolaire anneeScolaire) {
        this.code = code;
        this.libelle = libelle;
        //this.note = note;
        this.dateRealisation = date;
        this.typeControle = typeControle;
        this.anneeScolaire = anneeScolaire;
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

//    public Integer getNote() {
//        return note;
//    }
//
//    public void setNote(Integer note) {
//        this.note = note;
//    }

    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public TypeControle getTypeControle() {
        return typeControle;
    }

    public void setTypeControle(TypeControle typeControle) {
        this.typeControle = typeControle;
    }

    public AnneeScolaire getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.code);
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
        final Controle other = (Controle) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle{" + "code=" + code + ", libelle=" + libelle + ", date=" + dateRealisation + '}';
    }
    
}
