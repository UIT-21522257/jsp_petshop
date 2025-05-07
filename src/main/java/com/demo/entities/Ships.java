package com.demo.entities;

import java.util.Date;

public class Ships {
private int id;
private Date shipDate;
private String deliveryAddress;
private int billId;
private int status; // dang dong goi, dang ship, hoan thanh ...
public Ships(int id, Date shipDate, String deliveryAddress, int billId, int status) {
	super();
	this.id = id;
	this.shipDate = shipDate;
	this.deliveryAddress = deliveryAddress;
	this.billId = billId;
	this.status = status;
}
public Ships() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getShipDate() {
	return shipDate;
}
public void setShipDate(Date shipDate) {
	this.shipDate = shipDate;
}
public String getDeliveryAddress() {
	return deliveryAddress;
}
public void setDeliveryAddress(String deliveryAddress) {
	this.deliveryAddress = deliveryAddress;
}
public int getBillId() {
	return billId;
}
public void setBillId(int billId) {
	this.billId = billId;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
@Override
public String toString() {
	return "Ships [id=" + id + ", shipDate=" + shipDate + ", deliveryAddress=" + deliveryAddress + ", billId=" + billId
			+ ", status=" + status + "]";
}

}
