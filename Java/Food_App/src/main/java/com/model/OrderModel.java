package com.model;

import java.sql.Timestamp;

public class OrderModel {
    int id;
    int userId;
    double totalAmount;
    String paymentMethod;
    String status;
    Timestamp orderDate;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }

	public double getTotalAmount() { return totalAmount; }
	public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

	public String getPaymentMethod() { return paymentMethod; }
	public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public Timestamp getOrderDate() { return orderDate; }
	public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
}

