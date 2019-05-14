package com.example.webrtc.webrtc.model.janus;

public class Body {

    private JanusActionType request;
    private String username;

    public Body(JanusActionType request) {
        this.request = request;
    }

    public Body(JanusActionType request, String username) {
        this.request = request;
        this.username = username;
    }

    public JanusActionType getRequest() {
        return request;
    }

    public void setRequest(JanusActionType request) {
        this.request = request;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Body{" +
                "request='" + request + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
