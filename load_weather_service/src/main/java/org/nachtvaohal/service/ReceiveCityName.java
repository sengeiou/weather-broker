package org.nachtvaohal.service;

import org.nachtvaohal.view.City;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/city"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class ReceiveCityName implements MessageListener {
    private static final Logger LOG = Logger.getLogger(ReceiveCityName.class.getName());

    // TODO что то не то с внедрением
    private DataRequest dataRequest;
    private SendMessage sendMessage;
    private MessageService service;

    public ReceiveCityName(){}

    @Inject                 // Что вот тут не нравится?
    public ReceiveCityName(DataRequest dataRequest, SendMessage sendMessage, MessageService service) {
        this.dataRequest = dataRequest;
        this.sendMessage = sendMessage;
        this.service = service;
    }

    public void onMessage(Message rcvMessage) {
        try {
            String messageBody = rcvMessage.getBody(String.class);
            City city = service.convertToModel(messageBody, City.class);
            LOG.info(city.getName());
            sendMessage.sendMessage(dataRequest.getWeatherData(city.getName()));

        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}