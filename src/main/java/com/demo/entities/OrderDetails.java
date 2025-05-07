package com.demo.entities;

public class OrderDetails {
	private int id;
	private int quantity;
	private int orderId;
	private int petId;
	private double money;
public OrderDetails(int id, int quantity, int orderId, int petId, double money) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.orderId = orderId;
		this.petId = petId;
		this.money = money;
	}


public OrderDetails(int quantity, int orderId, int petId, double money) {
	super();
	this.quantity = quantity;
	this.orderId = orderId;
	this.petId = petId;
	this.money = money;
}


public OrderDetails() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getPetId() {
	return petId;
}
public void setPetId(int petId) {
	this.petId = petId;
}
public double getMoney() {
	return money;
}
public void setMoney(double money) {
	this.money = money;
}


@Override
public String toString() {
	return "OrderDetails [id=" + id + ", quantity=" + quantity + ", orderId=" + orderId + ", petId=" + petId
			+ ", money=" + money + "]";
}








}
