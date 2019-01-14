/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Matiere;
import tg.codeart.geco.ng.service.MatiereServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class MatiereBean extends GenericBean<Matiere, String>{
    
    @EJB
    private MatiereServiceBeanRemote service;
    
    private boolean disableUpdate;
    private boolean disableAdd;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Matiere();
        this.entity.setCode(this.service.generateCode());
        this.disableAdd = false;
        this.disableUpdate = true;
    }

    @Override
    public GenericServiceBeanRemote<Matiere, String> getService() {
        return this.service;
    }
    
    @Override
    public void setEntity(Matiere entity) {
        super.setEntity(entity);
        this.disableUpdate = false;
        this.disableAdd = true;
    }

    @Override
    public String delete(Matiere m) {
        try {
            this.service.deleteOne(m);
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
    public LazyDataModel<Matiere> getModel() {
        return super.getModel();
    }
    
    public List<Matiere> getMatieres(){
        return this.service.getAll();
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
    
}
