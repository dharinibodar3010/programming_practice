package com.example.campusbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.campusbot.model.ChatRequest;
import com.example.campusbot.model.ChatResponse;
import com.example.campusbot.service.OpenAIService;

@RestController
@RequestMapping("/api")
public class ChatController {

	@Autowired
	private OpenAIService service;

	@PostMapping("/ask")
	public ChatResponse askQuestion(@RequestBody ChatRequest request) {

	    String answer = service.askQuestion(request.getQuestion());

	    return new ChatResponse(answer);
	}
}