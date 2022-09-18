package com.javatechie.spring.ws.api.client;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class RunJavaWsClient {

    public static void startClient() throws ExecutionException, InterruptedException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String firstName = "";
        String lastName = "";
        System.out.println("This is Java client for connect to WebSocket Stomp server with SockJS");
        System.out.println("You need to type first name and last name into cmd-line");
        System.out.println("Type first name");
        firstName = bufferedReader.readLine();
        System.out.println("Type last name");
        lastName = bufferedReader.readLine();
        String jsonMsg = "{ " + firstName + " : " + lastName + " }";

        MyWebSocketClient myWebSocketClient = new MyWebSocketClient();
        ListenableFuture<StompSession> f = myWebSocketClient.connect();
        StompSession stompSession = f.get();
        myWebSocketClient.sendHello(stompSession, jsonMsg);
    }
}
