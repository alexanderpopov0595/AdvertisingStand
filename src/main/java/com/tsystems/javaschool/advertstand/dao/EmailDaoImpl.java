package com.tsystems.javaschool.advertstand.dao;

import com.tsystems.javaschool.advertstand.domain.Email;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmailDaoImpl implements EmailDao{

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public void addEmail(Email email) {
        entityManager.persist(email);
    }

    @Override
    public List<String> selectAllEmails() {
        return  entityManager.createQuery("SELECT e.address FROM Email e").getResultList();
    }

    @Override
    public void deleteEmail(Email email) {
        entityManager.createQuery("DELETE FROM Email e WHERE e.address=:address AND e.password=:password")
                .setParameter("address", email.getAddress())
                .setParameter("password", email.getPassword())
                .executeUpdate();
    }
}
