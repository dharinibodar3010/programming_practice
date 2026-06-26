package com.example.campusbot.model;

public class AnswerResponse {
	private String answer;
	private String status;
	private String timestamp;

	public AnswerResponse() {
	}

	public AnswerResponse(String answer, String status, String timestamp) {
		this.answer = answer;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getAnswer() {
		return answer;
	}

	public String getStatus() {
		return status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}