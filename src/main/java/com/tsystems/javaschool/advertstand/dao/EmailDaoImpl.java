package com.tsystems.javaschool.advertstand.dao;

import com.tsystems.javaschool.advertstand.domain.Email;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmailDaoImpl implements EmailDao{

    private static final Logger logger= Logger.getLogger(EmailDao.class);

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public void addEmail(Email email) {
        logger.info("Email to persist: "+ email.getId()+" "+email.getEmail()+" "+email.getPassword());
        entityManager.persist(email);
    }

    @Override
    public List<String> selectAllEmails() {
        return (List<String>) entityManager.createQuery("SELECT e.email FROM Email e").getResultList();
    }

    @Override
    public void deleteEmail(Email email) {
        entityManager.createQuery("DELETE FROM Email e WHERE e.email=:email AND e.password=:password")
                .setParameter("email", email.getEmail())
                .setParameter("password", email.getPassword())
                .executeUpdate();
    }
}
