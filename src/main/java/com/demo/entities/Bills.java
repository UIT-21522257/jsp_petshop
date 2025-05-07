package com.demo.entities;

import java.sql.Timestamp;

public class Bills {
private int id;
private int orderId;
private int paymentMethod;
private boolean status;
private Timestamp createDate;

public Bills(int id, int orderId, int paymentMethod, boolean status, Timestamp createDate) {
	super();
	this.id = id;
	this.orderId = orderId;
	this.paymentMethod = paymentMethod;
	this.status = status;
	this.createDate = createDate;
}

public Bills() {
	super();
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}

public int getPaymentMethod() {
	return paymentMethod;
}

public void setPaymentMethod(int paymentMethod) {
	this.paymentMethod = paymentMethod;
}

public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}

public Timestamp getCreateDate() {
	return createDate;
}

public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}

@Override
public String toString() {
	return "Bills [id=" + id + ", orderId=" + orderId + ", paymentMethod=" + paymentMethod + ", status=" + status
			+ ", createDate=" + createDate + "]";
}

}
