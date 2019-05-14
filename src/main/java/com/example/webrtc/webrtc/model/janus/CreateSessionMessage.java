package com.example.webrtc.webrtc.model.janus;

public class CreateSessionMessage {
    private JanusActionType janus = JanusActionType.CREATE;
    private String transaction;

    public JanusActionType getJanus() {
        return janus;
    }

    public CreateSessionMessage() {
    }

    public CreateSessionMessage(String transaction) {
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
