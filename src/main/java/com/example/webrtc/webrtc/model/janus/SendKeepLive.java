package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendKeepLive {
    private JanusActionType janus = JanusActionType.KEEPLIVE;
    @JsonProperty("session_id")
    private Long sessionId;
    private String transaction;

    public SendKeepLive(Long sessionId, String transaction) {
        this.sessionId = sessionId;
        this.transaction = transaction;
    }

    public SendKeepLive() {
    }

    public JanusActionType getJanus() {
        return janus;
    }

    public void setJanus(JanusActionType janus) {
        this.janus = janus;
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

    @Override
    public String toString() {
        return "SendKeepLive{" +
                "janus=" + janus +
                ", sessionId=" + sessionId +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
