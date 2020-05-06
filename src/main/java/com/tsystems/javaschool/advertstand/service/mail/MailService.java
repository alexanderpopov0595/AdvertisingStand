package com.tsystems.javaschool.advertstand.service.mail;

/**
 * Interface provides methods to work with mails
 */
public interface MailService {

    /**
     * Method sends html message to all subscribers
     * @param htmlMessage
     */
    public void sendEmail(String htmlMessage);
}
