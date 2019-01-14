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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.service.LogServiceBeanRemote;
import net.mediasoft.commons.core.utils.EqualsFilterParams;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.entites.Intervenir;
import tg.codeart.geco.ng.entites.IntervenirPK;
import tg.codeart.geco.ng.entites.TypeEtablissement;
import tg.codeart.geco.ng.service.ClasseServiceBeanRemote;
import tg.codeart.geco.ng.service.EtablissementServiceBeanRemote;
import tg.codeart.geco.ng.service.IMailSessionBeanLocal;
import tg.codeart.geco.ng.service.IntervenirServiceBeanRemote;
import tg.codeart.geco.ng.utils.GecoConstants;
import tg.codeart.geco.ng.utils.Twilio;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class IntervenirBean extends GenericBean<Intervenir, IntervenirPK> {

    @EJB
    private IntervenirServiceBeanRemote service;

    @EJB
    private LogServiceBeanRemote logService;

    @EJB
    private EtablissementServiceBeanRemote etablissementService;

    @EJB
    private ClasseServiceBeanRemote classeService;

    @EJB
    private IMailSessionBeanLocal mailService;

    private Twilio twilio = new Twilio();

    private List<Intervenir> intervenirs = new ArrayList<>();
    private List<Etablissement> etablissements = new ArrayList<>();
    private List<Classe> classes = new ArrayList<>();
    private String header;
    private String senderEmail;
    private String password;
    private String username;
    public static final String SUBJECT = "Communication des notes";
    private boolean disabled = true;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Intervenir();
        this.header = "Toutes les notes des élèves de tout établissements abonnés";
    }

    @Override
    public GenericServiceBeanRemote<Intervenir, IntervenirPK> getService() {
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
    public LazyDataModel<Intervenir> getModel() {
        return super.getModel();
    }

    @Override
    public String update() {
        try {
            this.service.updateOne(this.entity);
            this.logService.info("Modification de l'affectation de note.", GecoConstants.LOG_METIER);
            Messages.addFlashGlobalInfo("Modification effectuée avec succès !!!");
            this.init();
        } catch (Exception e) {
            Messages.addFlashGlobalError("Erreur survenue lors du processus de modification.");
            this.init();
        }
        return null;
    }

    @Override
    public String delete(Intervenir i) {
        try {
            this.service.deleteOne(i);
            this.logService.info("Suppression d'une affectation de note.", GecoConstants.LOG_METIER);
        } catch (Exception e) {
            Messages.addFlashGlobalError("Erreur survenue lors du processus de suppression.");
            this.init();
        }
        return null;
    }

    public void sendMail() {
        String emailParent = this.entity.getEleve().getEmailParent();
        System.out.println(emailParent);
        if (this.entity.getEleve().isAbonne()) {
            this.twilio.setReceverNum(this.entity.getEleve().getIndicatif1() + this.entity.getEleve().getPortableParent1());
            this.twilio.setSenderId(this.entity.getEleve().getClasse().getEtablissement().getNom());
            String message = "Bonjour cher parent,\n"
                    + "Élève : " + this.entity.getEleve().getNom() + this.entity.getEleve().getPrenom() + ",\n"
                    + "Matière : " + this.entity.getMatiere().getLibelle() + ",\n"
                    + "Contrôle effectué : " + this.entity.getControle().getLibelle() + ",\n"
                    + "Date du contôle : " + this.entity.getControle().getDateRealisation() + ",\n"
                    + "Note obtenue  : " + this.entity.getNote() + "/20\n"
                    + "Professeur : " + this.entity.getProfesseur().getNom() + this.entity.getProfesseur().getPrenom() + ",\n"
                    + "De la part de l'établissement : " + this.entity.getClasse().getEtablissement().getNom() + ",\n"
                    + "Merci, bonne journée!";
            this.twilio.setMessage(message);
            try {
                this.twilio.sendSms();
                Messages.addFlashGlobalInfo("Sms envoyé!");
                System.out.println("MESSAGE ENVOYÉ");
                this.mailService.envoyerEmailViaGmailTLS(this.senderEmail, emailParent, this.username, this.password,
                        SUBJECT, message);
                this.entity.setEnvoye(true);
                this.update();
                Messages.addFlashGlobalInfo("Email envoyé avec succès!");
                this.init();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("MESSAGE NON ENVOYÉ");
                Messages.addFlashGlobalError("Erreur survenue lors de l'envoie de l'email, ressayer ultérieurement");
                this.init();
            }
        } else {
            Messages.addFlashGlobalWarn("Email ne peut pas être envoyé car parent ou tuteur non abonné!");
        }

    }
    
    public void onRowEdit2(RowEditEvent event) {
        this.setEntity((Intervenir) event.getObject());
        this.service.updateOne(this.entity);
        this.disabled = !this.service.getALlNull().isEmpty();
    }

    public void onRowEdit(RowEditEvent event) {
        this.setEntity((Intervenir) event.getObject());
        this.update();
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
            this.header = "Tous les élèves de l'établissement  \"" + etab.getNom() + "\"";
            this.classes = this.classeService.getAll(EqualsFilterParams.from("etablissement", etab));
            this.intervenirs = this.service.getAll(EqualsFilterParams.from("classe.etablissement", etab));
        } else {
            this.classes = Collections.EMPTY_LIST;
            this.intervenirs = this.service.getAll();
        }
    }

    public void onClasseSelect(SelectEvent event) {
        Classe classe = (Classe) event.getObject();
        if (classe != null) {
            this.header = this.header + " de la classe \"" + classe.getLibelle() + "\"";
            this.intervenirs = this.service.getAll(EqualsFilterParams.from("classe", classe));
        }
    }

    public List<Intervenir> getIntervenirs() {
        return intervenirs;
    }

    public void setIntervenirs(List<Intervenir> intervenirs) {
        this.intervenirs = intervenirs;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
