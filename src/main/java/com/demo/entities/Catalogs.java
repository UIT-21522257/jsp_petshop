package com.demo.entities;

public class Catalogs {
private int id;
private String name;
private int categoryId;
public Catalogs(int id, String name, int categoryId) {
	super();
	this.id = id;
	this.name = name;
	this.categoryId = categoryId;
}
public Catalogs() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
@Override
public String toString() {
	return "Catalogs [id=" + id + ", name=" + name + ", categoryId=" + categoryId + "]";
}

}
