package com.example.webrtc.webrtc.event.message;

public abstract class BaseMessage {

    private TypeMessage typeMessage;


    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
