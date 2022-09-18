package com.javatechie.spring.ws.api.server.controller;

import com.javatechie.spring.ws.api.client.MyWebSocketClient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
public class ChatController {

//	@MessageMapping("/chat.register")
//	@SendTo("/topic/public")
//	public String register(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
//		headerAccessor.getSessionAttributes().put("message-log", message);
//		return message;
//	}

	@RequestMapping("/")
	public String home() {
		return "index.html";
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public String sendMessage(@Payload String message) {
		return message;
	}

}
