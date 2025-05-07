package com.demo.entities;

import java.util.Date;

public class Comments {
private int id;
private int userId;
private int petId;
private String note;
private Date createDate;
public Comments(int id, int userId, int petId, String note, Date createDate) {
	super();
	this.id = id;
	this.userId = userId;
	this.petId = petId;
	this.note = note;
	this.createDate = createDate;
}
public Comments() {
	super();
}
public Comments(int userId, int petId, String note, Date createDate) {
	super();
	this.userId = userId;
	this.petId = petId;
	this.note = note;
	this.createDate = createDate;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getPetId() {
	return petId;
}
public void setPetId(int petId) {
	this.petId = petId;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
@Override
public String toString() {
	return "Reviews [id=" + id + ", userId=" + userId + ", petId=" + petId + ", note=" + note + ", createDate="
			+ createDate + "]";
}



}
