package com.tsystems.javaschool.advertstand.messages;

import com.tsystems.javaschool.advertstand.controller.HomeBean;
import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.IOException;

/**
 * Class is responsible for receiving jms messages from topic and pushing them to endpoint
 */
@Named
@MessageDriven(name = "top-topic", mappedName = "jms/top-topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "top-topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
@ResourceAdapter(value = "activemq-rar.rar")
public class MessageReceiver implements MessageListener {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MessageReceiver.class);

    public MessageReceiver() {

    }

    /**
     * Injected endpoint to push jms messages in
     */
    private HomeBean endpoint;

    @Inject
    public MessageReceiver(HomeBean endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Method listens to jms messages and push message's text to enpoint
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                endpoint.sendMessageToSocket(textMessage.getText());
            } catch (JMSException e) {
                logger.error("Error receiving JMS message: " + e.getMessage());
            }
        }
    }
}
