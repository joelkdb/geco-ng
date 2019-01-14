/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.reports;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author MANU
 *
 */
@ManagedBean(name = "globalExportManagedBean")
@ViewScoped
public class ExportManagedBean implements Serializable {

    private List<String> listeDesFormat;
    private String formatDocument;
    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;
    private String adresseEtat;

    /**
     * Creates a new instance of ExportManagedBean
     */
    public ExportManagedBean() {
        this.listeDesFormat = new ArrayList();
        this.listeDesFormat.add("PDF");
        this.listeDesFormat.add("EXCEL");
//        this.listeDesFormat.add("HTML");
//        this.listeDesFormat.add("RTF");
//        this.listeDesFormat.add("XML");
//        this.listeDesFormat.add("CSV");
        this.formatDocument = "PDF";
    }

    public String getAdresseEtat() {
        return adresseEtat;
    }

    public void setAdresseEtat(String adresseEtat) {
        this.adresseEtat = adresseEtat;
    }

    public List<String> getListeDesFormat() {
        return listeDesFormat;
    }

    public void setListeDesFormat(List<String> listeDesFormat) {
        this.listeDesFormat = listeDesFormat;
    }

    public String getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(String format) {
        this.formatDocument = format;
    }

    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public void exporter() {
        System.out.println("entrer");
        Boolean val = this.formatDocument.equalsIgnoreCase("EXCEL");
        List liste = this.conteneur.isCondense() ? this.conteneur.getListeEtatCondense() : this.conteneur.getListeEtat();
        liste = liste == null ? this.conteneur.getMapEtat() : liste;
        liste = liste == null ? new ArrayList() : liste;
        EtatRechercheMultiple exporteur;
        if (this.conteneur.getMapEtat() != null) {
            exporteur = new EtatRechercheMultiple(liste, true);
        } else {
            exporteur = new EtatRechercheMultiple(liste);
        }
        this.conteneur.setParametres(this.conteneur.getParametres() == null ? new HashMap() : this.conteneur.getParametres());
        this.conteneur.getParametres().put("FICHIER_EXCEL", val);
        this.conteneur.getParametres().put("IS_IGNORE_PAGINATION", val);
        exporteur.changerFormat(formatDocument);
        exporteur.setCompileFileName(this.conteneur.getCompileFileName());
        //System.out.println(" Nom du fichier :"+this.conteneur.getCompileFileName());
        try {
            exporteur.exporter(this.conteneur.getParametres());
        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (IOException s) {
            System.out.println(s.getMessage());
        }
        //   System.out.println("entrer");
    }
}
