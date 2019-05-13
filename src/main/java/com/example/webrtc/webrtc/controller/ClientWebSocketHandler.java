package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.event.ClientSessionRepository;
import com.example.webrtc.webrtc.event.message.BaseMessage;
import com.example.webrtc.webrtc.event.message.IceMessage;
import com.example.webrtc.webrtc.event.message.ListUsersMessage;
import com.example.webrtc.webrtc.event.message.SdpMessage;
import com.example.webrtc.webrtc.event.message.TypeMessage;
import com.example.webrtc.webrtc.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;

public class ClientWebSocketHandler extends AbstractWebSocketHandler {

    private final ClientSessionRepository clientSessionRepository;
    private final ObjectMapper objectMapper;

    public ClientWebSocketHandler(ClientSessionRepository clientSessionRepository, ObjectMapper objectMapper) {
        this.clientSessionRepository = clientSessionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = session.getUri().getQuery().split("=")[1];
        User user = new User(name, session);
        clientSessionRepository.add(session.getId(), user);
        Map<String, User> activeUser = clientSessionRepository.getActiveUser();
        sendNotification(activeUser);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clientSessionRepository.removeUser(session.getId());
        Map<String, User> activeUser = clientSessionRepository.getActiveUser();
        sendNotification(activeUser);
    }

    private void sendNotification(Map<String, User> activeUser) throws IOException {
        ListUsersMessage listUsersMessage = new ListUsersMessage(TypeMessage.UPDATE_USERS, activeUser.entrySet());
        String answer = objectMapper.writeValueAsString(listUsersMessage);
        for (Map.Entry<String, User> entry : activeUser.entrySet()) {

            WebSocketSession webSocketSession = entry.getValue().getWebSocketSession();
            webSocketSession.sendMessage(new TextMessage(answer));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String id = session.getId();
        BaseMessage baseMessage = objectMapper.readValue(message.getPayload(), BaseMessage.class);
        switch (baseMessage.getTypeMessage()){
            case OFFER:
            case ANSWER:
                    SdpMessage sdpMessage = objectMapper.readValue(message.getPayload(), SdpMessage.class);
                    sendMessageToСorrespondent(id, sdpMessage); break;
            case ICE_CANDIDATE:
                IceMessage iceMessage = objectMapper.readValue(message.getPayload(), IceMessage.class);
                sendMessageToСorrespondent(id, iceMessage); break;
        }

    }

    private void sendMessageToСorrespondent(String id, SdpMessage sdpMessage) throws IOException {
        User user = clientSessionRepository.getUser(sdpMessage.getSessionId());
        WebSocketSession webSocketSession = user.getWebSocketSession();
        sdpMessage.setSessionId(id);
        String message = objectMapper.writeValueAsString(sdpMessage);
        webSocketSession.sendMessage(new TextMessage(message));

    }

    private void sendMessageToСorrespondent(String id, IceMessage iceMessage) throws IOException {
        User user = clientSessionRepository.getUser(iceMessage.getSessionId());
        WebSocketSession webSocketSession = user.getWebSocketSession();
        iceMessage.setSessionId(id);
        String message = objectMapper.writeValueAsString(iceMessage);
        webSocketSession.sendMessage(new TextMessage(message));

    }
}
