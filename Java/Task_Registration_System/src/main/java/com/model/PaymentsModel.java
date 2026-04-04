package com.model;

public class PaymentsModel {

	int id;
	String enrollment_id, amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnrollment_id() {
		return enrollment_id;
	}
	public void setEnrollment_id(String enrollment_id) {
		this.enrollment_id = enrollment_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
