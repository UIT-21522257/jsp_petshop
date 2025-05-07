package com.demo.entities;

import java.util.Date;

public class Pets {
private int id;
private String petName;
private String petType;
private String petGender;
private String description;
private String detail;
private String made;
private int amount;
private double money;
private Date petBirthday;
private String image;
private boolean status;
private int categoryId;
private int catalogId;
public Pets(int id, String petName, String petType, String petGender, String description, String detail, String made,
		int amount, double money, Date petBirthday, String image,boolean status, int categoryId, int catalogId) {
	super();
	this.id = id;
	this.petName = petName;
	this.petType = petType;
	this.petGender = petGender;
	this.description = description;
	this.detail = detail;
	this.made = made;
	this.amount = amount;
	this.money = money;
	this.petBirthday = petBirthday;
	this.image = image;
	this.status =status;
	this.categoryId = categoryId;
	this.catalogId = catalogId;
}

public Pets(String petName, String petType, String petGender, String description, String detail, String made,
		int amount, double money, Date petBirthday, String image, boolean status, int categoryId, int catalogId) {
	super();
	this.petName = petName;
	this.petType = petType;
	this.petGender = petGender;
	this.description = description;
	this.detail = detail;
	this.made = made;
	this.amount = amount;
	this.money = money;
	this.petBirthday = petBirthday;
	this.image = image;
	this.status = status;
	this.categoryId = categoryId;
	this.catalogId = catalogId;
}

public Pets() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPetName() {
	return petName;
}
public void setPetName(String petName) {
	this.petName = petName;
}
public String getPetType() {
	return petType;
}
public void setPetType(String petType) {
	this.petType = petType;
}
public String getPetGender() {
	return petGender;
}
public void setPetGender(String petGender) {
	this.petGender = petGender;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public String getMade() {
	return made;
}
public void setMade(String made) {
	this.made = made;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public double getMoney() {
	return money;
}
public void setMoney(double money) {
	this.money = money;
}
public Date getPetBirthday() {
	return petBirthday;
}
public void setPetBirthday(Date petBirthday) {
	this.petBirthday = petBirthday;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public int getCatalogId() {
	return catalogId;
}
public void setCatalogId(int catalogId) {
	this.catalogId = catalogId;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

@Override
public String toString() {
	return "Pets [id=" + id + ", petName=" + petName + ", petType=" + petType + ", petGender=" + petGender
			+ ", description=" + description + ", detail=" + detail + ", made=" + made + ", amount=" + amount
			+ ", money=" + money + ", petBirthday=" + petBirthday + ", image=" + image + ", status=" + status
			+ ", categoryId=" + categoryId + ", catalogId=" + catalogId + "]";
}


}
