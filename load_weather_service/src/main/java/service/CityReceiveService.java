package service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
public class CityReceiveService implements MessageListener {
    private static final Logger LOG = Logger.getLogger(CityReceiveService.class.getName());

    // TODO что то не то с внедрением
    private DataRequest dataRequest;

    public CityReceiveService(){}

    @Inject
    public CityReceiveService(DataRequest dataRequest) {
        this.dataRequest = dataRequest;
    }

    public void onMessage(Message rcvMessage) {
        try {
            LOG.info("Message received: " + rcvMessage.getBody(String.class));
            LOG.info(String.valueOf(dataRequest != null));
            dataRequest.getData();
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}