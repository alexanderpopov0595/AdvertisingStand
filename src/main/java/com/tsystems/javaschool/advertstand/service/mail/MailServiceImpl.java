package com.tsystems.javaschool.advertstand.service.mail;

import com.tsystems.javaschool.advertstand.service.emaill.EmailService;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Stateless
public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    @EJB
    private EmailService emailService;

    private Properties properties;

    @PostConstruct
    public void setUp() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("email.properties");
        properties = new Properties();
        properties.load(inputStream);
    }

    @Asynchronous
    @Lock(LockType.READ)
    public void sendEmail(String htmlMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("email.from")));
            List<String> recipients = emailService.selectAllEmails();
            for (String recipient : recipients) {
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            }
            message.setSubject(properties.getProperty("email.subject"));
            message.setText(properties.getProperty("email.text"));
            message.setContent(htmlMessage, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            logger.debug("Error while sending emails: " + e.getStackTrace());
        }
    }
}