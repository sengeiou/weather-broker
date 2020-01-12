package org.nachtvaohal.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nachtvaohal.dao.WeatherDataDAO;
import org.nachtvaohal.model.WeatherData;
import org.nachtvaohal.view.WeatherDataView;

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
    private WeatherDataDAO dao;

    public ReceiveWeatherData(){}

    @Inject
    public ReceiveWeatherData(WeatherDataDAO dao) {
        this.dao = dao;
    }

    public void onMessage(Message rcvMessage) {
        try {
            LOG.info("Message received.");
            //LOG.info(String.valueOf(storeData != null));
            //storeData.saveForecastInDatabase();
            String json = rcvMessage.getBody(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherDataView data = objectMapper.readValue(json, WeatherDataView.class);
            LOG.info(data.getLocationView().toString());
            LOG.info(data.getForecastViews().get(0).toString());
            // Данные получены и отсюда отправляются в БД.
            dao.save(new WeatherData(data.getLocationView().getCity()));
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}