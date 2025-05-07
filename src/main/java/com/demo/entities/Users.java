package com.demo.entities;

import java.util.Date;

public class Users {
private int id;
private String userName;
private String fullName;
private String email;
private String phoneNumber;
private String image;
private String password;
private int roleId;
private boolean status;
private String gender;
private Date birthday;
private String securityCode;


public Users(int id, String userName, String fullName, String email, String phoneNumber, String image,
		String password, int roleId, boolean status, String gender, Date birthday, String securityCode) {
	super();
	this.id = id;
	this.userName = userName;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.image = image;
	this.password = password;
	this.roleId = roleId;
	this.status = status;
	this.gender = gender;
	this.birthday = birthday;
	this.securityCode = securityCode;
}

public Users(String userName, String fullName, String email, String phoneNumber, String image,
		String password, int roleId, boolean status, String gender, Date birthday, String securityCode) {
	super();
	this.userName = userName;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.image = image;
	this.password = password;
	this.roleId = roleId;
	this.status = status;
	this.gender = gender;
	this.birthday = birthday;
	this.securityCode = securityCode;
}

public Users() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getSecurityCode() {
	return securityCode;
}
public void setSecurityCode(String securityCode) {
	this.securityCode = securityCode;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
@Override
public String toString() {
	return "Users [id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", password=" + password
			+ ", gender=" + gender + ", birthday=" + birthday + ", email=" + email + ", phoneNumber=" + phoneNumber
			+ ", image=" + image + ", securityCode=" + securityCode + ", status=" + status
			+ ", roleId=" + roleId + "]";
}



}
