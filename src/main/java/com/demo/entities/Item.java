package com.demo.entities;

public class Item {
private Pets pet;
private int quantity;
public Item(Pets pet, int quantity) {
	super();
	this.pet = pet;
	this.quantity = quantity;
}

public Item() {
	super();
}

public Pets getPet() {
	return pet;
}
public void setPet(Pets pet) {
	this.pet = pet;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString() {
	return "Item [pet=" + pet + ", quantity=" + quantity + "]";
}

}
