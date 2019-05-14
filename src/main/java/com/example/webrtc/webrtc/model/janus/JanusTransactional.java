package com.example.webrtc.webrtc.model.janus;

import org.springframework.web.socket.WebSocketSession;

public class JanusTransactional {

    private JanusTransactionalStatus status;
    private WebSocketSession userSession;

    public JanusTransactional(JanusTransactionalStatus status) {
        this.status = status;
    }

    public JanusTransactional(JanusTransactionalStatus status, WebSocketSession userSession) {
        this.status = status;
        this.userSession = userSession;
    }

    public JanusTransactionalStatus getStatus() {
        return status;
    }

    public void setStatus(JanusTransactionalStatus status) {
        this.status = status;
    }

    public WebSocketSession getUserSession() {
        return userSession;
    }

    public void setUserSession(WebSocketSession userSession) {
        this.userSession = userSession;
    }
}
