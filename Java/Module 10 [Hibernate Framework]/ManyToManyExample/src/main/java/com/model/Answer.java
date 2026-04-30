package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Answer")
public class Answer 
{
	@Id
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	@Column(name="ans_id")
	int id;
	
	@Column(name="ans")
	String ans;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	
	
}