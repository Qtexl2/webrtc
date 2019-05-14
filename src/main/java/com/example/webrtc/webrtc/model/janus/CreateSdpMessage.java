package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSdpMessage {
    private JanusActionType janus = JanusActionType.MESSAGE;
    private Body body;
    private Jsep jsep;
    private String transaction;
    @JsonProperty("session_id")
    private Long sessionId;
    @JsonProperty("handle_id")
    private Long handlerId;

    public CreateSdpMessage() {
    }

    public CreateSdpMessage(String username, JsepType type, String sdp, String transaction, Long sessionId, Long handlerId) {
        this.body = new Body(JanusActionType.CALL, username);
        this.jsep = new Jsep(type, sdp);
        this.transaction = transaction;
        this.sessionId = sessionId;
        this.handlerId = handlerId;
    }

    public JanusActionType getJanus() {
        return janus;
    }

    public void setJanus(JanusActionType janus) {
        this.janus = janus;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Jsep getJsep() {
        return jsep;
    }

    public void setJsep(Jsep jsep) {
        this.jsep = jsep;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
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
}
