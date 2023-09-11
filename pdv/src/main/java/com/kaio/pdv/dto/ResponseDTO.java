package com.kaio.pdv.dto;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO {

    private List<String> messages;

    public ResponseDTO(List<String> messages) {
        this.messages = messages;
    }

    public ResponseDTO(String messages) {
        this.messages = Arrays.asList(messages);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
