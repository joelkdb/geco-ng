/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.codeart.geco.ng.utils;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


/**
 *
 * @author joelkdb
 */
public class Twilio {
   // Find your Account Sid and Token at Twilio.com/user/account
    public static final String ACCOUNT_SID = "AC60ea76491b938b2d694bb76fcf0fca0e";
    public static final String AUTH_TOKEN = "3df83f05c67d273cacbddadfba393b95";

    private String receverNum;
    private String message;
    private String senderId;
    //
    public String sendSms() {
        String messageEnreg = null;
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
            // Build the parameters 
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("To", receverNum));
            params.add(new BasicNameValuePair("From", senderId));
            params.add(new BasicNameValuePair("Body", message));
            params.add(new BasicNameValuePair("FriendlyName", senderId));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message1 = messageFactory.create(params);
            System.out.println(message1.getSid());
            messageEnreg = "SID: " + message1.getSid() + " Date envoi:" + message1.getDateSent();
            //
            return messageEnreg;

        } catch (TwilioRestException e) {
            e.printStackTrace();
            System.out.println(e.getErrorMessage() + ' ' + e.getErrorCode() + ' ' + e.getMoreInfo());
            // TwilioRestException(e.getErrorMessage(),e.getErrorCode(),e.getMoreInfo());
            return messageEnreg;
        }
    }

    public String getReceverNum() {
        return receverNum;
    }

    public void setReceverNum(String receverNum) {
        this.receverNum = receverNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
