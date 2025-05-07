package com.demo.entities;

public class Address {
private int id;
private String country;
private String district;
private String ward;
private String address;
private int idUser;
public Address(int id, String country, String district, String ward, String address, int idUser) {
	super();
	this.id = id;
	this.country = country;
	this.district = district;
	this.ward = ward;
	this.address = address;
	this.idUser = idUser;
}
public Address(String country, String district, String ward, String address, int idUser) {
	super();
	this.country = country;
	this.district = district;
	this.ward = ward;
	this.address = address;
	this.idUser = idUser;
}
public Address() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getWard() {
	return ward;
}
public void setWard(String ward) {
	this.ward = ward;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getIdUser() {
	return idUser;
}
public void setIdUser(int idUser) {
	this.idUser = idUser;
}
@Override
public String toString() {
	return "Address [id=" + id + ", country=" + country + ", district=" + district + ", ward=" + ward + ", address="
			+ address + ", idUser=" + idUser + "]";
}

}
