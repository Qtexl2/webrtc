package com.example.webrtc.webrtc.event.message;

import com.example.webrtc.webrtc.model.ClientSession;

import java.util.Map;
import java.util.Set;

public class ListUsersMessage extends BaseMessage {

    private Set<Map.Entry<String, ClientSession>> users;

    public ListUsersMessage(TypeMessage typeMessage, Set<Map.Entry<String, ClientSession>> users) {
        super.setTypeMessage(typeMessage);
        this.users = users;
    }

    public Set<Map.Entry<String, ClientSession>> getUsers() {
        return users;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return super.getTypeMessage();
    }
}
