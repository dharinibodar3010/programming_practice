package com.example.campusbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String askQuestion(String question) {

        if(question.equalsIgnoreCase("What is Java?")) {
            return "Java is a programming language.";
        }

        if(question.equalsIgnoreCase("What is Spring Boot?")) {
            return "Spring Boot is a framework used for Java applications.";
        }

        if(question.equalsIgnoreCase("What is AI?")) {
            return "AI stands for Artificial Intelligence.";
        }

        return "Sorry, I don't know the answer.";
    }
}