package com.example.webrtc.webrtc.controller;

import com.example.webrtc.webrtc.adapter.JanusWebSocketMediaServerAdapter;
import com.example.webrtc.webrtc.event.ClientSessionRepository;
import com.example.webrtc.webrtc.event.TransactionRepository;
import com.example.webrtc.webrtc.generator.TransactionalKeyGenerator;
import com.example.webrtc.webrtc.model.ClientSession;
import com.example.webrtc.webrtc.model.janus.CreateHandlerMessage;
import com.example.webrtc.webrtc.model.janus.JanusResponse;
import com.example.webrtc.webrtc.model.janus.JanusTransactional;
import com.example.webrtc.webrtc.model.janus.JanusTransactionalStatus;
import com.example.webrtc.webrtc.model.janus.PluginType;
import com.example.webrtc.webrtc.model.janus.RegisterMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class JanusWebSocketHandler extends AbstractWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final TransactionRepository transactionRepository;
    private final JanusWebSocketMediaServerAdapter adapter;
    private final ClientSessionRepository clientSessionRepository;

    public JanusWebSocketHandler(ObjectMapper objectMapper, TransactionRepository transactionRepository, ClientSessionRepository clientSessionRepository, JanusWebSocketMediaServerAdapter adapter) {
        this.objectMapper = objectMapper;
        this.transactionRepository = transactionRepository;
        this.adapter = adapter;
        this.clientSessionRepository = clientSessionRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("START");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String content = message.getPayload();
        System.out.println(content);
        JanusResponse response = objectMapper.readValue(content, JanusResponse.class);
        String transaction = response.getTransaction();
        JanusTransactional janusTransactional = transactionRepository.getAndRemove(transaction);
        if(janusTransactional != null){
            String userSessionId = janusTransactional.getUserSession().getId();
            ClientSession clientSession = clientSessionRepository.getUser(userSessionId);
            switch (janusTransactional.getStatus()){
                case CREATE_SESSION:
                    Long janusSessionId = response.getData().getId();
                    clientSession.setJanusSessionId(janusSessionId);
                    createHandler(janusSessionId, clientSession);
                    break;
                case CREATE_HANDLER:
                    Long janusHandlerId = response.getData().getId();
                    clientSession.setJanusHandlerId(janusHandlerId);
                    System.out.println(clientSession);
                    createRegister(clientSession);
                    break;
                case REGISTER:
                    System.out.println("ЗАРЕГАЛСЯ ЛОЛКА");
            }
        }

    }

    private void createRegister(ClientSession clientSession) {
        String transactionKey = TransactionalKeyGenerator.generateKey();
        RegisterMessage registerMessage = new RegisterMessage(transactionKey, clientSession.getName(), clientSession.getJanusHandlerId(), clientSession.getJanusSessionId());
        JanusTransactional transactional = new JanusTransactional(JanusTransactionalStatus.REGISTER, clientSession.getWebSocketSession());
        transactionRepository.add(transactionKey, transactional);
        adapter.createRegister(registerMessage);

    }

    private void createHandler(Long janusSessionId, ClientSession clientSession){
        String transactionKey = TransactionalKeyGenerator.generateKey();
        CreateHandlerMessage createHandlerMessage = new CreateHandlerMessage(PluginType.VIDEO_CALL, transactionKey, janusSessionId);
        JanusTransactional transactional = new JanusTransactional(JanusTransactionalStatus.CREATE_HANDLER, clientSession.getWebSocketSession());
        transactionRepository.add(transactionKey, transactional);
        adapter.createHandler(createHandlerMessage);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("END");

    }
}
