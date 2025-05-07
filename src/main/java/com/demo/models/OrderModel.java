package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;
import com.demo.entities.Users;

import DB.ConnectDB;

public class OrderModel {

	public List<Orders> findAll() {
		List<Orders> orders = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from orders");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Orders order = new Orders();
				order.setId(resultSet.getInt("id"));
				order.setPhoneNumber(resultSet.getString("phoneNumber"));
				order.setEmail(resultSet.getString("email"));
				order.setNote(resultSet.getString("note"));
				order.setOrderDate(resultSet.getTimestamp("orderDate"));
				order.setTotalMoney(resultSet.getDouble("totalMoney"));
				order.setStatus(resultSet.getInt("status"));
				order.setUserId(resultSet.getInt("userId"));
				order.setAddressId(resultSet.getInt("addressId"));
				order.setPublicKeyId(resultSet.getInt("publicKeyId"));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			orders = null;
		} finally {
			ConnectDB.disconnect();
		}
		return orders;
	}

	public List<Users> findUsersWithOrdersByDateRange(Timestamp startDate, Timestamp endDate) {
		List<Users> usersWithOrders = new ArrayList<>();
		try {
			PreparedStatement stmt = ConnectDB.connection().prepareStatement(
					"SELECT DISTINCT u.* FROM Users u JOIN Orders o ON u.id = o.userId WHERE o.orderDate BETWEEN ? AND ?");
			stmt.setTimestamp(1, startDate);
			stmt.setTimestamp(2, endDate);

			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Users user = new Users();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("userName"));
				user.setFullName(resultSet.getString("fullName"));
				user.setEmail(resultSet.getString("email"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
				user.setImage(resultSet.getString("image"));
				user.setPassword(resultSet.getString("password"));
				user.setRoleId(resultSet.getInt("roleId"));
				user.setStatus(resultSet.getBoolean("status"));
				user.setGender(resultSet.getString("gender"));
				user.setBirthday(resultSet.getDate("birthday"));
				usersWithOrders.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersWithOrders;
	}

	public List<Orders> findOrdersByDateRange(Timestamp startDate, Timestamp endDate) {
		List<Orders> orders = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"SELECT * FROM orders WHERE orderDate BETWEEN ? AND ?");
			preparedStatement.setTimestamp(1, startDate);
			preparedStatement.setTimestamp(2, endDate);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Orders order = new Orders();
				order.setId(resultSet.getInt("id"));
				order.setPhoneNumber(resultSet.getString("phoneNumber"));
				order.setEmail(resultSet.getString("email"));
				order.setNote(resultSet.getString("note"));
				order.setOrderDate(resultSet.getTimestamp("orderDate"));
				order.setTotalMoney(resultSet.getDouble("totalMoney"));
				order.setStatus(resultSet.getInt("status"));
				order.setUserId(resultSet.getInt("userId"));
				order.setAddressId(resultSet.getInt("addressId"));
				order.setPublicKeyId(resultSet.getInt("publicKeyId"));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			orders = null;
		} finally {
			ConnectDB.disconnect();
		}
		return orders;
	}

	public double calculateTotalMoney(Timestamp startDate, Timestamp endDate) {
		double total = 0.0;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"SELECT SUM(totalMoney) FROM orders WHERE orderDate BETWEEN ? AND ?");
			preparedStatement.setTimestamp(1, startDate);
			preparedStatement.setTimestamp(2, endDate);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				total = resultSet.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.disconnect();
		}
		return total;
	}

	public boolean create(Orders order) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"INSERT INTO orders(phoneNumber, email, note, orderDate, totalMoney, status, userId, addressId, publicKeyId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, order.getPhoneNumber());
			preparedStatement.setString(2, order.getEmail());
			preparedStatement.setString(3, order.getNote());
			preparedStatement.setTimestamp(4, new Timestamp(order.getOrderDate().getTime()));
			preparedStatement.setDouble(5, order.getTotalMoney());
			preparedStatement.setInt(6, order.getStatus());
			preparedStatement.setInt(7, order.getUserId());
			preparedStatement.setInt(8, order.getAddressId());
			preparedStatement.setInt(9, order.getPublicKeyId());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean update(Orders order) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"UPDATE orders SET phoneNumber = ?, email = ?, note = ?, orderDate = ?, totalMoney = ?, status = ?, userId = ?, addressId = ?, publicKeyId = ? WHERE id = ?");
			preparedStatement.setString(1, order.getPhoneNumber());
			preparedStatement.setString(2, order.getEmail());
			preparedStatement.setString(3, order.getNote());
			preparedStatement.setTimestamp(4, new Timestamp(order.getOrderDate().getTime()));
			preparedStatement.setDouble(5, order.getTotalMoney());
			preparedStatement.setInt(6, order.getStatus());
			preparedStatement.setInt(7, order.getUserId());
			preparedStatement.setInt(8, order.getAddressId());
			preparedStatement.setInt(9, order.getPublicKeyId());
			preparedStatement.setInt(10, order.getId());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"DELETE FROM orders WHERE id = ?");
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

	public Orders findOrderById(int id) {
		Orders order = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"SELECT * FROM orders WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Orders();
				order.setId(resultSet.getInt("id"));
				order.setPhoneNumber(resultSet.getString("phoneNumber"));
				order.setEmail(resultSet.getString("email"));
				order.setNote(resultSet.getString("note"));
				order.setOrderDate(resultSet.getTimestamp("orderDate"));
				order.setTotalMoney(resultSet.getDouble("totalMoney"));
				order.setStatus(resultSet.getInt("status"));
				order.setUserId(resultSet.getInt("userId"));
				order.setAddressId(resultSet.getInt("addressId"));
				order.setPublicKeyId(resultSet.getInt("publicKeyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			order = null;
		} finally {
			ConnectDB.disconnect();
		}
		return order;
	}

	public Orders getLastOrder() {
		Orders order = null;
		try {
			PreparedStatement ps = ConnectDB.connection().prepareStatement(
					"SELECT * FROM orders ORDER BY id DESC LIMIT 1");
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				order = new Orders();
				order.setId(resultSet.getInt("id"));
				order.setPhoneNumber(resultSet.getString("phoneNumber"));
				order.setEmail(resultSet.getString("email"));
				order.setNote(resultSet.getString("note"));
				order.setOrderDate(resultSet.getTimestamp("orderDate"));
				order.setTotalMoney(resultSet.getDouble("totalMoney"));
				order.setStatus(resultSet.getInt("status"));
				order.setUserId(resultSet.getInt("userId"));
				order.setAddressId(resultSet.getInt("addressId"));
				order.setPublicKeyId(resultSet.getInt("publicKeyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectDB.disconnect();
		}
		return order;
	}

	public List<Orders> findAllByUserId(int id) {
		List<Orders> orders = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"SELECT * FROM orders WHERE userId = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Orders order = new Orders();
				order.setId(resultSet.getInt("id"));
				order.setPhoneNumber(resultSet.getString("phoneNumber"));
				order.setEmail(resultSet.getString("email"));
				order.setNote(resultSet.getString("note"));
				order.setOrderDate(resultSet.getTimestamp("orderDate"));
				order.setTotalMoney(resultSet.getDouble("totalMoney"));
				order.setStatus(resultSet.getInt("status"));
				order.setUserId(resultSet.getInt("userId"));
				order.setAddressId(resultSet.getInt("addressId"));
				order.setPublicKeyId(resultSet.getInt("publicKeyId"));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			orders = null;
		} finally {
			ConnectDB.disconnect();
		}
		return orders;
	}

	public List<OrderDetails> getAllOrderdetails() {
		List<OrderDetails> ordersList = new ArrayList<>();
		try {
			PreparedStatement ps = ConnectDB.connection().prepareStatement("SELECT * FROM orderdetails");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setId(rs.getInt("id"));
				orderDetails.setOrderId(rs.getInt("orderId"));
				orderDetails.setPetId(rs.getInt("petId"));
				orderDetails.setMoney(rs.getInt("money"));
				orderDetails.setQuantity(rs.getInt("quantity"));
				ordersList.add(orderDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	public static void main(String[] args) {
		OrderModel orderModel = new OrderModel();
		System.out.println(orderModel.calculateTotalMoney(
				Timestamp.valueOf("2024-07-14 00:00:00"),
				Timestamp.valueOf("2024-07-20 23:59:59")));
	}
}
