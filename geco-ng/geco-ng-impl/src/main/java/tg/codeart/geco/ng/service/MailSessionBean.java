/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Joelkdb
 */
@Stateless
public class MailSessionBean implements IMailSessionBeanLocal {

    @Override
    public void envoyerEmailViaGmailSSL(String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setContent(message, "text/html");
            mailMessage.setSubject(sujet);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void envoyerEmailViaGmailSSLAvecFichier(String lien, String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setSubject(sujet);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = lien;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            mailMessage.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void envoyerEmailViaGmailTLS(String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setContent(message, "text/html");
            mailMessage.setSubject(sujet);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void envoyerEmailViaGmailTLSAvecFichier(String lien, String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setSubject(sujet);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = lien;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            mailMessage.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void envoyerEmail(String host, String port, String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.ssl.trust", host);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "10000");

            Session mailSession = Session.getInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setContent(message, "text/html");
            mailMessage.setSubject(sujet);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void envoyerEmailAvecFichier(String host, String port, String lien, String expediteurMail, String destinataireMail, String username, String password, String sujet, String message) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.ssl.trust", host);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "10000");

            Session mailSession = Session.getInstance(props, null);
            mailSession.setDebug(true);

            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(expediteurMail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireMail));
            mailMessage.setSubject(sujet);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = lien;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            mailMessage.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public Message[] checkEmails(String host, String port, String storeType, String username, String password) {
        try {
            Properties props = System.getProperties();

            props.put("mail.pop3.host", host);
            props.put("mail.pop3.port", port);
            props.put("mail.pop3.starttls.enable", "true");

            Session mailSession = Session.getDefaultInstance(props);
            Store store = mailSession.getStore(storeType);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            return messages;
        } catch (Exception ex) {
            Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

}
