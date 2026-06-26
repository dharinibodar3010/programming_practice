package com.example.campusbot.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {
	public String getAnswer(String question) {
		if (question == null)
			return "Please ask a question.";

		if (question.toLowerCase().contains("java"))
			return "Java is a programming language.";

		if (question.toLowerCase().contains("spring"))
			return "Spring Boot is a Java framework.";

		if (question.toLowerCase().contains("ai"))
			return "AI stands for Artificial Intelligence.";
		
		return "Sorry, I don't know the answer.";
	}
}