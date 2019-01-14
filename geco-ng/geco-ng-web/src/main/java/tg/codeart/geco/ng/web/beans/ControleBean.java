/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.utils.EqualsFilterParams;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Controle;
import tg.codeart.geco.ng.entites.Eleve;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.entites.Intervenir;
import tg.codeart.geco.ng.entites.IntervenirPK;
import tg.codeart.geco.ng.entites.Matiere;
import tg.codeart.geco.ng.entites.Professeur;
import tg.codeart.geco.ng.entites.Serie;
import tg.codeart.geco.ng.entites.TypeControle;
import tg.codeart.geco.ng.entites.TypeEtablissement;
import tg.codeart.geco.ng.service.ClasseServiceBeanRemote;
import tg.codeart.geco.ng.service.ControleServiceBeanRemote;
import tg.codeart.geco.ng.service.EleveServiceBeanRemote;
import tg.codeart.geco.ng.service.EtablissementServiceBeanRemote;
import tg.codeart.geco.ng.service.IntervenirServiceBeanRemote;
import tg.codeart.geco.ng.service.MatiereServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class ControleBean extends GenericBean<Controle, String> {

    @EJB
    private ControleServiceBeanRemote service;

    @EJB
    private EtablissementServiceBeanRemote etablissementService;

    @EJB
    private ClasseServiceBeanRemote classeService;

    @EJB
    private EleveServiceBeanRemote eleveService;

    @EJB
    private MatiereServiceBeanRemote matiereService;

    @EJB
    private IntervenirServiceBeanRemote intervenirService;

    private List<Etablissement> etablissements = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();
    private List<Classe> classes = new ArrayList<>();
    private List<Eleve> eleves = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    private List<Intervenir> intervenirs = new ArrayList<>();
    private Intervenir intervenir;
    private IntervenirPK intervenirPK;
    private Classe classe;
    private Eleve eleve;
    private Professeur professeur;
    private Matiere matiere;
    private String code;
    private String header;

    @Override
    public void init() {
        super.init();
        this.classe = new Classe();
        this.eleve = new Eleve();
        this.professeur = new Professeur();
        this.matiere = new Matiere();
        this.entity = new Controle();
        this.intervenirPK = new IntervenirPK();
        this.intervenir = new Intervenir();
        this.header = "Toutes les notes des élèves";
    }

    @Override
    public GenericServiceBeanRemote<Controle, String> getService() {
        return this.service;
    }

    @Override
    public boolean canAdd() {
        return true;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    public void initAdd() {

    }

    @Override
    public String add() {
        try {
            this.code = this.service.generateCode();
            this.entity.setCode(this.code);
            this.service.addOne(this.entity);
            this.addIntervenir();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('list').show();");
            //context.update("listAffectation");
            Messages.addFlashGlobalInfo("Veuillez affecter les notes des élèves !!!");
            this.intervenirs = this.intervenirService.getALlNull();
            this.init();
        } catch (Exception e) {
            Messages.addFlashGlobalError("Erreur survenue lors du processus.");
            //this.init();
        }
        return null;
    }

    public void addIntervenir() {
        this.eleves.stream().map((el) -> {
            this.intervenirPK.setCodeClasse(classe.getCode());
            return el;
        }).map((el) -> {
            this.intervenirPK.setCodeControle(this.code);
            return el;
        }).map((el) -> {
            this.intervenirPK.setCodeEleve(el.getCode());
            return el;
        }).map((el) -> {
            this.intervenirPK.setCodeMatiere(matiere.getCode());
            return el;
        }).map((el) -> {
            this.intervenirPK.setCodeProfesseur(professeur.getCode());
            return el;
        }).map((el) -> {
            this.intervenir.setIntervenirPK(intervenirPK);
            return el;
        }).map((el) -> {
            this.intervenir.setClasse(classe);
            return el;
        }).map((el) -> {
            this.intervenir.setControle(this.entity);
            return el;
        }).map((el) -> {
            this.intervenir.setEleve(el);
            return el;
        }).map((_item) -> {
            this.intervenir.setMatiere(matiere);
            return _item;
        }).map((_item) -> {
            this.intervenir.setProfesseur(professeur);
            return _item;
        }).map((_item) -> {
            this.intervenir.setEnvoye(false);
            return _item;
        }).forEach((_item) -> {
            this.intervenirService.addOne(intervenir);
        });
    }

    public void onTypeSelect(SelectEvent event) {
        TypeEtablissement type = (TypeEtablissement) event.getObject();
        if (type != null) {
            this.etablissements = this.etablissementService.getAll(EqualsFilterParams.from("typeEtablissement", type));
            this.etablissements = this.etablissements.stream().filter(et -> et.isActif()).collect(Collectors.toList());
        } else {
            this.etablissements = Collections.EMPTY_LIST;
        }
    }

    public void onEtablissementSelect(SelectEvent event) {
        Etablissement etab = (Etablissement) event.getObject();
        if (etab != null) {
            this.classes = this.classeService.getAll(EqualsFilterParams.from("etablissement", etab));
            this.eleves = this.eleveService.getStudentsFromSchool(etab.getCode());
            this.header = "Toutes les notes des élèves de l'établissement \"" + etab.getNom() + "\"";
        } else {
            this.classes = Collections.EMPTY_LIST;
            this.eleves = this.eleveService.getAll();
        }
    }

    public void onClasseSelect(SelectEvent event) {
        Classe cl = (Classe) event.getObject();
        if (cl != null) {
            this.eleves = this.eleveService.getAll(EqualsFilterParams.from("classe", cl));
            this.matieres = this.matiereService.getAll(EqualsFilterParams.from("serie", cl.getSerie()));
            this.header = this.header + " de \"" + cl.getLibelle() + "\"";
        }
    }

    public void onMatiereSelect(SelectEvent event) {
        Matiere mat = (Matiere) event.getObject();
        if (mat != null) {
            this.intervenirs = this.intervenirService.getAll(EqualsFilterParams.from("matiere", mat));
            this.header = this.header + " en \"" + mat.getLibelle() + "\"";
        }
    }

    public void onTypeControleSelect(SelectEvent event) {
        TypeControle typeCtrl = (TypeControle) event.getObject();
        if (typeCtrl != null) {
            this.intervenirs = this.intervenirService.getAll(EqualsFilterParams.from("controle.typeControle", typeCtrl));
            if (this.header.contains("-")) {
                StringBuilder sb = new StringBuilder();
                int a = this.header.indexOf("-");
                for (int i = 0; i < a; i++) {
                    sb.append(this.header.charAt(i));
                }
                sb.append("- \"").append(typeCtrl.getLibelle()).append("\"");
                this.header = sb.toString();
            } else {
                this.header = this.header + " - \"" + typeCtrl.getLibelle() + "\"";
            }
        }
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public Intervenir getIntervenir() {
        return intervenir;
    }

    public void setIntervenir(Intervenir intervenir) {
        this.intervenir = intervenir;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public IntervenirPK getIntervenirPK() {
        return intervenirPK;
    }

    public void setIntervenirPK(IntervenirPK intervenirPK) {
        this.intervenirPK = intervenirPK;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Intervenir> getIntervenirs() {
        return intervenirs;
    }

    public void setIntervenirs(List<Intervenir> intervenirs) {
        this.intervenirs = intervenirs;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
}
