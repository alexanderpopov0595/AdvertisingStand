package com.tsystems.javaschool.advertstand.dao;

import com.tsystems.javaschool.advertstand.domain.Email;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of email dao interface
 */
@Stateless
public class EmailDaoImpl implements EmailDao{

    /**
     * Injected entity manager
     */
    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    /**
     * Method adds email to database
     * @param email
     */
    @Override
    public void addEmail(Email email) {
        entityManager.persist(email);
    }

    /**
     * Method selects all emails from database
     * @return email list
     */
    @Override
    public List<String> selectAllEmails() {
        return  entityManager.createQuery("SELECT e.address FROM Email e").getResultList();
    }

    /**
     * Method deletes email from database with specific address and password
     * @param email
     */
    @Override
    public void deleteEmail(Email email) {
        entityManager.createQuery("DELETE FROM Email e WHERE e.address=:address AND e.password=:password")
                .setParameter("address", email.getAddress())
                .setParameter("password", email.getPassword())
                .executeUpdate();
    }
}
