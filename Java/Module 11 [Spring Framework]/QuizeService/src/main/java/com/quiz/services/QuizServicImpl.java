package com.quiz.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;

@Service
public class QuizServicImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	public QuizRepository getQuizRepository() {
		return quizRepository;
	}

	public void setQuizRepository(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}

	@Autowired
	private QuestionClient questionClient;

	public QuestionClient getQuestionClient() {
		return questionClient;
	}

	public void setQuestionClient(QuestionClient questionClient) {
		this.questionClient = questionClient;
	}

	@Override
	public Quiz add(Quiz quiz) {
		// TODO Auto-generated method stub
		return quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> get() {
		// TODO Auto-generated method stub
		List<Quiz> quizzes = quizRepository.findAll();

		List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
			quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList());

		return newQuizList;
	}

	@Override
	public Quiz get(Long id) {
		// TODO Auto-generated method stub
		Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
		quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		return quiz;
	}

}
