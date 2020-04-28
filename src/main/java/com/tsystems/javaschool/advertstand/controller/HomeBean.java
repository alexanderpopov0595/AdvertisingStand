package com.tsystems.javaschool.advertstand.controller;

import com.tsystems.javaschool.advertstand.service.mail.MailService;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a web socket server endpoint that notifies clients asynchronously with incoming JMS messages
 */
@ManagedBean
@ApplicationScoped
@ServerEndpoint(value = "/home")
public class HomeBean implements Serializable {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(HomeBean.class);

    /**
     *  Sessions object holds all web socket sessions connected to this web socket server endpoint
     */
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Injected mail service
     */
    @EJB
    private MailService mailService;

    /**
     * Message object stores recieved message before the new one comes
     */
    private static String message;

    public HomeBean(){

    }


    /**
     * Method opens web socket connection and sends message to frontend
     * @param peer -  connected user
     */
    /*
    @OnOpen
    public void onOpen (Session peer)  {
       sessions.add(peer);
        try {
            peer.getBasicRemote().sendText(this.message);
        } catch (Exception e) {
            logger.error("Error while sending stored message to web socket: "+e.getMessage());
        }
    }*/

    /**
     * Method closes connection for current user
     * @param peer - current user
     */
    @OnClose
    public void onClose (Session peer) {
        sessions.remove(peer);
    }



    @OnMessage
    public void textMessage(Session session, String message) {
        logger.info(message);
        mailService.sendEmail(message);
    }


    /**
     * Method saves new received jms message and then sends this message to all open web sockets
     * @param message
     */
    public void sendMessageToSocket(String message)  {
        this.message = message;
        for (Session session : sessions) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("Error while sending message to web socket: "+ e.getMessage());
                }
            } else {
                sessions.remove(session);
            }
        }
    }
}
