package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {
    private String candidate;
    private Integer sdpMLineIndex;
    private String sdpMid;
    private Boolean completed;

    public Candidate(String candidate, Integer sdpMLineIndex, String sdpMid) {
        this.candidate = candidate;
        this.sdpMLineIndex = sdpMLineIndex;
        this.sdpMid = sdpMid;
    }

    public Candidate() {
        this.completed = true;
    }

    public Candidate(Boolean completed) {
        this.completed = completed;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Integer getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(Integer sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidate='" + candidate + '\'' +
                ", sdpMLineIndex=" + sdpMLineIndex +
                ", sdpMid='" + sdpMid + '\'' +
                ", completed=" + completed +
                '}';
    }
}
