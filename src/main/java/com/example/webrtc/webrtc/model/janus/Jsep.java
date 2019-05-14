package com.example.webrtc.webrtc.model.janus;

public class Jsep {
    private JsepType type;
    private String sdp;


    public Jsep() {
    }

    public Jsep(JsepType type, String sdp) {
        this.type = type;
        this.sdp = sdp;
    }

    public JsepType getType() {
        return type;
    }

    public void setType(JsepType type) {
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    @Override
    public String toString() {
        return "Jsep{" +
                "type=" + type +
                ", sdp='" + sdp + '\'' +
                '}';
    }
}
