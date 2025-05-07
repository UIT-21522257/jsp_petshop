package com.demo.entities;

import java.sql.Timestamp;

public class Orders {
private int id;
private String phoneNumber;
private String email;
private String note;
private Timestamp orderDate;
private double totalMoney;
private int status;
private int userId;
private int addressId;
private int publicKeyId;

public Orders(String phoneNumber, String email, String note, Timestamp orderDate, double totalMoney, int status,
			  int userId, int addressId) {
	super();
	this.id = id;
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.note = note;
	this.orderDate = orderDate;
	this.totalMoney = totalMoney;
	this.status = status;
	this.userId = userId;
	this.addressId = addressId;
	this.publicKeyId = publicKeyId;
}

public Orders(String phoneNumber, String email, String note, Timestamp orderDate, double totalMoney, int status, int userId,
		int addressId, int publicKeyId) {
	super();
	this.phoneNumber = phoneNumber;
	this.email = email;
	this.note = note;
	this.orderDate = orderDate;
	this.totalMoney = totalMoney;
	this.status = status;
	this.userId = userId;
	this.addressId = addressId;
	this.publicKeyId = publicKeyId;

}

public Orders() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Timestamp getOrderDate() {
	return orderDate;
}
public void setOrderDate(Timestamp orderDate) {
	this.orderDate = orderDate;
}
public double getTotalMoney() {
	return totalMoney;
}
public void setTotalMoney(double totalMoney) {
	this.totalMoney = totalMoney;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

	public int getPublicKeyId() {
		return publicKeyId;
	}

	public void setPublicKeyId(int publicKeyId) {
		this.publicKeyId = publicKeyId;
	}

	@Override
public String toString() {
	return "Orders [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", note=" + note + ", orderDate="
			+ orderDate + ", totalMoney=" + totalMoney + ", status=" + status + ", userId=" + userId + ", addressId="
			+ addressId + "]";
}




}