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
import com.example.webrtc.webrtc.model.janus.CreateSdpMessage;
import com.example.webrtc.webrtc.model.janus.CreateSessionMessage;
import com.example.webrtc.webrtc.model.janus.JanusActionType;
import com.example.webrtc.webrtc.model.janus.JanusTransactional;
import com.example.webrtc.webrtc.model.janus.JanusTransactionalStatus;
import com.example.webrtc.webrtc.model.janus.JsepType;
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
    private final JanusWebSocketMediaServerAdapter adapter;
    @Lazy
    public ClientWebSocketHandler(ClientSessionRepository clientSessionRepository, ObjectMapper objectMapper, JanusWebSocketMediaServerAdapter janusAdapter, TransactionRepository transactionRepository, JanusWebSocketMediaServerAdapter adapter) {
        this.clientSessionRepository = clientSessionRepository;
        this.objectMapper = objectMapper;
        this.janusAdapter = janusAdapter;
        this.transactionRepository = transactionRepository;
        this.adapter = adapter;
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
        System.out.println("SEND BY USER  = " + message.getPayload());
        String id = session.getId();
        BaseMessage baseMessage = objectMapper.readValue(message.getPayload(), BaseMessage.class);
        switch (baseMessage.getTypeMessage()){
            case OFFER:
            case ANSWER:
                SdpMessage sdpMessage = objectMapper.readValue(message.getPayload(), SdpMessage.class);
                sendSdpToMediaServer(id, sdpMessage, baseMessage.getTypeMessage());
                break;
            case ICE_CANDIDATE:
//                IceMessage iceMessage = objectMapper.readValue(message.getPayload(), IceMessage.class);
//                sendMessageToСorrespondent(id, iceMessage); break;
        }

    }

    private void sendSdpToMediaServer(String currentSessionId, SdpMessage sdpMessage, TypeMessage type){
        ClientSession currentUser = clientSessionRepository.getUser(currentSessionId);
        ClientSession receiverUser = clientSessionRepository.getUser(sdpMessage.getSessionId());

        String key = TransactionalKeyGenerator.generateKey();
        CreateSdpMessage createSdpMessage;
        JanusTransactional transactional;
        if(type == TypeMessage.OFFER){
            createSdpMessage = new CreateSdpMessage(JanusActionType.CALL, receiverUser.getName(), JsepType.OFFER, sdpMessage.getMessage().getSdp(), key, currentUser.getJanusSessionId(), currentUser.getJanusHandlerId());
            transactional = new JanusTransactional(JanusTransactionalStatus.OFFER, receiverUser.getWebSocketSession());
        } else {
            createSdpMessage = new CreateSdpMessage(JanusActionType.ACCEPT, null, JsepType.ANSWER, sdpMessage.getMessage().getSdp(), key, currentUser.getJanusSessionId(), currentUser.getJanusHandlerId());
            transactional = new JanusTransactional(JanusTransactionalStatus.ANSWER, receiverUser.getWebSocketSession());
            System.out.println("УЛЕТАЕТ АНСВЕР НА ЯНУС");
            System.out.println(transactional);
        }
        transactionRepository.add(key, transactional);
        adapter.sendSdp(createSdpMessage);
    }

//    private void sendMessageToСorrespondent(String id, SdpMessage sdpMessage) throws IOException {
//        ClientSession clientSession = clientSessionRepository.getUser(sdpMessage.getSessionId());
//        WebSocketSession webSocketSession = clientSession.getWebSocketSession();
//        sdpMessage.setSessionId(id);
//        String message = objectMapper.writeValueAsString(sdpMessage);
//        webSocketSession.sendMessage(new TextMessage(message));
//
//    }
//
//    private void sendMessageToСorrespondent(String id, IceMessage iceMessage) throws IOException {
//        ClientSession clientSession = clientSessionRepository.getUser(iceMessage.getSessionId());
//        WebSocketSession webSocketSession = clientSession.getWebSocketSession();
//        iceMessage.setSessionId(id);
//        String message = objectMapper.writeValueAsString(iceMessage);
//        webSocketSession.sendMessage(new TextMessage(message));
//
//    }
}
