
####create session {session_id}

**POST https://janus.conf.meetecho.com/janus**

_request_
```json
{ 
  "janus": "create", 
  "transaction": "4W6TKhv7T6mX"
}
```
_response_
```json
{
   "janus": "success",
   "transaction": "4W6TKhv7T6mX",
   "data": {
      "id": "{session_id}"
   }
}
```
___________________
####Created handle {handler_id}. Attache plugin

**POST https://janus.conf.meetecho.com/janus/{session_id}**

_request_
```json
{
  "janus": "attach", 
  "plugin": "janus.plugin.videocall", 
  "opaque_id": "videocalltest-DrlKBr3KjC9K",
  "transaction": "lSaP96DCXOtf"
 }
```
_response_
```json
{
   "janus": "success",
   "session_id": "{session_id}",
   "transaction": "lSaP96DCXOtf",
   "data": {
      "id": "{handler_id}"
   }
}
```
______________
####Send message

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "message",
   "body": {
            "request": "register", 
            "username": "22"
   }, 
   "transaction": "eVExoP3yIME7"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "eVExoP3yIME7"
}
```
________________
####Check event REGISTER (maxev = max events per request)

**GET https://janus.conf.meetecho.com/janus/{session_id}?maxev=10**

_response_
```json
[
   {
      "janus": "event",
      "session_id": "{session_id}",
      "transaction": "eVExoP3yIME7",
      "sender": "{handler_id}",
      "plugindata": {
         "plugin": "janus.plugin.videocall",
         "data": {
            "videocall": "event",
            "result": {
               "event": "registered",
               "username": "22"
            }
         }
      }
   }
]
```

______________
####List clientSession

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "message",
   "body": {
            "request": "list"
   }, 
   "transaction": "1LIgAzZPFbyR"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "eVExoP3yIME7"
}
```
______________
####Video call create OFFER

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "message",
   "body": {
            "request": "call",
            "username": "{receiver_name}"
   }, 
   "jsep": {
      "type": "offer",
      "sdp": "v=0o=- 713410859401750863 2 IN IP4 127.0.0.1 s=- t=0 0...."
   },
   "transaction": "1LIgAzZPFbyR"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "baCjRWLztI7E"
}
```
______________
####SEND Ice candidate 

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "trickle",
   "candidate": {
            "candidate": "candidate:2266845274 1 udp 2122260223 192.168.33.38 55281 typ host generation 0 ufrag U8oS network-id 1",
            "sdpMLineIndex": 0,
            "sdpMid": "0"
   }, 
   "transaction": "JMNnC9ZQwONi"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "baCjRWLztI7E"
}
```

