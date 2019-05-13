package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.model.janus.JanusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class JanusWebSocketHandler extends AbstractWebSocketHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("START");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("WORK");
        System.out.println(message.getPayload());
        String content = message.getPayload();

        JanusResponse response = objectMapper.readValue(content, JanusResponse.class);
        System.out.println(response);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("END");

    }
}
