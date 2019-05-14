package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PluginType {
    VIDEO_CALL("janus.plugin.videocall");


    PluginType(String value) {
        this.value = value;
    }

    private String value;
    @JsonValue
    public String getValue() {
        return value;
    }
}
