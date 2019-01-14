/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.utils.EqualsFilterParams;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Classe;
import tg.codeart.geco.ng.entites.Etablissement;
import tg.codeart.geco.ng.service.ClasseServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class ClasseBean extends GenericBean<Classe, String> {

    @EJB
    private ClasseServiceBeanRemote service;
    
    private Etablissement etablissement;
    
    private List<Classe> classes = new ArrayList<>();

    private String typeEtablissement;
    private boolean disableUpdate;
    private boolean disableAdd;
    private boolean columnRendered;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Classe();
        this.entity.setCode(this.service.generateCode());
        this.classes = this.service.getAll("code", false);
        this.disableAdd = false;
        this.disableUpdate = true;
        this.columnRendered = true;
        this.typeEtablissement = null;
        this.etablissement = new Etablissement();
        this.etablissement.setNom("Tout établissements");
    }

    @Override
    public GenericServiceBeanRemote<Classe, String> getService() {
        return this.service;
    }

    @Override
    public void setEntity(Classe entity) {
        super.setEntity(entity);
        this.disableUpdate = false;
        this.disableAdd = true;
    }

    @Override
    public String delete(Classe c) {
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

    @Override
    public LazyDataModel<Classe> getModel() {
        return super.getModel();
    }

    public void onSelectItems(SelectEvent event) {
        Etablissement etablissement = (Etablissement) event.getObject();
        this.typeEtablissement = etablissement.getTypeEtablissement().getLibelle();
    }
    
    public void onSelectEtablissement(SelectEvent event){
        Etablissement etablissement = (Etablissement) event.getObject();
        this.etablissement = etablissement;
        if (etablissement != null) {
            this.columnRendered = false;
            this.classes = this.service.getAll(EqualsFilterParams.from("etablissement", etablissement));
        } else {
            this.columnRendered = true;
            this.etablissement.setNom("Tout établissements");
            this.classes = this.service.getAll("code", false);
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

    public boolean isColumnRendered() {
        return columnRendered;
    }

    public void setColumnRendered(boolean columnRendered) {
        this.columnRendered = columnRendered;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
}
