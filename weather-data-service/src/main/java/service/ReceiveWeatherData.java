package service;

import repository.StoreData;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/weather"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class ReceiveWeatherData implements MessageListener {
    private static final Logger LOG = Logger.getLogger(ReceiveWeatherData.class.getName());

    // TODO что то не то с внедрением
    //private StoreData storeData;

    public ReceiveWeatherData(){}

//    @Inject
//    public ReceiveWeatherData(StoreData dataRequest) {
//        this.storeData = dataRequest;
//    }

    public void onMessage(Message rcvMessage) {
        try {
            LOG.info("Message received: " + rcvMessage.getBody(String.class));
            //LOG.info(String.valueOf(storeData != null));
            //storeData.saveForecastInDatabase();
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}