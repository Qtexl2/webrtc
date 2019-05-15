package com.example.webrtc.webrtc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ScheduledExecutorService;

public class ClientSession {

    private String name;
    @JsonIgnore
    private WebSocketSession webSocketSession;
    private Long janusHandlerId;
    private Long janusSessionId;
    @JsonIgnore
    private ScheduledExecutorService keepLiveScheduler;

    public ClientSession(String name, WebSocketSession webSocketSession) {
        this.name = name;
        this.webSocketSession = webSocketSession;
    }

    public ScheduledExecutorService getKeepLiveScheduler() {
        return keepLiveScheduler;
    }

    public void setKeepLiveScheduler(ScheduledExecutorService keepLiveScheduler) {
        this.keepLiveScheduler = keepLiveScheduler;
    }

    public Long getJanusHandlerId() {
        return janusHandlerId;
    }

    public void setJanusHandlerId(Long janusHandlerId) {
        this.janusHandlerId = janusHandlerId;
    }

    public Long getJanusSessionId() {
        return janusSessionId;
    }

    public void setJanusSessionId(Long janusSessionId) {
        this.janusSessionId = janusSessionId;
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

    @Override
    public String toString() {
        return "ClientSession{" +
                "name='" + name + '\'' +
                ", janusHandlerId=" + janusHandlerId +
                ", janusSessionId=" + janusSessionId +
                '}';
    }
}
