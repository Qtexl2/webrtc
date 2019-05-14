package com.example.webrtc.webrtc.event;

import com.example.webrtc.webrtc.model.ClientSession;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientSessionRepository {

    private Map<String, ClientSession> activeUser = new ConcurrentHashMap<>();

    public void add(String sessionId, ClientSession clientSession) {
        activeUser.put(sessionId, clientSession);
    }

    public ClientSession getUser(String name) {
        return activeUser.get(name);
    }

    public void removeUser(String sessionId) {
        activeUser.remove(sessionId);
    }

    public Map<String, ClientSession> getActiveUser() {
        return activeUser;
    }

    public ClientSession getClientBySessionAndHandleId(Long sessionId, Long handleId){
        return activeUser.values().stream()
                .filter(it -> Objects.equals(it.getJanusSessionId(), sessionId) && Objects.equals(it.getJanusHandlerId(), handleId))
                .findFirst().get();
    }

    public ClientSession getClientByName(String name){
        return activeUser.values().stream()
                .filter(it -> Objects.equals(it.getName(), name))
                .findFirst().get();
    }

}