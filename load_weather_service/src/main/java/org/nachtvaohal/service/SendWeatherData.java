package org.nachtvaohal.service;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import java.util.logging.Logger;

@RequestScoped
public class SendWeatherData implements SendMessage {
    private final Logger LOG = Logger.getLogger(SendWeatherData.class.getName());

    @Resource(mappedName = "java:/jms/queue/weather")
    private Queue queue;

    @Inject
    private JMSContext context;

    public void sendMessage(String cityName) {
        LOG.info("Sending message to weather-data-service module: ");
        context.createProducer().send(queue, cityName);
    }
}
