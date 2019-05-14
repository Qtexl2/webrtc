package com.example.webrtc.webrtc.model.janus;

public class Sdp {
        private JsepType type;
        private String sdp;

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

        public Sdp(JsepType type, String sdp) {
            this.type = type;
            this.sdp = sdp;
        }

        public Sdp() {
        }

        @Override
        public String toString() {
            return "Sdp{" +
                    "type='" + type + '\'' +
                    ", sdp='" + sdp + '\'' +
                    '}';
        }
    }