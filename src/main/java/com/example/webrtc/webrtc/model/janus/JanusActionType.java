package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JanusActionType {
    CREATE("create"),
    TIMEOUT("timeout"),
    ATTACH("attach"),
    ACCEPT("accept"),
    CALL("call"),
    WEBRTCUP("webrtcup"),
    ERROR("error"),
    MEDIA("media"),
    TRICKLE("trickle"),
    MESSAGE("message"),
    KEEPLIVE("keepalive"),
    REGISTER("register"),
    DETACHED("detached"),
    HANGUP("hangup"),
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
