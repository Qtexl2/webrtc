package com.example.webrtc.webrtc.event.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMessage {

    private TypeMessage typeMessage;


    public BaseMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public BaseMessage() {
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
