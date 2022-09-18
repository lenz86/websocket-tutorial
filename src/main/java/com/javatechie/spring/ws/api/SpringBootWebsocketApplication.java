package com.javatechie.spring.ws.api;

import com.javatechie.spring.ws.api.client.RunJavaWsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SpringBootWebsocketApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        SpringApplication.run(SpringBootWebsocketApplication.class, args);
        RunJavaWsClient.startClient();
    }

}

