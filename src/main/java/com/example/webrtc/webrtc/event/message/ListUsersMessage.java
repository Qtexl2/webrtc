package com.example.webrtc.webrtc.event.message;

import com.example.webrtc.webrtc.model.User;

import java.util.Map;
import java.util.Set;

public class ListUsersMessage extends BaseMessage {

    private Set<Map.Entry<String, User>> users;

    public ListUsersMessage(TypeMessage typeMessage, Set<Map.Entry<String, User>> users) {
        super.setTypeMessage(typeMessage);
        this.users = users;
    }

    public Set<Map.Entry<String, User>> getUsers() {
        return users;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return super.getTypeMessage();
    }
}
