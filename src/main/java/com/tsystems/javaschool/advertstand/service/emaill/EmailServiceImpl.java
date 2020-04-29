package com.tsystems.javaschool.advertstand.service.emaill;

import com.tsystems.javaschool.advertstand.dao.EmailDao;
import com.tsystems.javaschool.advertstand.domain.Email;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmailServiceImpl implements EmailService{

    /**
     * Injected email dao
     */
    @EJB
    private EmailDao emailDao;

    @Override
    public void addEmail(Email email) {
        emailDao.addEmail(email);
    }

    @Override
    public List<String> selectAllEmails() {
        return emailDao.selectAllEmails();
    }

    @Override
    public void deleteEmail(Email email) {
        emailDao.deleteEmail(email);
    }
}
