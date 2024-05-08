package com.batchdentilab.batchdentilab.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
@Component
public class EmailProperties {
    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    public Session initSession(){
        Session session = Session.getInstance(initProp(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tmp.test.oneshot@gmail.com", "avvydqonsdlvohko");
            }
        });

        return session;
    }
    private Properties initProp(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        return prop;
    }
}
