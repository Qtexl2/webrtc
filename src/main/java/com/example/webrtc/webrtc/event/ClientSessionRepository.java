package com.example.webrtc.webrtc.event;

import com.example.webrtc.webrtc.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientSessionRepository {

    private Map<String, User> activeUser = new ConcurrentHashMap<>();

    public void add(String sessionId, User user) {
        activeUser.put(sessionId, user);
    }

    public User getUser(String name) {
        return activeUser.get(name);
    }

    public void removeUser(String sessionId) {
        activeUser.remove(sessionId);
    }

    public Map<String, User> getActiveUser() {
        return activeUser;
    }

}