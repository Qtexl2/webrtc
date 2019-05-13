package com.example.webrtc.webrtc.adapter.janus;

import com.example.webrtc.webrtc.adapter.MediaServerAdapter;
import com.example.webrtc.webrtc.connector.JanusWebSocketConnector;
import com.example.webrtc.webrtc.model.janus.CreateHandlerMessage;
import com.example.webrtc.webrtc.model.janus.CreateSessionMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JanusWebSocketMediaServerAdapter implements MediaServerAdapter {

    private final JanusWebSocketConnector connector;
    private final ObjectMapper objectMapper;

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
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void createHandler(CreateHandlerMessage createHandlerMessage) {
        String message = serializeObject(createHandlerMessage);
        connector.send(message);
    }
}
