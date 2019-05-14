package com.example.webrtc.webrtc.event.message;

import com.example.webrtc.webrtc.model.janus.Jsep;
import com.example.webrtc.webrtc.model.janus.JsepType;
import com.example.webrtc.webrtc.model.janus.Sdp;

public class SdpMessage extends BaseMessage {
    private String sessionId;
    private Sdp message;

    public SdpMessage() {
    }

    public SdpMessage(String sessionId, Sdp message, TypeMessage typeMessage) {
        super(typeMessage);
        this.sessionId = sessionId;
        this.message = message;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Sdp getMessage() {
        return message;
    }

    public void setMessage(Sdp message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SdpMessage{" +
                "sessionId='" + sessionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}
