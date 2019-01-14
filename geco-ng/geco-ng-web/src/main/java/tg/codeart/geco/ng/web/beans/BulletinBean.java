/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.mediasoft.commons.core.service.GenericServiceBeanRemote;
import net.mediasoft.commons.core.web.beans.GenericBean;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import tg.codeart.geco.ng.entites.Bulletin;
import tg.codeart.geco.ng.entites.BulletinPK;
import tg.codeart.geco.ng.service.BulletinServiceBeanRemote;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class BulletinBean extends GenericBean<Bulletin, BulletinPK> {

    @EJB
    private BulletinServiceBeanRemote service;

    private boolean disabled = true;

    @Override
    public void init() {
        super.init();
        this.entity = new Bulletin();
    }

    @Override
    public GenericServiceBeanRemote<Bulletin, BulletinPK> getService() {
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
    public LazyDataModel<Bulletin> getModel() {
        return super.getModel();
    }

    public void onRowEdit(RowEditEvent event) {
        this.setEntity((Bulletin) event.getObject());
        this.service.updateOne(this.entity);
        this.disabled = !this.service.getAllNul().isEmpty();
    }
     

public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
