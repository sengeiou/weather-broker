package org.nachtvaohal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nachtvaohal.view.City;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Logger;

@RequestScoped
public class SendCityName implements SendMessage {
    private final Logger LOG = Logger.getLogger(SendCityName.class.getName());

    // todo почему здесь два параметра инжектятся через конструктор, а один в поле?
    @Resource(mappedName = "java:/jms/queue/city")
    private Queue queue;
    private JMSContext context;
    private MessageService service;

    @Inject
    public SendCityName(JMSContext context, MessageService service) {
        this.context = context;
        this.service = service;
    }

    public void sendMessage(String cityName) {
        City city = new City(cityName);
        String message = null;
        try {
            message = service.convertToMessage(city);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        LOG.info("Sending message to load_weather_service module: " + cityName);
        context.createProducer().send(queue, message);
    }
}
