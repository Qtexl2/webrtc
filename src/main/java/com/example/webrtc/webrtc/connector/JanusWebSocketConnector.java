package com.example.webrtc.webrtc.connector;

import com.example.webrtc.webrtc.controller.JanusWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URI;

@Component
public class JanusWebSocketConnector {
    private final String secWebSocketProtocol = "janus-protocol";
    private final String url = "ws://localhost:8188";
    private final WebSocketSession session;


    public JanusWebSocketConnector(JanusWebSocketHandler janusWebSocketHandler) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.setSecWebSocketProtocol(secWebSocketProtocol);
        URI uri = URI.create(url);
        try {
            session = client.doHandshake(janusWebSocketHandler, webSocketHttpHeaders, uri).get();
        } catch (Exception e) {
            throw new RuntimeException("JanusWebSocketConnector - проблемы");
        }
    }

    @PreDestroy
    private void destroy() throws IOException {
        if (session.isOpen()) {
            session.close();
        }
    }

    public void send(String message) {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
