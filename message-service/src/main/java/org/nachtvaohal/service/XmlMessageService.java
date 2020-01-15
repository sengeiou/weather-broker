package org.nachtvaohal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nachtvaohal.view.MessageModel;

public class XmlMessageService implements MessageService {
    @Override
    public String convertToMessage(MessageModel messageModel) throws JsonProcessingException {
        return null;
    }

    @Override
    public <T extends MessageModel> T convertToModel(String message, Class<T> messageModelClass) {
        return null;
    }
}
