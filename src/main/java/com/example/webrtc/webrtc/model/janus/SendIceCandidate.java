package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendIceCandidate {

    @JsonProperty("session_id")
    private Long sessionId;
    @JsonProperty("handle_id")
    private Long handlerId;
    private JanusActionType janus;
    private String transaction;
    private Candidate candidate;

    public SendIceCandidate() {
    }

    public SendIceCandidate(Long sessionId, Long handlerId, JanusActionType janus, String transaction, Candidate candidate) {
        this.sessionId = sessionId;
        this.handlerId = handlerId;
        this.janus = janus;
        this.transaction = transaction;
        this.candidate = candidate;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public JanusActionType getJanus() {
        return janus;
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "SendIceCandidate{" +
                "sessionId=" + sessionId +
                ", handlerId=" + handlerId +
                ", janus=" + janus +
                ", transaction='" + transaction + '\'' +
                ", candidate=" + candidate +
                '}';
    }
}
