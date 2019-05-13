package com.example.webrtc.webrtc.event;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionRepository {

    private Map<String, WebSocketSession> activeTransaction = new ConcurrentHashMap<>();

    public void add(String transaction, WebSocketSession socketSession){
        activeTransaction.put(transaction, socketSession);
    }

    public WebSocketSession getAndRemove(String transaction){
        WebSocketSession webSocketSession = get(transaction);
        activeTransaction.remove(transaction);
        return webSocketSession;
    }

    public WebSocketSession get(String transaction){
        return activeTransaction.get(transaction);
    }
}
