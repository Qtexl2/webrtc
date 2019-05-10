package com.example.webrtc.webrtc.event;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class PresenceEventListener {
	
	private ParticipantRepository participantRepository;


	public PresenceEventListener(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}
		
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String username = headers.getUser().getName();

//		messagingTemplate.convertAndSend(loginDestination, loginEvent);
		
		// We store the session as we need to be idempotent in the disconnect event processing
		participantRepository.add(headers.getSessionId(), username);
	}
	
	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		
		Optional.ofNullable(participantRepository.getParticipant(event.getSessionId()))
				.ifPresent(login -> {
//					messagingTemplate.convertAndSend(logoutDestination, new LogoutEvent(login.getUsername()));
					participantRepository.removeParticipant(event.getSessionId());
				});
	}
//
//	public void setLoginDestination(String loginDestination) {
//		this.loginDestination = loginDestination;
//	}
//
//	public void setLogoutDestination(String logoutDestination) {
//		this.logoutDestination = logoutDestination;
//	}
}
