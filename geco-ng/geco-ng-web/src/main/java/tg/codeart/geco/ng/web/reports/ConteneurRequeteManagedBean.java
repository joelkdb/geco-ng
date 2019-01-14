/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.reports;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author MANU
 */
@ManagedBean
@SessionScoped
public class ConteneurRequeteManagedBean implements Serializable {

    private String requete;
    private List listeEtat;
    private List<Map<String, Object>> mapEtat;

    private String compileFileName;
    private Map<String, Object> parametres;
    private Boolean condense;
    private List listeEtatCondense;
    private String elementsDerniereRecherche;
    private byte[] ficherPdf;
    private InputStream fluxPdf;
    private Map<String, List> mapListeEtat;

    private List secondeListe;

    /**
     * Creates a new instance of ConteneurRequeteManagedBean
     */
    public ConteneurRequeteManagedBean() {

    }

    public List getSecondeListe() {
        return secondeListe;
    }

    public void setSecondeListe(List secondeListe) {
        this.secondeListe = secondeListe;
    }

    public Map<String, List> getMapListeEtat() {
        return mapListeEtat;
    }

    public void setMapListeEtat(Map<String, List> mapListeEtat) {
        this.mapListeEtat = mapListeEtat;
    }

    public byte[] getFicherPdf() {
        return ficherPdf;
    }

    public void setFicherPdf(byte[] ficherPdf) {
        this.ficherPdf = ficherPdf;
        if (ficherPdf != null) {
            ByteArrayInputStream tmp = new ByteArrayInputStream(ficherPdf);
            this.fluxPdf = tmp;
        }
    }

    public InputStream getFluxPdf() {
        return fluxPdf;
    }

    public void setFluxPdf(InputStream fluxPdf) {
        this.fluxPdf = fluxPdf;
    }

    public String getElementsDerniereRecherche() {
        return elementsDerniereRecherche;
    }

    public void setElementsDerniereRecherche(String elementsDerniereRecherche) {
        this.elementsDerniereRecherche = elementsDerniereRecherche;
    }

    public Boolean isCondense() {
        return (condense == null ? false : condense);
    }

    public void setCondense(Boolean condense) {
        this.condense = condense;
    }

    public List getListeEtatCondense() {
        return listeEtatCondense;
    }

    public void setListeEtatCondense(List listeEtatCondense) {
        this.listeEtatCondense = listeEtatCondense;
    }

    public Map<String, Object> getParametres() {
        return parametres;
    }

    public void setParametres(Map<String, Object> parametres) {
        this.parametres = parametres == null ? new HashMap() : parametres;

        Date date = new Date();
        //date = date == null ? new Date() : date;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        this.parametres.put("logo", context.getRealPath("/resources/image/logo.png"));
        this.parametres.put("DATE_EDITION", format.format(date));
        this.parametres.put("LIBELLE_AGENCE", "SAINTE RITA");
        this.parametres.put("LIBELLE_APPLICATION", "MS-CREDIT");
        this.parametres.put("BP_AGENCE", "BP 07123 COTONOU");
        this.parametres.put("TEL_AGENCE", "00229 96586243");
        this.parametres.put("ADRESSE_AGENCE", "En venant de la mairie de WOLOGUEDE, carrefour cyclone");
    }

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    public List getListeEtat() {
        return listeEtat;
    }

    public void setListeEtat(List listeEtat) {
        this.listeEtat = listeEtat;
    }

    public String getCompileFileName() {
        return compileFileName;
    }

    public void setCompileFileName(String compileFileName) {
        this.compileFileName = compileFileName;
    }

    public List<Map<String, Object>> getMapEtat() {
        return mapEtat;
    }

    public void setMapEtat(List<Map<String, Object>> mapEtat) {
        this.mapEtat = mapEtat;
    }

}
