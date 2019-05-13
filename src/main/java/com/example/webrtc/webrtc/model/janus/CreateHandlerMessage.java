package com.example.webrtc.webrtc.model.janus;

public class CreateHandlerMessage {
    private JanusActionType janus;
    private String plugin;
    private String transaction;


    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public JanusActionType getJanus() {
        return janus;
    }

    public CreateHandlerMessage() {
    }


    public CreateHandlerMessage(JanusActionType janus, String plugin, String transaction) {
        this.janus = janus;
        this.plugin = plugin;
        this.transaction = transaction;
    }

    public void setJanus(JanusActionType janus) {
        this.janus = janus;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
