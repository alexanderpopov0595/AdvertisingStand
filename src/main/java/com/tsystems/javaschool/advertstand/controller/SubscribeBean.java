package com.tsystems.javaschool.advertstand.controller;

import com.tsystems.javaschool.advertstand.domain.Email;
import com.tsystems.javaschool.advertstand.service.emaill.EmailService;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.websocket.server.ServerEndpoint;

@ManagedBean
@ApplicationScoped
@ServerEndpoint(value = "/subscribe")
public class SubscribeBean {

    private Email email;

    @EJB
    private EmailService emailService;

    public SubscribeBean(){
        email=new Email();
    }

    public void subscribe(){
        emailService.addEmail(email);
        email=new Email();
    }
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
