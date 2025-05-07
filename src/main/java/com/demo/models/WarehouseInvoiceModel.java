package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.entities.Catalogs;
import com.demo.entities.Categorys;
import com.demo.entities.Pets;
import com.demo.entities.Users;
import com.demo.entities.WarehouseInvoice;

import DB.ConnectDB;

public class WarehouseInvoiceModel {
	public List<WarehouseInvoice> findAll() {
		List<WarehouseInvoice> invoices = new ArrayList<WarehouseInvoice>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from warehouseinvoice");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				WarehouseInvoice invoice = new WarehouseInvoice();
				invoice.setId(rs.getInt("id"));
				invoice.setPetId(rs.getInt("petId"));
				invoice.setQuantity(rs.getInt("quantity"));
				invoice.setPrice(rs.getDouble("price"));
				invoice.setTradeDate(rs.getDate("tradeDate"));
				invoice.setStatus(rs.getBoolean("status"));
				invoices.add(invoice);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			invoices = null;
			
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}
	public WarehouseInvoice findInvoiceByID(int ID) {
		WarehouseInvoice invoice = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from warehouseinvoice where id = ?");
			preparedStatement.setInt(1, ID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				invoice = new WarehouseInvoice();
				invoice.setId(rs.getInt("id"));
				invoice.setPetId(rs.getInt("petId"));
				invoice.setQuantity(rs.getInt("quantity"));
				invoice.setPrice(rs.getDouble("price"));
				invoice.setTradeDate(rs.getDate("tradeDate"));
				invoice.setStatus(rs.getBoolean("status"));
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			invoice = null;
			
		} finally {
			ConnectDB.disconnect();
		}
		return invoice;
	}
	public boolean create(WarehouseInvoice invoice) {
		boolean result = true;
		
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into warehouseinvoice (petId, quantity, price, tradeDate, status) "
							+ "values(?, ?, ?, ?,? )");
			preparedStatement.setInt(1, invoice.getPetId());
			preparedStatement.setInt(2, invoice.getQuantity());
			preparedStatement.setDouble(3, invoice.getPrice());
			preparedStatement.setDate(4, new java.sql.Date(invoice.getTradeDate().getTime()));
			preparedStatement.setBoolean(5, invoice.isStatus());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean update(WarehouseInvoice warehouseInvoice ) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update warehouseinvoice set petId=?,quantity = ?,price=?,tradeDate=?,status=? where id = ?");
			preparedStatement.setInt(1, warehouseInvoice.getPetId());
			preparedStatement.setInt(2, warehouseInvoice.getQuantity());
			preparedStatement.setDouble(3, warehouseInvoice.getPrice());
			preparedStatement.setDate(4, new java.sql.Date(warehouseInvoice.getTradeDate().getTime()));
			preparedStatement.setBoolean(5, warehouseInvoice.isStatus());
			preparedStatement.setInt(6, warehouseInvoice.getId());
			result = preparedStatement.executeUpdate() > 0;
			
		}catch(Exception e) {
			e.printStackTrace();
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("delete from warehouseinvoice where id = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate() > 0;
			
		}catch(Exception e) {
			e.printStackTrace();
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public static void main(String[] args) {
	}
}
