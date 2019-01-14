/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.web.beans;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import net.mediasoft.commons.core.service.UserServiceBeanRemote;
import net.mediasoft.commons.core.utils.CoreConstants;
import net.mediasoft.commons.core.web.beans.BaseApplicationBean;
import tg.codeart.geco.ng.utils.GecoConstants;

/**
 *
 * @author komilo
 */
@ManagedBean
@ApplicationScoped
public class ApplicationBean extends BaseApplicationBean {

    @EJB
    private UserServiceBeanRemote userService;

    public static final String MENU_FILE_PATH = "/geco/templates/menu.xhtml";
    public static final String SECONDARY_MENU_FILE_PATH = "/geco/templates/secondary-menu.xhtml";

    @Override
    public String getApplicationName() {
        return "GeCo+";
    }

    @Override
    public String getApplicationDisplayName() {
        return "GeCo+";
    }

    @Override
    public String getApplicationSlogan() {
        return "CodeArt geco+";
    }

    @Override
    public String getMenuFilePath() {
        return MENU_FILE_PATH;
    }

    @Override
    public String getSecondaryMenuFilePath() {
        return SECONDARY_MENU_FILE_PATH;
    }

    public boolean canAccessAdmin() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ALL);
    }
    
    public boolean canAccessAllMetier() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_ALL);
    }

    public boolean canAccessEtablissement() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_ETABLISSEMENT_ALL);
    }

    public boolean canAccessClasse() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_CLASSE_ALL);
    }
    
    public boolean canAccessEleve() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_ELEVE_ALL);
    }
    
    public boolean canAccessEleveCarte() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_ELEVE_CARTE);
    }
    
    public boolean canAccessEleveBull() {
        return this.userService.isPermitted(GecoConstants.PERM_METIER_ELEVE_BULLETIN);
    }
    
    public boolean canAccessNote() {
        return this.userService.isPermitted(GecoConstants.PERM_AFFECTATION_ALL);
    }
    
    public boolean canAddNote() {
        return this.userService.isPermitted(GecoConstants.PERM_AFFECTATION_NOTE_ADD);
    }
    
    public boolean canEditNote() {
        return this.userService.isPermitted(GecoConstants.PERM_AFFECTATION_NOTE_EDIT);
    }
    
    public boolean canDeleteNote(){
        return this.userService.isPermitted(GecoConstants.PERM_AFFECTATION_NOTE_DELETE);
    }
    
    public boolean canAccessParametrage() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_ALL);
    }
    
    public boolean canAccessParamAnnee() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_ANNEE_ALL);
    }
    
    public boolean canAccessParamPeriode() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_PERIODE_ALL);
    }
    
    public boolean canAccessParamTypeMatiere() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_MATIERE_ALL);
    }
    
    public boolean canAccessParamSerie() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_SERIE_ALL);
    }
    
    public boolean canAccessParamControle() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_CONTROLE_ALL);
    }
    
    public boolean canAccessParamEtablissement() {
        return this.userService.isPermitted(GecoConstants.PERM_PARAMETRE_ETABLISSEMENT_ALL);
    }

}
