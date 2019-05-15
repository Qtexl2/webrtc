package com.example.webrtc.webrtc.event;


import com.example.webrtc.webrtc.model.janus.JanusTransactional;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionRepository {

    private Map<String, JanusTransactional> activeTransaction = new ConcurrentHashMap<>();

    public void add(String transaction, JanusTransactional janusTransactional) {
        activeTransaction.put(transaction, janusTransactional);
    }

    public JanusTransactional getAndRemove(String transaction) {
        if(transaction != null && activeTransaction.containsKey(transaction)){
            JanusTransactional janusTransactional = get(transaction);
            activeTransaction.remove(transaction);
            return janusTransactional;
        }
        return null;
    }
    public int size(){
        return activeTransaction.size();
    }
    private JanusTransactional get(String transaction) {
        return activeTransaction.get(transaction);
    }
}
