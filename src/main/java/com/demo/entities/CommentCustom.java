package com.demo.entities;

import java.sql.Timestamp;

public class CommentCustom {
	private int id;
	private String userId;
	private int petId;
	private String note;
	private Timestamp createDate;
	public CommentCustom(int id, String userId, int petId, String note, Timestamp createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.petId = petId;
		this.note = note;
		this.createDate = createDate;
	}
	public CommentCustom(String userId, int petId, String note, Timestamp createDate) {
		super();
		this.userId = userId;
		this.petId = petId;
		this.note = note;
		this.createDate = createDate;
	}
	public CommentCustom() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "CommentCustom [id=" + id + ", userId=" + userId + ", petId=" + petId + ", note=" + note
				+ ", createDate=" + createDate + "]";
	}
	
	
}
