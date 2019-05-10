package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.event.SessionRepository;
import com.example.webrtc.webrtc.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private final SessionRepository sessionRepository;
    private final ObjectMapper objectMapper;
    public WebSocketHandler(SessionRepository sessionRepository, ObjectMapper objectMapper) {
        this.sessionRepository = sessionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = session.getUri().getQuery().split("=")[1];
        User user = new User(name, session);
        sessionRepository.add(session.getId(), user);

        Map<String, User> activeUser = sessionRepository.getActiveUser();
        String answer = objectMapper.writeValueAsString(activeUser);

        session.sendMessage(new TextMessage(answer));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionRepository.removeUser(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }
}
