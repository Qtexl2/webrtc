package com.example.webrtc.webrtc.event.answers;

import com.example.webrtc.webrtc.model.User;

import java.util.Map;
import java.util.Set;

public class ListUsers extends BaseAnswer{

    private Set<Map.Entry<String, User>> users;

    public ListUsers(TypeAnswer typeAnswer, Set<Map.Entry<String, User>> users) {
        super(typeAnswer);
        this.users = users;
    }

    public Set<Map.Entry<String, User>> getUsers() {
        return users;
    }

    @Override
    public TypeAnswer getTypeAnswer() {
        return super.getTypeAnswer();
    }
}
