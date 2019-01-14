/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.utils.EqualsFilterParams;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.entites.TypeEtablissement;
import tg.codeart.geco.ng.service.EtablissementServiceBeanRemote;
import tg.codeart.geco.ng.service.TypeEtablissementServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class EtablissementBean extends GenericBean<Etablissement, String> {

    @EJB
    private EtablissementServiceBeanRemote service;

    @EJB
    private TypeEtablissementServiceBeanRemote typeEtablissementService;

    private TypeEtablissement typeEtablissement;

    private List<Etablissement> etablissements = new ArrayList<>();

    private List<Etablissement> etablissements1 = new ArrayList<>();

    private String logoName;
    private boolean disableUpdate;
    private boolean disableAdd;
    private boolean columnRendered;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Etablissement();
        this.entity.setCode(this.service.generateCode());
        this.typeEtablissement = new TypeEtablissement();
        this.etablissements = this.service.getAll(EqualsFilterParams.from("actif", true));
        this.etablissements1 = this.service.getAll();
        this.typeEtablissement.setLibelle("Tous types");
        this.disableAdd = false;
        this.disableUpdate = true;
        this.columnRendered = true;
    }

    @Override
    public GenericServiceBeanRemote<Etablissement, String> getService() {
        return this.service;
    }

    @Override
    public void setEntity(Etablissement entity) {
        super.setEntity(entity);
        this.disableUpdate = false;
        this.disableAdd = true;
    }

    @Override
    public String delete(Etablissement as) {
        try {
            this.service.deleteOne(as);
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

    public void onSelectItems(SelectEvent event) {
        TypeEtablissement type = (TypeEtablissement) event.getObject();
        this.typeEtablissement = type;
        if (type != null) {
            this.columnRendered = false;
            this.etablissements1 = this.service.getAll(EqualsFilterParams.from("typeEtablissement", type));
        } else {
            this.columnRendered = true;
            this.typeEtablissement.setLibelle("Tous types");
            this.etablissements1 = this.service.getAll("code", false);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        byte[] data = event.getFile().getContents();
        this.logoName = event.getFile().getFileName();
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
            this.entity.setLogo(event.getFile().getFileName());
        } catch (IOException e) {
            e.printStackTrace();
            Messages.addFlashGlobalError("Erreur lors de l'écriture de l'image.");
        }
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
    public LazyDataModel<Etablissement> getModel() {
        return super.getModel();
    }

    public List<TypeEtablissement> getTypesEtablissement() {
        return this.typeEtablissementService.getAll();
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
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

    public TypeEtablissement getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(TypeEtablissement typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public boolean isColumnRendered() {
        return columnRendered;
    }

    public void setColumnRendered(boolean columnRendered) {
        this.columnRendered = columnRendered;
    }

    public List<Etablissement> getEtablissements1() {
        return etablissements1;
    }

    public void setEtablissements1(List<Etablissement> etablissements1) {
        this.etablissements1 = etablissements1;
    }

}
