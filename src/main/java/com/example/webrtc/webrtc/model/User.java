package com.example.webrtc.webrtc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.WebSocketSession;

public class User {

    private String name;
    @JsonIgnore
    private WebSocketSession webSocketSession;

    public User(String name, WebSocketSession webSocketSession) {
        this.name = name;
        this.webSocketSession = webSocketSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }
}
