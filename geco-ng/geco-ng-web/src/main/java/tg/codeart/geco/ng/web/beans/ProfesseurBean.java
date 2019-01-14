/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Professeur;
import tg.codeart.geco.ng.service.ProfesseurServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class ProfesseurBean extends GenericBean<Professeur, String>{

    @EJB
    private ProfesseurServiceBeanRemote service;
    
    private boolean disableUpdate;
    private boolean disableAdd;
    private Date now  = new Date();

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Professeur();
        this.entity.setCode(this.service.generateCode());
        this.entity.setIndicatif("228");
        this.disableAdd = false;
        this.disableUpdate = true;
    }
    
    @Override
    public GenericServiceBeanRemote<Professeur, String> getService() {
        return this.service;
    }
    
    @Override
    public void setEntity(Professeur entity) {
        super.setEntity(entity);
        this.disableUpdate = false;
        this.disableAdd = true;
    }

    @Override
    public String delete(Professeur p) {
        try {
            this.service.deleteOne(p);
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
            this.init();
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public LazyDataModel<Professeur> getModel() {
        return super.getModel();
    }
    
    public List<Professeur> getProfesseurs(){
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

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
    
}
