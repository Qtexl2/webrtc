package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JanusActionType {
    CREATE("create"),
    TIMEOUT("timeout"),
    ATTACH("attach"),
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
