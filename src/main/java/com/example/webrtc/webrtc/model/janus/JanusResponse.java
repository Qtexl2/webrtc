package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JanusResponse {
    private JanusActionType janus;
    private String transaction;
    private DataResponse data;
    @JsonProperty("session_id")
    private Long sessionId;
    private Long sender;
    private Jsep jsep;
    private PluginData plugindata;

    public JanusActionType getJanus() {
        return janus;
    }

    public void setJanus(JanusActionType janus) {
        this.janus = janus;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Jsep getJsep() {
        return jsep;
    }

    public void setJsep(Jsep jsep) {
        this.jsep = jsep;
    }

    public PluginData getPlugindata() {
        return plugindata;
    }

    public void setPlugindata(PluginData plugindata) {
        this.plugindata = plugindata;
    }

    @Override
    public String toString() {
        return "JanusResponse{" +
                "janus=" + janus +
                ", transaction='" + transaction + '\'' +
                ", data=" + data +
                ", sessionId=" + sessionId +
                ", sender=" + sender +
                ", jsep=" + jsep +
                ", plugindata=" + plugindata +
                '}';
    }
}
