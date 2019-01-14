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
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.TypeControle;
import tg.codeart.geco.ng.service.TypeControleServiceBeanRemote;

/**
 *
 * @author ibrah
 */
@ManagedBean
@ViewScoped
public class TypeControleBean extends GenericBean<TypeControle, Long> {

    @EJB
    private TypeControleServiceBeanRemote service;

    private boolean disableSave;
    private boolean disableEdit;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new TypeControle();
        this.disableSave = false;
        this.disableEdit = true;
    }

    public void setService(TypeControleServiceBeanRemote service) {
        this.service = service;
    }

    @Override
    public TypeControleServiceBeanRemote getService() {
        return service;
    }

    @Override
    public String delete(TypeControle tc) {
        try {
            this.service.deleteOne(tc);
            Messages.addFlashGlobalInfo("Type de contrôle supprimée avec succés !!!");

        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
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
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String add() {
        try {
            this.service.addOne(this.entity);
            this.init();
            Messages.addFlashGlobalInfo("Ajout effectué avec succés !!!");

        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
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
        this.entity = new TypeControle();
    }

    @Override
    public void setEntity(TypeControle entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<TypeControle> getModel() {
        return super.getModel();
    }
    
    public List<TypeControle> getTypeControles(){
        return this.service.getAll("libelle", true);
    }

    public boolean isDisableSave() {
        return disableSave;
    }

    public void setDisableSave(boolean disableSave) {
        this.disableSave = disableSave;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

}
