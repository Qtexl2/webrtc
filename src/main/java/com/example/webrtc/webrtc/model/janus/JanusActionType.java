package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JanusActionType {
    CREATE("create"),
    TIMEOUT("timeout"),
    ATTACH("attach"),
    CALL("call"),
    MESSAGE("message"),
    REGISTER("register"),
    DETACHED("detached"),
    HANGUP("HANGUP"),
    ASK("ack"),
    EVENT("event"),
    SUCCESS("success");

    private String value;

    JanusActionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
