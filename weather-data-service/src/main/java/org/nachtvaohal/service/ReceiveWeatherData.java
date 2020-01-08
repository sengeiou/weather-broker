package org.nachtvaohal.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nachtvaohal.entities.BeanWithCreator;
import org.nachtvaohal.entities.Location;
import org.nachtvaohal.entities.WeatherData;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
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
            LOG.info("Message received.");
            //LOG.info(String.valueOf(storeData != null));
            //storeData.saveForecastInDatabase();
            String json = rcvMessage.getBody(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherData data = objectMapper.readValue(json, WeatherData.class);
            LOG.info(data.getLocation().toString());
            LOG.info(data.getForecasts().get(0).toString());
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}