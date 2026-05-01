package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile1") //one-to-one
public class Profile {
	
	@Id
	@GeneratedValue(generator = "increment")
	
	@Column(name = "profile_id")
	int id;
	
	@Column(name ="profile_address")
	String address;
	
	@Column(name = "profile_phone")
	String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
