package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterMessage {
    private JanusActionType janus = JanusActionType.MESSAGE;
    private String transaction;
    private Body body;
    @JsonProperty("handle_id")
    private Long handleId;
    @JsonProperty("session_id")
    private Long sessionId;

    public RegisterMessage(String transaction, String username, Long handleId, Long sessionId) {
        this.transaction = transaction;
        this.body = new Body(JanusActionType.REGISTER, username);
        this.handleId = handleId;
        this.sessionId = sessionId;
    }

    public RegisterMessage() {
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

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "RegisterMessage{" +
                "janus=" + janus +
                ", transaction='" + transaction + '\'' +
                ", body=" + body +
                ", handleId=" + handleId +
                ", sessionId=" + sessionId +
                '}';
    }
}
