package com.example.campusbot.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.campusbot.dto.AnswerResponse;
import com.example.campusbot.dto.QuestionRequest;
import com.example.campusbot.service.AIService;

@RestController
public class ChatController {
	@Autowired
	private AIService service;

	@GetMapping("/")
	public String home() {
		return "CampusBot AI FAQ Service Running";
	}

	@PostMapping("/ask-question")
	public AnswerResponse askQuestion(@RequestBody QuestionRequest request) {
		return new AnswerResponse(service.getAnswer(request.getQuestion()), "SUCCESS", LocalDateTime.now().toString());
	}
}