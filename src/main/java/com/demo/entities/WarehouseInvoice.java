package com.demo.entities;

import java.util.Date;

public class WarehouseInvoice {
	private int id;
	private int petId;
	private int quantity;
	private double price;
	private Date tradeDate;
	private boolean status;
	public WarehouseInvoice(int id, int petId, int quantity, double price, Date tradeDate, boolean status) {
		super();
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.price = price;
		this.tradeDate = tradeDate;
		this.status = status;
	}
	
	public WarehouseInvoice() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WarehouseInvoice [id=" + id + ", petId=" + petId + ", quantity=" + quantity + ", price=" + price
				+ ", tradeDate=" + tradeDate + ", status=" + status + "]";
	}

	
	
}
