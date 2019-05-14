package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateHandlerMessage {
    private JanusActionType janus = JanusActionType.ATTACH;
    private PluginType plugin;
    private String transaction;
    @JsonProperty("session_id")
    private Long sessionId;


    public PluginType getPlugin() {
        return plugin;
    }

    public void setPlugin(PluginType plugin) {
        this.plugin = plugin;
    }

    public JanusActionType getJanus() {
        return janus;
    }

    public CreateHandlerMessage() {
    }


    public CreateHandlerMessage(PluginType plugin, String transaction, Long sessionId) {
        this.plugin = plugin;
        this.transaction = transaction;
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
