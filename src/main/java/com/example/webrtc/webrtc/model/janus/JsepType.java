package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JsepType {
    OFFER("offer"),
    ANSWER("answer");

    JsepType(String value) {
        this.value = value;
    }

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
