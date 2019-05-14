package com.example.webrtc.webrtc.adapter;

import com.example.webrtc.webrtc.connector.JanusWebSocketConnector;
import com.example.webrtc.webrtc.model.janus.CreateHandlerMessage;
import com.example.webrtc.webrtc.model.janus.CreateSessionMessage;
import com.example.webrtc.webrtc.model.janus.RegisterMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class JanusWebSocketMediaServerAdapter {

    private final JanusWebSocketConnector connector;
    private final ObjectMapper objectMapper;

    @Lazy
    public JanusWebSocketMediaServerAdapter(JanusWebSocketConnector connector, ObjectMapper objectMapper) {
        this.connector = connector;
        this.objectMapper = objectMapper;
    }


    public void createSession(CreateSessionMessage createSessionMessage) {
        String message = serializeObject(createSessionMessage);
        connector.send(message);
    }

    private String serializeObject(Object object) {
        try {
            String s = objectMapper.writeValueAsString(object);
            System.out.println(" SEND " + s);
            return s;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void createHandler(CreateHandlerMessage createHandlerMessage) {
        String message = serializeObject(createHandlerMessage);
        connector.send(message);
    }

    public void createRegister(RegisterMessage registerMessage) {
        String message = serializeObject(registerMessage);
        connector.send(message);
    }
}