package com.tsystems.javaschool.advertstand.service.mail;

import com.sun.mail.iap.Protocol;
import com.tsystems.javaschool.advertstand.service.emaill.EmailService;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Stateless
public class MailServiceImpl implements MailService{


    private static final Logger logger= Logger.getLogger(MailServiceImpl.class);

    /*

   @Inject
    private Session mailSession;
     */


    @EJB
    private EmailService emailService;

    public MailServiceImpl(){

    }

    @Asynchronous
    @Lock(LockType.READ)
    public void sendEmail(String htmlMessage ){

        System.out.println("HERE");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("shikikagami@gmail.com","shikikagami@");
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("shikikagami@gmail.com"));

           // message.setRecipients(Message.RecipientType.TO,  InternetAddress.parse("shikikagami@gmail.com"));
          //  message.setRecipients(Message.RecipientType.TO,   InternetAddress.parse(recipients));
            List<String> recipients=emailService.selectAllEmails();
            logger.info("List: "+ recipients);
            for(String recipient: recipients) {
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            }
            logger.info("Addresses");
            for(Address a: message.getAllRecipients()){
                logger.info(a);
            }

           // message.setRecipients(Message.RecipientType.TO, new InternetAddress(r));
            message.setSubject("Testing Subject");
            message.setText("Test Mail");
           // message.setContent("<h1>Hello</h1>", "text/html");
            message.setContent(htmlMessage, "text/html");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}