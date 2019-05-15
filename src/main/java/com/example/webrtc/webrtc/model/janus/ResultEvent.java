package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultEvent {
    INCOMINGCALL("incomingcall"),
    CALLING("calling"),
    ACCEPTED("accepted"),
    HANGUP("hangup"),
    REGISTERED("registered");

    private String value;

    ResultEvent(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
