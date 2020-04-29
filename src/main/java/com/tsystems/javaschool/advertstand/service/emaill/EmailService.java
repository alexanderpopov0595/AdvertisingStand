package com.tsystems.javaschool.advertstand.service.emaill;

import com.tsystems.javaschool.advertstand.domain.Email;
import java.util.List;

/**
 * Interface provides methods to work with emails
 */
public interface EmailService {

    /**
     * Method adds email
     * @param email
     */
    public void addEmail(Email email);

    /**
     * Method selects all emails
     */
    public List<String> selectAllEmails();

    /**
     * Method deletes email
     * @param email
     */
    public void deleteEmail(Email email);
}
