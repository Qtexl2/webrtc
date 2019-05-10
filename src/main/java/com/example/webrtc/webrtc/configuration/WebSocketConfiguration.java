package com.example.webrtc.webrtc.configuration;

import com.example.webrtc.webrtc.controller.WebSocketHandler;
import com.example.webrtc.webrtc.event.SessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(sessionRepository(), objectMapper()), "/socket").setAllowedOrigins("*");
    }

    @Bean
    public SessionRepository sessionRepository(){
        return new SessionRepository();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
