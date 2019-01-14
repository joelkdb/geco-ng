/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.utils.EqualsFilterParams;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.AnneeScolaire;
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Eleve;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.entites.Matiere;
import tg.codeart.geco.ng.entites.Professeur;
import tg.codeart.geco.ng.entites.TableauBulletin;
import tg.codeart.geco.ng.entites.TypeEtablissement;
import tg.codeart.geco.ng.service.BulletinServiceBeanRemote;
import tg.codeart.geco.ng.service.ClasseServiceBeanRemote;
import tg.codeart.geco.ng.service.EleveServiceBeanRemote;
import tg.codeart.geco.ng.service.EtablissementServiceBeanRemote;
import tg.codeart.geco.ng.service.IMailSessionBeanLocal;
import tg.codeart.geco.ng.service.MatiereServiceBeanRemote;
import tg.codeart.geco.ng.service.ProfesseurServiceBeanRemote;
import tg.codeart.geco.ng.web.reports.ConteneurRequeteManagedBean;
import tg.codeart.geco.ng.web.reports.ExportManagedBean;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class EleveBean extends GenericBean<Eleve, String> {

    @EJB
    private EleveServiceBeanRemote service;

    @EJB
    private ClasseServiceBeanRemote classeService;

    @EJB
    private EtablissementServiceBeanRemote etablissementService;

    @EJB
    private MatiereServiceBeanRemote matiereService;

    @EJB
    private BulletinServiceBeanRemote bulletinService;

    @EJB
    private ProfesseurServiceBeanRemote profService;

    @ManagedProperty(value = "#{conteneurRequeteManagedBean}")
    private ConteneurRequeteManagedBean conteneur;

    @ManagedProperty(value = "#{globalExportManagedBean}")
    private ExportManagedBean exportManagedBean;

    @EJB
    private IMailSessionBeanLocal mailService;

    private List<Etablissement> etablissements = new ArrayList<>();
    private List<Classe> classes = new ArrayList<>();
    private List<Eleve> eleves = new ArrayList<>();
    private List<Eleve> eleves2 = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    private List<Professeur> professeurs = new ArrayList<>();
    private List<Bulletin> bulletins = new ArrayList<>();
    private List<TableauBulletin> ligneTableauBull = new ArrayList<>();
    private AnneeScolaire anneeScolaire = new AnneeScolaire();
    private Etablissement etablissement;
    private Eleve eleve = new Eleve();
    private Bulletin bulletin;
    private BulletinPK bulletinPK;
    private String periode;
    private boolean disableUpdate;
    private boolean disableAdd;
    private boolean required = false;
    private String headerLabel;
    private boolean rendered;
    private boolean renderedBtn1;
    private boolean renderedBtn2;
    private String photo;
    private String nomEleve;
    private String prenomEleve;
    private Date dateNaissance;
    private String lieuNaissance;
    private String nationalite;
    private String matricule;
    private String telephone;
    private String sexe;
    private String classe;
    private boolean rendered1;
    private boolean readonly;
    private String uploadFileName;
    private String subject;
    private String message;
    private String senderMail;
    private String motDePasse;
    private String userName;
    private Integer absence;
    private BigDecimal sommeNoteDef = BigDecimal.ZERO;
    private Long sommeCoef;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Eleve();
        this.entity.setAbonne(false);
        this.entity.setCode(this.service.generateCode());
        this.eleves2 = this.service.getAll(EqualsFilterParams.from("abonne", true));
        this.professeurs = this.profService.getAll();
        this.etablissement = new Etablissement();
        this.bulletin = new Bulletin();
        this.bulletinPK = new BulletinPK();
        this.disableAdd = false;
        this.disableUpdate = true;
        this.readonly = false;
        this.headerLabel = "Tous les élèves dont les parents sont abonnés";
        this.entity.setIndicatif1("228");
        this.entity.setIndicatif2("228");
        this.rendered = true;
        this.renderedBtn1 = true;
        this.renderedBtn2 = false;
        this.rendered1 = false;
    }

    @Override
    public GenericServiceBeanRemote<Eleve, String> getService() {
        return this.service;
    }

    @Override
    public void setEntity(Eleve entity) {
        super.setEntity(entity);
        this.disableUpdate = false;
        this.disableAdd = true;
    }

    public String send() {
        try {
            System.out.println("R1");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('emailDlg').show();");
            System.out.println("R2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String delete(Eleve c) {
        try {
            this.service.deleteOne(c);
            Messages.addFlashGlobalInfo("Suppression effectuée avec succés !!!");
            this.init();
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
            this.init();
        }
        return null;
    }

    @Override
    public String update() {
        try {
            this.service.update(this.entity);
            Messages.addFlashGlobalInfo("Modification effectué avec succés !!!");
            this.init();
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            this.init();
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String add() {
        try {
            this.service.addOne(this.entity);
            Messages.addFlashGlobalInfo("Ajout effectué avec succés !!!");
            this.init();
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    public void addBulletin() {
        if (this.professeurs.isEmpty()) {
            Messages.addFlashGlobalWarn("Veuillez enregistrer au moins un professeur !!!");
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            try {
                System.out.println("Somme des coef : " + this.sommeCoef);
                this.matieres.stream().map((mat) -> {
                    this.bulletinPK.setCodeMatiere(mat.getCode());
                    return mat;
                }).map((mat) -> {
                    this.bulletinPK.setCodeProfesseur(this.professeurs.get(0).getCode());
                    return mat;
                }).map((mat) -> {
                    this.bulletin.setBulletinPK(bulletinPK);
                    return mat;
                }).map((mat) -> {
                    this.bulletin.setMatiere(mat);
                    return mat;
                }).map((_item) -> {
                    this.bulletin.setProfesseur(this.professeurs.get(0));
                    return _item;
                }).forEach((_item) -> {
                    this.bulletinService.addOne(bulletin);
                });
                this.bulletins = this.bulletinService.getAll();
                context.execute("PF('bulletinDlg').show();");
                this.init();
            } catch (Exception e) {
                e.printStackTrace();
                Messages.addFlashGlobalError("Erreur survenue lors du processus !");
            }
        }
    }

    @Override
    public LazyDataModel<Eleve> getModel() {
        return super.getModel();
    }

    public void onSelectEtablissement(SelectEvent event) {
        Etablissement etab = (Etablissement) event.getObject();
        this.classes = this.classeService.getAll(EqualsFilterParams.from("etablissement", etab));
    }

    public void onTypeSelect(SelectEvent event) {
        TypeEtablissement type = (TypeEtablissement) event.getObject();
        if (type != null) {
            this.etablissements = this.etablissementService.getAll(EqualsFilterParams.from("typeEtablissement", type));
            this.etablissements = this.etablissements.stream().filter(et -> et.isActif()).collect(Collectors.toList());
        } else {
            this.etablissements = Collections.EMPTY_LIST;
            //this.eleves = this.service.getAll();
        }
    }

    public void onEtablissementSelect(SelectEvent event) {
        Etablissement etab = (Etablissement) event.getObject();
        if (etab != null) {
            this.headerLabel = "Tous les élèves de l'établissement  \"" + etab.getNom() + "\"";
            this.classes = this.classeService.getAll(EqualsFilterParams.from("etablissement", etab));
            this.eleves = this.service.getStudentsFromSchool(etab.getCode());
        } else {
            this.classes = Collections.EMPTY_LIST;
            //this.eleves = this.service.getAll();
        }
    }

    public void onClasseSelect(SelectEvent event) {
        Classe cl = (Classe) event.getObject();
        if (cl != null) {
            this.headerLabel = this.headerLabel + " de la classe \"" + cl.getLibelle() + "\"";
            this.matieres = this.matiereService.getAll(EqualsFilterParams.from("serie", cl.getSerie()));
            this.eleves = this.service.getAll(EqualsFilterParams.from("classe", cl));
        }
    }

    public void fillBulletinTable() {
        System.out.println("Rentrer dans le remplissage!");
        BigDecimal noteDefTmp  = BigDecimal.ZERO;
        BigDecimal sumNoteDefTmp = BigDecimal.ZERO;
        Double sum = 0.0;
        this.bulletins = this.bulletinService.getAll("matiere.typeMatiere.id", true);
        this.ligneTableauBull = new ArrayList();
        for (Bulletin bul : this.bulletins) {
            TableauBulletin tb = new TableauBulletin();
            if (bul.getMatiere().getTypeMatiere().getLibelle().toUpperCase().equals("FACULTATIVE")) {
                tb.setMatiere(bul.getMatiere().getLibelle() + " **");
            } else {
                tb.setMatiere(bul.getMatiere().getLibelle());
            }
            tb.setProfesseur(bul.getProfesseur().getNom() + bul.getProfesseur().getPrenom());
            tb.setMoyClass(bul.getMoyenneClasse());
            tb.setNoteDevoir(bul.getNoteDevoir());
            tb.setNoteCompo(bul.getNoteCompo());
            tb.setCoef(bul.getMatiere().getCoefficient());
            noteDefTmp = bul.getMoyenneClasse().add(bul.getNoteDevoir()).
                    divide(BigDecimal.valueOf(2)).add(bul.getNoteCompo()).divide(BigDecimal.valueOf(2));
            tb.setAppreciation(this.renvoiAppreciation(noteDefTmp));
            if(bul.getMatiere().getCoefficient()!=null){
                tb.setNoteDef(noteDefTmp.multiply(BigDecimal.valueOf(bul.getMatiere().getCoefficient())));
                sum += noteDefTmp.multiply(BigDecimal.valueOf(bul.getMatiere().getCoefficient())).doubleValue();
                //sumNoteDefTmp.add(noteDefTmp.multiply(BigDecimal.valueOf(bul.getMatiere().getCoefficient())));
                System.out.println("Somme note Def. Temp :" + sum);
            }else{
                tb.setNoteDef(noteDefTmp.subtract(BigDecimal.TEN));
                sum += noteDefTmp.subtract(BigDecimal.TEN).doubleValue();
                //sumNoteDefTmp.add(noteDefTmp.subtract(BigDecimal.TEN));
                System.out.println("Somme note Def. Temp" + sum);
            }
            tb.setSignature("");
            System.out.println("Matière :"+bul.getMatiere().getLibelle()+ "(" + bul.getMatiere().getCoefficient() + ")");
            this.ligneTableauBull.add(tb);
        }
        this.sommeNoteDef = BigDecimal.valueOf(sum);
        this.sommeCoef = this.bulletinService.getCoefSum();
        System.out.println("Somme coef. : " + this.sommeCoef);
        System.out.println("Somme not Def. : " + this.sommeNoteDef);
        //this.ligneTableauBull = this.ligneTableauBull.stream().sorted().collect(Collectors.toList());
        System.out.println("Sortie du remplissage!");
    }

    public String renvoiAppreciation(BigDecimal val) {
        if (Double.valueOf(val.toString()) >= 0 && Double.valueOf(val.toString()) < 5) {
            return "Médiocre";
        } else if (Double.valueOf(val.toString()) >= 5 && Double.valueOf(val.toString()) < 7) {
            return "Faible";
        } else if (Double.valueOf(val.toString()) >= 7 && Double.valueOf(val.toString()) < 8) {
            return "Très insuffisant";
        } else if (Double.valueOf(val.toString()) >= 8 && Double.valueOf(val.toString()) < 10) {
            return "Insuffisant";
        } else if (Double.valueOf(val.toString()) >= 10 && Double.valueOf(val.toString()) < 12) {
            return "Passable";
        } else if (Double.valueOf(val.toString()) >= 12 && Double.valueOf(val.toString()) < 14) {
            return "Assez bien";
        } else if (Double.valueOf(val.toString()) >= 14 && Double.valueOf(val.toString()) < 16) {
            return "Bien";
        } else if (Double.valueOf(val.toString()) >= 16 && Double.valueOf(val.toString()) < 18.5) {
            return "Très bien";
        } else if (Double.valueOf(val.toString()) >= 18.5 && Double.valueOf(val.toString()) < 20) {
            return "Excellent";
        }
        return null;
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

    public void onChange() {
        this.required = this.required != true;
    }

    public void onChangeForCarte() {
        this.rendered = this.rendered != true;
        this.rendered1 = this.rendered1 == false;
        this.renderedBtn1 = this.renderedBtn1 != true;
        this.renderedBtn2 = this.renderedBtn2 != true;
    }

    public void onEleveSelect(SelectEvent event) {
        Eleve el = (Eleve) event.getObject();
        this.readonly = this.readonly == false;
        this.setEntity(el);
        this.classe = el.getClasse().getLibelle();
    }

    public void handleFileUpload(FileUploadEvent event) {
        byte[] data = event.getFile().getContents();
        this.uploadFileName = event.getFile().getFileName();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        try {
            String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                    + File.separator + "images" + File.separator + event.getFile().getFileName();

            FileImageOutputStream imageOutput;
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            Messages.addFlashGlobalInfo("Image charger avec succès!");
            this.photo = context.getRealPath("/resources/images/" + event.getFile().getFileName());
            System.out.println("Url photo : " + this.photo);
        } catch (IOException e) {
            e.printStackTrace();
            Messages.addFlashGlobalError("Erreur lors de l'écriture de l'image.");
        }
    }

    public void handleFileUpload2(FileUploadEvent event) {
        byte[] data = event.getFile().getContents();
        this.uploadFileName = event.getFile().getFileName();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        try {
            String newFileName = externalContext.getRealPath("") + File.separator + "resources"
                    + File.separator + "files" + File.separator + event.getFile().getFileName();

            FileOutputStream output;
            output = new FileOutputStream(new File(newFileName));
            output.write(data, 0, data.length);
            output.close();
            Messages.addFlashGlobalInfo("Fichier charger avec succès!");
            this.photo = context.getRealPath("/resources/files/" + event.getFile().getFileName());
            System.out.println("Url fichier : " + this.photo);
        } catch (IOException e) {
            e.printStackTrace();
            Messages.addFlashGlobalError("Erreur lors de l'écriture du fichier.");
        }
    }

    public void exporterCarte1() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //On passe les paramètres
        Map<String, Object> param = new HashMap();
        param.put("nomEtablissement", this.etablissement.getNom().toUpperCase());
        param.put("bpEtab", this.etablissement.getBp());
        param.put("telEtab", etablissement.getTelephone());
        param.put("nomPrenom", this.nomEleve + " " + this.prenomEleve);
        param.put("dateNaiss", format.format(this.dateNaissance));
        param.put("lieu", this.lieuNaissance);
        param.put("sexe", this.sexe);
        param.put("classe", this.classe);
        param.put("nationalite", this.nationalite.toUpperCase());
        param.put("matri", this.matricule);
        param.put("telElev", this.telephone);
        param.put("annee", this.anneeScolaire.getDateDebut() + "-" + this.anneeScolaire.getDateFin());
        param.put("image", this.photo);

        this.conteneur.setCompileFileName("carte");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
        this.conteneur.setParametres(param);
        exportManagedBean.setFormatDocument("PDF");
        exportManagedBean.exporter();
        this.renitChampsCarte();
    }

    public void exporterCarte2() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //On passe les paramètres
        Map<String, Object> param = new HashMap();
        param.put("nomEtablissement", this.entity.getClasse().getEtablissement().getNom().toUpperCase());
        param.put("bpEtab", "3232");
        param.put("telEtab", this.entity.getClasse().getEtablissement().getTelephone());
        param.put("nomPrenom", this.entity.getNom() + " " + this.entity.getPrenom());
        param.put("dateNaiss", format.format(this.entity.getDateNaissance()));
        param.put("lieu", this.lieuNaissance);
        param.put("sexe", String.valueOf(this.entity.getSexe()));
        param.put("classe", this.entity.getClasse().getLibelle());
        param.put("nationalite", this.entity.getNationalite().toUpperCase());
        param.put("matri", this.matricule);
        param.put("telElev", this.telephone);
        param.put("annee", this.anneeScolaire.getDateDebut() + "-" + this.anneeScolaire.getDateFin());
        param.put("image", this.photo);

        this.conteneur.setCompileFileName("carte");
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
        this.conteneur.setParametres(param);
        exportManagedBean.setFormatDocument("PDF");
        exportManagedBean.exporter();
        this.renitChampsCarte();
    }

    public void exporterBulletin() {
        System.out.println("Entrer dans l'export du bulletin");
        this.fillBulletinTable();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(this.sommeNoteDef.divide(BigDecimal.valueOf(this.sommeCoef)).toString().charAt(i));
        }
        //numberFormat.
        String[] periodes = this.periode.split(" ");
        Map<String, Object> param = new HashMap();
        //On passe les paramètres au report
        param.put("nomEtablissement", this.eleve.getClasse().getEtablissement().getNom());
        param.put("eleve", this.eleve.getNom() + " " + this.eleve.getPrenom());
        param.put("bp", this.eleve.getClasse().getEtablissement().getBp());
        param.put("tel", this.eleve.getClasse().getEtablissement().getTelephone());
        param.put("annee", this.anneeScolaire.getDateDebut() + "-" + this.anneeScolaire.getDateFin());
        param.put("classe", this.eleve.getClasse().getLibelle());
        param.put("effectif", String.valueOf(this.eleves.size()));
        param.put("sexe", String.valueOf(this.eleve.getSexe()));
        param.put("periode", this.periode);
        param.put("periode2", periodes[0]);
        param.put("periode3", periodes[1]);
        param.put("moyGen", "");
        param.put("moyExtrem", "");
        param.put("travail", this.renvoiAppreciation(this.sommeNoteDef.divide(BigDecimal.valueOf(this.sommeCoef))));
        param.put("rang", "");
        param.put("moyObt", sb.toString());
        param.put("absence", String.valueOf(this.absence));

        this.conteneur.setCompileFileName("bulletin");
        this.conteneur.setListeEtat(ligneTableauBull);
        this.conteneur.setCondense(false);
        this.conteneur.setMapEtat(null);
        this.conteneur.setParametres(param);
        exportManagedBean.setFormatDocument("PDF");
        exportManagedBean.exporter();
        this.renitChampsBull();
        System.out.println("Sorti de l'export du bulletin");
        this.bulletinService.deleteAll();
    }

    public void renitChampsBull() {
        this.eleve = new Eleve();
        this.anneeScolaire = new AnneeScolaire();
        this.periode = new String();
        this.absence = 0;
    }

    public void renitChampsCarte() {
        this.anneeScolaire = new AnneeScolaire();
        this.entity = new Eleve();
        this.etablissement = new Etablissement();
        this.nomEleve = new String();
        this.prenomEleve = new String();
        this.dateNaissance = null;
        this.lieuNaissance = new String();
        this.sexe = new String();
        this.classe = new String();
        this.matricule = new String();
        this.telephone = new String();
        this.uploadFileName = new String();
    }

    public void sendMail() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            this.mailService.envoyerEmailViaGmailTLSAvecFichier(this.photo, this.senderMail, this.eleve.getEmailParent(),
                    this.userName, this.motDePasse, this.subject, this.message);
            context.execute("PF('emailDlg').hide();");
            Messages.addFlashGlobalInfo("Bulletin envoyé avec succès !!!");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addFlashGlobalError("Erreur survenue lors du processus.");
        }
    }

    public void listeBulletin() {

    }

    public List<Eleve> getEleves1() {
        return this.service.getAll();
    }

    public boolean isDisableUpdate() {
        return disableUpdate;
    }

    public void setDisableUpdate(boolean disableUpdate) {
        this.disableUpdate = disableUpdate;
    }

    public boolean isDisableAdd() {
        return disableAdd;
    }

    public void setDisableAdd(boolean disableAdd) {
        this.disableAdd = disableAdd;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public String getHeaderLabel() {
        return headerLabel;
    }

    public void setHeaderLabel(String headerLabel) {
        this.headerLabel = headerLabel;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public boolean isRenderedBtn1() {
        return renderedBtn1;
    }

    public void setRenderedBtn1(boolean renderedBtn1) {
        this.renderedBtn1 = renderedBtn1;
    }

    public boolean isRenderedBtn2() {
        return renderedBtn2;
    }

    public void setRenderedBtn2(boolean renderedBtn2) {
        this.renderedBtn2 = renderedBtn2;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public AnneeScolaire getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getPrenomEleve() {
        return prenomEleve;
    }

    public void setPrenomEleve(String prenomEleve) {
        this.prenomEleve = prenomEleve;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public ConteneurRequeteManagedBean getConteneur() {
        return conteneur;
    }

    public void setConteneur(ConteneurRequeteManagedBean conteneur) {
        this.conteneur = conteneur;
    }

    public ExportManagedBean getExportManagedBean() {
        return exportManagedBean;
    }

    public void setExportManagedBean(ExportManagedBean exportManagedBean) {
        this.exportManagedBean = exportManagedBean;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public boolean isRendered1() {
        return rendered1;
    }

    public void setRendered1(boolean rendered1) {
        this.rendered1 = rendered1;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Eleve> getEleves2() {
        return eleves2;
    }

    public List<TableauBulletin> getLigneTableauBull() {
        return ligneTableauBull;
    }

    public void setLigneTableauBull(List<TableauBulletin> ligneTableauBull) {
        this.ligneTableauBull = ligneTableauBull;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public BulletinPK getBulletinPK() {
        return bulletinPK;
    }

    public void setBulletinPK(BulletinPK bulletinPK) {
        this.bulletinPK = bulletinPK;
    }

    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

}
