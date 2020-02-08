package org.nachtvaohal.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nachtvaohal.dao.DataTransformer;
import org.nachtvaohal.view.WeatherDataView;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/weather"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class ReceiveWeatherData implements MessageListener {
    private static final Logger LOG = Logger.getLogger(ReceiveWeatherData.class.getName());

    private DataTransformer dataTransformer;
    // TODO не работает без конструктора по умолчанию
    public ReceiveWeatherData() {
    }

    @Inject
    public ReceiveWeatherData(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    public void onMessage(Message rcvMessage) {
        try {
            LOG.info("Message received.");
            String json = rcvMessage.getBody(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherDataView data = objectMapper.readValue(json, WeatherDataView.class);

            // Данные получены и отсюда отправляются в БД.
            dataTransformer.transformToEntity(data);
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}