/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;
import net.mediasoft.commons.core.entities.BaseEntity;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "type_etablissements")
public class TypeEtablissement extends BaseEntity{
   
   @Id
   @Column(name = "id", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
   @Column(name = "libelle", nullable = false, length = 27)
    private String libelle;
   
   @OneToMany(cascade = {}, mappedBy = "typeEtablissement")
   private List<Etablissement> etablissements = new ArrayList<>();

    public TypeEtablissement() {
    }

    public TypeEtablissement(String liebelle) {
        this.libelle = liebelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final TypeEtablissement other = (TypeEtablissement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeEtablissement{" + "id=" + id + ", liebelle=" + libelle + '}';
    }
    
}
