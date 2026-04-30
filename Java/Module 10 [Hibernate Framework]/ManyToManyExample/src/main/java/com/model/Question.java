package com.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="question")
public class Question 
{
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	@Column(name="question_id")
	int id;
	
	@Column(name="question_name")
	String q_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQ_name() {
		return q_name;
	}

	public void setQ_name(String q_name) {
		this.q_name = q_name;
	}
	
	
	@ManyToMany(targetEntity = Answer.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "question_answer", joinColumns = { @JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "ans_id") })	
	
	private List<Answer> answers;

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}