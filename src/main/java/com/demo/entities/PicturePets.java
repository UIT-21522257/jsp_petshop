package com.demo.entities;

import java.util.Date;

public class PicturePets {
private int id;
private String image;
private int petId;
private Date created;

public PicturePets(int id, String image, int petId, Date created) {
	super();
	this.id = id;
	this.image = image;
	this.petId = petId;
	this.created = created;
}
public PicturePets() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getPetId() {
	return petId;
}
public void setPetId(int petId) {
	this.petId = petId;
}

public Date getCreated() {
	return created;
}
public void setCreated(Date created) {
	this.created = created;
}
@Override
public String toString() {
	return "PicturePets [id=" + id + ", image=" + image + ", petId=" + petId + ", created=" + created + "]";
}


}
