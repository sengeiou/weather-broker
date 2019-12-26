package servlet;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import java.util.logging.Logger;

@RequestScoped
public class RequestServiceImpl implements RequestService {
    private final Logger LOG = Logger.getLogger(RequestServiceImpl.class.getName());
    private String name;

    @Resource(mappedName = "java:jboss/exported/jms/topic/weather")
    private Topic topic;

    @Inject
    private JMSContext context;

    public RequestServiceImpl(){}

    public void sendMessage(String txt) {
        LOG.info("Sending message to yahoo_weather module: " + txt);
        context.createProducer().send(topic, txt);

    }
}
