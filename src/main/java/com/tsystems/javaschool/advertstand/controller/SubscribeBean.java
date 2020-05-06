package com.tsystems.javaschool.advertstand.controller;

import com.tsystems.javaschool.advertstand.domain.Email;
import com.tsystems.javaschool.advertstand.service.emaill.EmailService;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.websocket.server.ServerEndpoint;

/**
 * Represents a controller which is responsible for getting email's information from form
 */
@ManagedBean
@ApplicationScoped
@ServerEndpoint(value = "/subscribe")
public class SubscribeBean {

    /**
     * Email object
     */
    private Email email;

    /**
     * Injected email service
     */
    @EJB
    private EmailService emailService;

    public SubscribeBean(){
        email=new Email();
    }

    /**
     * Method adds email to database
     */
    public void subscribe(){
        emailService.addEmail(email);
        email=new Email();
    }

    /**
     * Method deletes email from database
     */
    public void unsubscribe(){
        emailService.deleteEmail(email);
    }

    public void setEmail(Email email){
        this.email=email;
    }
    public Email getEmail(){
        return email;
    }
}
