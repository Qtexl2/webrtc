package com.example.webrtc.webrtc.generator;


import java.util.UUID;

public class TransactionalKeyGenerator {

    public static String generateKey(){
        return UUID.randomUUID().toString() + Thread.currentThread().getId();
    }
}