______________
####SEND Ice candidate. In the end of list of candidates

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "trickle",
   "candidate": {
            "completed": true
   }, 
   "transaction": "0CUSAJFO5ZgY"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "baCjRWLztI7E"
}
```

##ANSWER

###CCheck event ACCEPTED video call

**GET https://janus.conf.meetecho.com/janus/{session_id}?maxev=10**

_response_
```json
[
   {
      "janus": "event",
      "session_id": "{session_id}",
      "transaction": "eVExoP3yIME7",
      "sender": "{handler_id}",
      "plugindata": {
         "plugin": "janus.plugin.videocall",
         "data": {
            "videocall": "event",
            "result": {
               "event": "accepted"
            }
         }
      }
   }
]
```

______________
####Video call create ANSWER

**POST https://janus.conf.meetecho.com/janus/{session_id}/{handler_id}**

_request_
```json
{
   "janus": "message",
   "body": {
            "request": "accept"
   }, 
   "jsep": {
      "type": "answer",
      "sdp": "v=0o=- 3516202664709355492 2 IN IP4 127.0.0.1 s=- t=0 0...."
   },
   "transaction": "e4FhpfHL2Uea"
}
```
_response_
```json
{
   "janus": "ack",
   "session_id": "{session_id}",
   "transaction": "e4FhpfHL2Uea"
}
```
______________
####Also SEND Ice candidates...


#### First client get event with ANSWER
_response_
```json
[
   {
      "janus": "event",
      "session_id": "{session_id}",
      "sender": "{handler_id}",
      "plugindata": {
         "plugin": "janus.plugin.videocall",
         "data": {
            "videocall": "event",
            "result": {
               "event": "accepted",
               "username": "333"
            }
         }
      },
      "jsep": {
         "type": "answer",
         "sdp": "v=0\r\no=- 3516202664709355492 2 IN IP4 188.213.167.189\r\ns=-\r\nt=0 0\r\na=group:BUNDLE 0 1 2\r\na=msid-semantic: WMS janus\r\nm=audio 9 UDP/TLS/RTP/SAVPF 111 9 0 8 106 105 13 110 112 113\r\nc=IN IP4 188.213.167.189\r\na=sendrecv\r\na=mid:0\r\na=rtcp-mux\r\na=ice-ufrag:9eig\r\na=ice-pwd:iMSdaq9UUnsVrrNH0nQXPA\r\na=ice-options:trickle\r\na=fingerprint:sha-256 D2:B9:31:8F:DF:24:D8:0E:ED:D2:EF:25:9E:AF:6F:B8:34:AE:53:9C:E6:F3:8F:F2:64:15:FA:E8:7F:53:2D:38\r\na=setup:active\r\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\r\na=extmap:2 http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\r\na=rtpmap:111 opus/48000/2\r\na=rtcp-fb:111 transport-cc\r\na=fmtp:111 minptime=10;useinbandfec=1\r\na=rtpmap:9 G722/8000\r\na=rtpmap:0 PCMU/8000\r\na=rtpmap:8 PCMA/8000\r\na=rtpmap:106 CN/32000\r\na=rtpmap:105 CN/16000\r\na=rtpmap:13 CN/8000\r\na=rtpmap:110 telephone-event/48000\r\na=rtpmap:112 telephone-event/32000\r\na=rtpmap:113 telephone-event/16000\r\na=ssrc:1294490271 cname:janus\r\na=ssrc:1294490271 msid:janus janusa0\r\na=ssrc:1294490271 mslabel:janus\r\na=ssrc:1294490271 label:janusa0\r\na=candidate:1 1 udp 2013266431 172.22.0.3 55899 typ host\r\na=candidate:2 1 udp 2013266431 172.18.0.2 52462 typ host\r\na=candidate:3 1 udp 2013266431 188.213.167.189 57576 typ host\r\na=end-of-candidates\r\nm=video 9 UDP/TLS/RTP/SAVPF 96 98 100 102 127 125 108 97 99 101 122 121 107 109\r\nc=IN IP4 188.213.167.189\r\na=sendrecv\r\na=mid:1\r\na=rtcp-mux\r\na=ice-ufrag:9eig\r\na=ice-pwd:iMSdaq9UUnsVrrNH0nQXPA\r\na=ice-options:trickle\r\na=fingerprint:sha-256 D2:B9:31:8F:DF:24:D8:0E:ED:D2:EF:25:9E:AF:6F:B8:34:AE:53:9C:E6:F3:8F:F2:64:15:FA:E8:7F:53:2D:38\r\na=setup:active\r\na=extmap:14 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:13 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:12 urn:3gpp:video-orientation\r\na=extmap:2 http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\r\na=extmap:11 http://www.webrtc.org/experiments/rtp-hdrext/playout-delay\r\na=extmap:6 http://www.webrtc.org/experiments/rtp-hdrext/video-content-type\r\na=extmap:7 http://www.webrtc.org/experiments/rtp-hdrext/video-timing\r\na=extmap:8 http://tools.ietf.org/html/draft-ietf-avtext-framemarking-07\r\na=extmap:9 http://www.webrtc.org/experiments/rtp-hdrext/color-space\r\na=rtpmap:96 VP8/90000\r\na=rtcp-fb:96 goog-remb\r\na=rtcp-fb:96 transport-cc\r\na=rtcp-fb:96 ccm fir\r\na=rtcp-fb:96 nack\r\na=rtcp-fb:96 nack pli\r\na=rtpmap:98 VP9/90000\r\na=rtcp-fb:98 goog-remb\r\na=rtcp-fb:98 transport-cc\r\na=rtcp-fb:98 ccm fir\r\na=rtcp-fb:98 nack\r\na=rtcp-fb:98 nack pli\r\na=fmtp:98 profile-id=0\r\na=rtpmap:100 VP9/90000\r\na=rtcp-fb:100 goog-remb\r\na=rtcp-fb:100 transport-cc\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=fmtp:100 profile-id=2\r\na=rtpmap:102 H264/90000\r\na=rtcp-fb:102 goog-remb\r\na=rtcp-fb:102 transport-cc\r\na=rtcp-fb:102 ccm fir\r\na=rtcp-fb:102 nack\r\na=rtcp-fb:102 nack pli\r\na=fmtp:102 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42001f\r\na=rtpmap:127 H264/90000\r\na=rtcp-fb:127 goog-remb\r\na=rtcp-fb:127 transport-cc\r\na=rtcp-fb:127 ccm fir\r\na=rtcp-fb:127 nack\r\na=rtcp-fb:127 nack pli\r\na=fmtp:127 level-asymmetry-allowed=1;packetization-mode=0;profile-level-id=42001f\r\na=rtpmap:125 H264/90000\r\na=rtcp-fb:125 goog-remb\r\na=rtcp-fb:125 transport-cc\r\na=rtcp-fb:125 ccm fir\r\na=rtcp-fb:125 nack\r\na=rtcp-fb:125 nack pli\r\na=fmtp:125 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42e01f\r\na=rtpmap:108 H264/90000\r\na=rtcp-fb:108 goog-remb\r\na=rtcp-fb:108 transport-cc\r\na=rtcp-fb:108 ccm fir\r\na=rtcp-fb:108 nack\r\na=rtcp-fb:108 nack pli\r\na=fmtp:108 level-asymmetry-allowed=1;packetization-mode=0;profile-level-id=42e01f\r\na=rtpmap:97 rtx/90000\r\na=fmtp:97 apt=96\r\na=rtpmap:99 rtx/90000\r\na=fmtp:99 apt=98\r\na=rtpmap:101 rtx/90000\r\na=fmtp:101 apt=100\r\na=rtpmap:122 rtx/90000\r\na=fmtp:122 apt=102\r\na=rtpmap:121 rtx/90000\r\na=fmtp:121 apt=127\r\na=rtpmap:107 rtx/90000\r\na=fmtp:107 apt=125\r\na=rtpmap:109 rtx/90000\r\na=fmtp:109 apt=108\r\na=ssrc-group:FID 3185065419 2071975610\r\na=ssrc:3185065419 cname:janus\r\na=ssrc:3185065419 msid:janus janusv0\r\na=ssrc:3185065419 mslabel:janus\r\na=ssrc:3185065419 label:janusv0\r\na=ssrc:2071975610 cname:janus\r\na=ssrc:2071975610 msid:janus janusv0\r\na=ssrc:2071975610 mslabel:janus\r\na=ssrc:2071975610 label:janusv0\r\na=candidate:1 1 udp 2013266431 172.22.0.3 55899 typ host\r\na=candidate:2 1 udp 2013266431 172.18.0.2 52462 typ host\r\na=candidate:3 1 udp 2013266431 188.213.167.189 57576 typ host\r\na=end-of-candidates\r\nm=application 9 DTLS/SCTP 5000\r\nc=IN IP4 188.213.167.189\r\nb=AS:30\r\na=sendrecv\r\na=sctpmap:5000 webrtc-datachannel 16\r\na=mid:2\r\na=ice-ufrag:9eig\r\na=ice-pwd:iMSdaq9UUnsVrrNH0nQXPA\r\na=ice-options:trickle\r\na=fingerprint:sha-256 D2:B9:31:8F:DF:24:D8:0E:ED:D2:EF:25:9E:AF:6F:B8:34:AE:53:9C:E6:F3:8F:F2:64:15:FA:E8:7F:53:2D:38\r\na=setup:active\r\na=candidate:1 1 udp 2013266431 172.22.0.3 55899 typ host\r\na=candidate:2 1 udp 2013266431 172.18.0.2 52462 typ host\r\na=candidate:3 1 udp 2013266431 188.213.167.189 57576 typ host\r\na=end-of-candidates\r\n"
      }
   }
]
```

#### Get something =) first
_response_
```json
[
   {
      "janus": "webrtcup",
      "session_id": "{session_id}",
      "sender": "{handler_id}"
   },
   {
      "janus": "media",
      "session_id": "{session_id}",
      "sender": "{handler_id}",
      "type": "video",
      "receiving": true
   },
   {
      "janus": "media",
      "session_id": "{session_id}",
      "sender": "{handler_id}",
      "type": "audio",
      "receiving": true
   }
]
```

#### Get something =) second
_response_
```json
[
   {
      "janus": "webrtcup",
      "session_id": "{session_id}",
      "sender": "{handler_id}"
   }
]
```