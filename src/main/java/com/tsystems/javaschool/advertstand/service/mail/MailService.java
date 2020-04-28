package com.tsystems.javaschool.advertstand.service.mail;

import javax.ejb.Local;

/**
 * Interface provides methods to work with mails
 */
public interface MailService {

    public void sendEmail(String htmlMessage);
}
