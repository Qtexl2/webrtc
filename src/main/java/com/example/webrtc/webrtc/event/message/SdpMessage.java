package com.example.webrtc.webrtc.event.message;

public class SdpMessage extends BaseMessage {
    private String sessionId;
    private Offer offer;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "SdpMessage{" +
                "sessionId='" + sessionId + '\'' +
                ", offer='" + offer + '\'' +
                '}';
    }

    public class Offer{
        private String type;
        private String sdp;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSdp() {
            return sdp;
        }

        public void setSdp(String sdp) {
            this.sdp = sdp;
        }
    }
}
