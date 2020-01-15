package org.nachtvaohal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nachtvaohal.view.MessageModel;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import java.util.logging.Logger;

@RequestScoped
@Default
public class JsonMessageService implements MessageService {

    private final Logger LOG = Logger.getLogger(JsonMessageService.class.getName());

    public JsonMessageService() {
    }

    // todo добавить исключение
    @Override
    public String convertToMessage(MessageModel messageModel) throws JsonProcessingException {
        //Сериализация в JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messageModel);
        LOG.info(json);
        return json;
    }

    // todo добавить исключение
    @Override
    @SuppressWarnings("unchecked")
    public <T extends MessageModel> T convertToModel(String message, Class<T> messageModelClass) {
        ObjectMapper mapper = new ObjectMapper();
        MessageModel messageModel = null;
        try {
            messageModel = mapper.readValue(message, messageModelClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (messageModel == null || !messageModel.getClass().isAssignableFrom(messageModelClass)) {
            throw new RuntimeException ("SOME UNBELIEVEABLE TEXT************************************");
        }
        return (T)messageModel;
    }
}
