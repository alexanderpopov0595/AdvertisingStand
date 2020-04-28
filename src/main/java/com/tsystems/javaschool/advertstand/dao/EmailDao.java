package com.tsystems.javaschool.advertstand.dao;

import com.tsystems.javaschool.advertstand.domain.Email;
import java.util.List;

/**
 * Inteface provides methods to work with emails in database
 */
public interface EmailDao {

    /**
     * Method adds email to database
     * @param email
     */
    public void addEmail(Email email);

    /**
     * Method returns all emails
     * @return
     */
    public List<String> selectAllEmails();

    /**
     * Method deletes email from database
     * @param email
     */
    public void deleteEmail(Email email);
}
