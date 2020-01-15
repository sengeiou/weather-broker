package org.nachtvaohal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nachtvaohal.view.MessageModel;

public interface MessageService {

    String convertToMessage(MessageModel messageModel) throws JsonProcessingException;

    public <T extends MessageModel> T convertToModel(String message, Class<T> messageModelClass);

}
