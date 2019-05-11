package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.event.SessionRepository;
import com.example.webrtc.webrtc.event.answers.ListUsers;
import com.example.webrtc.webrtc.event.answers.TypeAnswer;
import com.example.webrtc.webrtc.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;

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

        sendNotification(activeUser);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionRepository.removeUser(session.getId());
        Map<String, User> activeUser = sessionRepository.getActiveUser();
        sendNotification(activeUser);
    }

    private void sendNotification(Map<String, User> activeUser) throws IOException {
        ListUsers listUsers = new ListUsers(TypeAnswer.UPDATE_USERS, activeUser.entrySet());
        String answer = objectMapper.writeValueAsString(listUsers);
        System.out.println(answer);
        for (Map.Entry<String, User> entry : activeUser.entrySet()) {

            WebSocketSession webSocketSession = entry.getValue().getWebSocketSession();
            webSocketSession.sendMessage(new TextMessage(answer));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }
}
