package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.Bills;
import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;
import com.demo.entities.Users;

import DB.ConnectDB;

public class BillModel {
	// ham lay ra danh sach cac order
		public List<Bills> findAll() {
			List<Bills> bills = new ArrayList<>();
			try {
				PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from bills");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Bills bill = new Bills();
					bill.setId(resultSet.getInt("id"));
					bill.setOrderId(resultSet.getInt("orderId"));
					bill.setPaymentMethod(resultSet.getInt("paymentMethod"));
					bill.setStatus(resultSet.getBoolean("status"));
					bill.setCreateDate(resultSet.getTimestamp("createDate"));
					bills.add(bill);

				}
			} catch (Exception e) {
				e.printStackTrace();
				bills = null;
			} finally {
				ConnectDB.disconnect();
			}
			return bills;
		}

	// ham dang ky
		public boolean create(Bills bill) {
			boolean result = true;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
						"insert into bills(orderId, paymentMethod,status,createDate) values (?,?,?,?)");
				preparedStatement.setInt(1, bill.getOrderId());
				preparedStatement.setInt(2, bill.getPaymentMethod());
				preparedStatement.setBoolean(3, bill.isStatus());
				preparedStatement.setTimestamp(4, new Timestamp(bill.getCreateDate().getTime()));
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

	// ham update
		public boolean update(Bills bill) {
			boolean result = true;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("update bills set orderId = ?, paymentMethod = ?, "
								+ "status = ?, createDate = ?"
								+ " where id = ? ");
				preparedStatement.setInt(1, bill.getOrderId());
				preparedStatement.setInt(2, bill.getPaymentMethod());
				preparedStatement.setBoolean(3, bill.isStatus());
				preparedStatement.setTimestamp(4, new Timestamp(bill.getCreateDate().getTime()));
				preparedStatement.setInt(5, bill.getId());
				result = preparedStatement.executeUpdate() > 0;
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			} finally {
				ConnectDB.disconnect();
			}
			return result;
		}

	// ham xoa user
		public boolean delete(int id) {
			boolean result = true;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("delete from bills where id = ? ");
				preparedStatement.setInt(1, id);
				result = preparedStatement.executeUpdate() > 0;
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			} finally {
				ConnectDB.disconnect();
			}
			return result;
		}

		// tim order dua vao id cua bill
		public Bills findBillById(int id) {
			Bills bill = null;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("select * from bills where id = ? ");
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					bill = new Bills();
					bill.setId(resultSet.getInt("id"));
					bill.setOrderId(resultSet.getInt("orderId"));
					bill.setPaymentMethod(resultSet.getInt("paymentMethod"));
					bill.setStatus(resultSet.getBoolean("status"));
					bill.setCreateDate(resultSet.getTimestamp("createDate"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				bill = null;
			} finally {
				ConnectDB.disconnect();
			}
			return bill;
		}
		public Bills findBillByOrderId(int orderId) {
			Bills bill = null;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("select * from bills where orderId = ? ");
				preparedStatement.setInt(1, orderId);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					bill = new Bills();
					bill.setId(resultSet.getInt("id"));
					bill.setOrderId(resultSet.getInt("orderId"));
					bill.setPaymentMethod(resultSet.getInt("paymentMethod"));
					bill.setStatus(resultSet.getBoolean("status"));
					bill.setCreateDate(resultSet.getTimestamp("createDate"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				bill = null;
			} finally {
				ConnectDB.disconnect();
			}
			return bill;
		}
		// ham de lay ra order cuoi cung
		public Bills getLastOrder() {
			Bills bill = new Bills();
			try {

				PreparedStatement ps = ConnectDB.connection().prepareStatement("SELECT * FROM bills order by id desc limit 1");


				ResultSet resultSet = ps.executeQuery();

				while (resultSet.next()) {
					bill = new Bills();
					bill.setId(resultSet.getInt("id"));
					bill.setOrderId(resultSet.getInt("orderId"));
					bill.setPaymentMethod(resultSet.getInt("paymentMethod"));
					bill.setStatus(resultSet.getBoolean("status"));
					bill.setCreateDate(resultSet.getTimestamp("createDate"));
					return bill;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		// ham lay ra danh sach bill cua nguoi dung
		public List<Bills> findAllByUserId(int userId) {
		    List<Bills> bills = new ArrayList<>();
		    try {
		        // Sử dụng JOIN để lấy dữ liệu từ cả hai bảng
		        String query = "SELECT b.* FROM bills b " +
		                       "JOIN orders o ON b.orderId = o.id " +
		                       "WHERE o.userId = ?";
		        PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(query);
		        preparedStatement.setInt(1, userId);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        while (resultSet.next()) {
		            Bills bill = new Bills();
		            bill.setId(resultSet.getInt("id"));
		            bill.setOrderId(resultSet.getInt("orderId"));
		            bill.setPaymentMethod(resultSet.getInt("paymentMethod"));
		            bill.setStatus(resultSet.getBoolean("status"));
		            bill.setCreateDate(resultSet.getTimestamp("createDate"));
		            bills.add(bill);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        bills = null;
		    } finally {
		        ConnectDB.disconnect();
		    }
		    return bills;
		}
public static void main(String[] args) {
	BillModel billModel = new BillModel();
	System.out.println(billModel.findBillById(35));
}

}
