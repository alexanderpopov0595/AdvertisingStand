package com.tsystems.javaschool.advertstand.service.emaill;

import com.tsystems.javaschool.advertstand.dao.EmailDao;
import com.tsystems.javaschool.advertstand.domain.Email;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of email service
 */
@Stateless
public class EmailServiceImpl implements EmailService{

    /**
     * Injected email dao
     */
    private EmailDao emailDao;

    @Inject
    public EmailServiceImpl(EmailDao emailDao){
        this.emailDao=emailDao;
    }

    /**
     * Method adds email to database
     * @param email
     */
    @Override
    public void addEmail(Email email) {
        emailDao.addEmail(email);
    }

    /**
     * Method selects all emails from database
     * @return email list
     */
    @Override
    public List<String> selectAllEmails() {
        return emailDao.selectAllEmails();
    }

    /**
     * Method deletes email from database
     * @param email
     */
    @Override
    public void deleteEmail(Email email) {
        emailDao.deleteEmail(email);
    }
}
