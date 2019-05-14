package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.adapter.JanusWebSocketMediaServerAdapter;
import com.example.webrtc.webrtc.event.ClientSessionRepository;
import com.example.webrtc.webrtc.event.TransactionRepository;
import com.example.webrtc.webrtc.event.message.BaseMessage;
import com.example.webrtc.webrtc.event.message.IceMessage;
import com.example.webrtc.webrtc.event.message.ListUsersMessage;
import com.example.webrtc.webrtc.event.message.SdpMessage;
import com.example.webrtc.webrtc.event.message.TypeMessage;
import com.example.webrtc.webrtc.generator.TransactionalKeyGenerator;
import com.example.webrtc.webrtc.model.ClientSession;
import com.example.webrtc.webrtc.model.janus.CreateSessionMessage;
import com.example.webrtc.webrtc.model.janus.JanusTransactional;
import com.example.webrtc.webrtc.model.janus.JanusTransactionalStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;

@Component
public class ClientWebSocketHandler extends AbstractWebSocketHandler {

    private final ClientSessionRepository clientSessionRepository;
    private final ObjectMapper objectMapper;
    private final JanusWebSocketMediaServerAdapter janusAdapter;
    private final TransactionRepository transactionRepository;
    @Lazy
    public ClientWebSocketHandler(ClientSessionRepository clientSessionRepository, ObjectMapper objectMapper, JanusWebSocketMediaServerAdapter janusAdapter, TransactionRepository transactionRepository) {
        this.clientSessionRepository = clientSessionRepository;
        this.objectMapper = objectMapper;
        this.janusAdapter = janusAdapter;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = session.getUri().getQuery().split("=")[1];

        ClientSession clientSession = new ClientSession(name, session);
        String transactionKey = TransactionalKeyGenerator.generateKey();
        JanusTransactional janusTransactional = new JanusTransactional(JanusTransactionalStatus.CREATE_SESSION, session);
        CreateSessionMessage createSessionMessage = new CreateSessionMessage(transactionKey);

        clientSessionRepository.add(session.getId(), clientSession);
        transactionRepository.add(transactionKey, janusTransactional);
        Map<String, ClientSession> activeUser = clientSessionRepository.getActiveUser();

        janusAdapter.createSession(createSessionMessage);
        sendNotification(activeUser);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clientSessionRepository.removeUser(session.getId());
        Map<String, ClientSession> activeUser = clientSessionRepository.getActiveUser();
        sendNotification(activeUser);
    }

    private void sendNotification(Map<String, ClientSession> activeUser) throws IOException {
        ListUsersMessage listUsersMessage = new ListUsersMessage(TypeMessage.UPDATE_USERS, activeUser.entrySet());
        String answer = objectMapper.writeValueAsString(listUsersMessage);
        for (Map.Entry<String, ClientSession> entry : activeUser.entrySet()) {

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
        ClientSession clientSession = clientSessionRepository.getUser(sdpMessage.getSessionId());
        WebSocketSession webSocketSession = clientSession.getWebSocketSession();
        sdpMessage.setSessionId(id);
        String message = objectMapper.writeValueAsString(sdpMessage);
        webSocketSession.sendMessage(new TextMessage(message));

    }

    private void sendMessageToСorrespondent(String id, IceMessage iceMessage) throws IOException {
        ClientSession clientSession = clientSessionRepository.getUser(iceMessage.getSessionId());
        WebSocketSession webSocketSession = clientSession.getWebSocketSession();
        iceMessage.setSessionId(id);
        String message = objectMapper.writeValueAsString(iceMessage);
        webSocketSession.sendMessage(new TextMessage(message));

    }
}
