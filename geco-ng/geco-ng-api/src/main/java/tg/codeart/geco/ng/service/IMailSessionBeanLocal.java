/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import javax.ejb.Local;
import javax.mail.Message;

/**
 *
 * @author Joelkdb
 */
@Local
public interface IMailSessionBeanLocal {
    /**
     * Envoyer un mail via l'hôte smtp.gmail.com, port 465, format du message : text/html
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message et le message
     */
    public void envoyerEmailViaGmailSSL(String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
    
    /**
     * Envoyer un mail via l'hôte smtp.gmail.com, port 465, format du message : Mulitpart, avec le fichier dont le lien est passé paramètre
     * @param lien le lien du fichier
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message et le message
     */
    public void envoyerEmailViaGmailSSLAvecFichier(String lien,String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
            
    /**
     * Envoyer un mail via l'hôte smtp.gmail.com, port 587, format du message : text/html
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message  et le message
     */
    public void envoyerEmailViaGmailTLS(String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
    
    /**
     *Envoyer un mail via l'hôte smtp.gmail.com, port 587, format du message : Mulitpart, avec le fichier dont le lien est passé paramètre
     * @param lien le lien du fichier
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message et le message
     */
    public void envoyerEmailViaGmailTLSAvecFichier(String lien,String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
    
    /**
     * Envoyer un mail via l'hôte :'host' passé en paramètre, port :'port' passe en paramètre, format du message : text/html
     * @param host le nom du server ou de l'hôte
     * @param port le numero du port
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message  et le message
     */
    public void envoyerEmail(String host,String port,String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
    
    /**
     * Envoyer un mail via l'hôte :'host' passé en paramètre, port :'port' passe en paramètre, format du message : multipart
     * @param host le nom du server ou de l'hôte
     * @param port le numero du port
     * @param lien le lien du fichier
     * @param expediteurMail l'email de l'expediteur
     * @param destinataireMail l'email du destinataire
     * @param username le nom d'utilisateur du compte qu'on va utiliser pour envoyer le mail
     * @param password le mot de passe du compte
     * @param sujet le sujet de l'email
     * @param message  et le message
     */
    public void envoyerEmailAvecFichier(String host, String port, String lien, String expediteurMail, String destinataireMail, String username, String password, String sujet, String message);
    
    /**
     * Permet de renvoyer la totalite des messages se trouvant dans la boîte mail grace au protocole POP3
     * @param host  le nom du server ou de l'hôte  exemple :pop.gmail.com
     * @param port le numero du port
     * @param storeType le nom du type du store exemple :pop3s
     * @param username le nom d'utilisateur du compte 
     * @param password le mot de passe du compte
     * @return 
     */
    public Message[] checkEmails(String host, String port, String storeType, String username, String password);
}
