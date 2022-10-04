package com.javatechie.spring.ws.api.client;


import org.springframework.messaging.simp.stomp.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.util.Collections;
import java.util.List;

public class MyWebSocketClient {

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    public ListenableFuture<StompSession> connect() {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/wshost";
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
    }


    /**Message handler into client if you didn't config it on the server side*/

//    public void subscribeGreetings(StompSession stompSession) throws ExecutionException, InterruptedException {
//        stompSession.subscribe("/topic/greetings", new StompFrameHandler() {
//
//            public Type getPayloadType(StompHeaders stompHeaders) {
//                return byte[].class;
//            }
//
//            public void handleFrame(StompHeaders stompHeaders, Object o) {
//                logger.info("Received greeting " + new String((byte[]) o));
//            }
//        });
//    }

    public void sendHello(StompSession stompSession, String jsonMsg) {
        stompSession.send("/app/chat.send", jsonMsg.getBytes());
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            System.out.println("Now connected");
        }
    }

}
