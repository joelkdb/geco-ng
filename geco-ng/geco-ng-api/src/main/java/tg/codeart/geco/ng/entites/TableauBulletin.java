/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.entites;

import java.math.BigDecimal;

/**
 *
 * @author joelkdb
 */
public class TableauBulletin {
    private String matiere;
    private String professeur;
    private BigDecimal moyClass;
    private BigDecimal noteDevoir;
    private BigDecimal noteCompo;
    //private BigDecimal moy;
    private BigDecimal noteDef;
    private Integer coef;
    private String appreciation;
    private String signature;

    public TableauBulletin() {
    }

    public TableauBulletin(String matiere, String professeur, BigDecimal moyClass, BigDecimal noteDevoir, 
            BigDecimal noteCompo, Integer coef, String appreciation, String signature, BigDecimal noteDef) {
        this.matiere = matiere;
        this.professeur = professeur;
        this.moyClass = moyClass;
        this.noteDevoir = noteDevoir;
        this.noteCompo = noteCompo;
        //this.moy = moy;
        this.noteDef = noteDef;
        this.coef = coef;
        this.appreciation = appreciation;
        this.signature = signature;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }

    public BigDecimal getMoyClass() {
        return moyClass;
    }

    public void setMoyClass(BigDecimal moyClass) {
        this.moyClass = moyClass;
    }

    public BigDecimal getNoteDevoir() {
        return noteDevoir;
    }

    public void setNoteDevoir(BigDecimal noteDevoir) {
        this.noteDevoir = noteDevoir;
    }

    public BigDecimal getNoteCompo() {
        return noteCompo;
    }

    public void setNoteCompo(BigDecimal noteCompo) {
        this.noteCompo = noteCompo;
    }

//    public BigDecimal getMoy() {
//        return moy;
//    }
//
//    public void setMoy(BigDecimal moy) {
//        this.moy = moy;
//    }

    public BigDecimal getNoteDef() {
        return noteDef;
    }

    public void setNoteDef(BigDecimal noteDef) {
        this.noteDef = noteDef;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "TableauBulletin{" + "matiere=" + matiere + ", professeur=" + professeur + ", moyClass=" + moyClass 
                + ", noteDevoir=" + noteDevoir + ", noteCompo=" + noteCompo + ", coef=" + coef + ", appreciation=" 
                + appreciation + ", signature=" + signature + '}';
    }
    
}
