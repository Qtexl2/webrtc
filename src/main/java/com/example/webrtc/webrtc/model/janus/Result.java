package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private ResultEvent event;
    private String username;

    public ResultEvent getEvent() {
        return event;
    }

    public void setEvent(ResultEvent event) {
        this.event = event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Result{" +
                "event=" + event +
                ", username='" + username + '\'' +
                '}';
    }
}
