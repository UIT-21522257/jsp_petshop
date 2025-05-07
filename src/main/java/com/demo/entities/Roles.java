package com.demo.entities;

public class Roles {
private int id;
private String name;
public Roles(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public Roles() {
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
@Override
public String toString() {
	return "Roles [id=" + id + ", name=" + name + "]";
}

}
