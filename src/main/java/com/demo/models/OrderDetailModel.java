package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;

import DB.ConnectDB;

public class OrderDetailModel {
	// ham lay ra danh sach cac order
			public List<OrderDetails> findAll() {
				List<OrderDetails> orderDetails = new ArrayList<>();
				try {
					PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from orderdetails");
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						OrderDetails orderDetail = new OrderDetails();
						orderDetail.setId(resultSet.getInt("id"));
						orderDetail.setQuantity(resultSet.getInt("quantity"));
						orderDetail.setOrderId(resultSet.getInt("orderId"));
						orderDetail.setPetId(resultSet.getInt("petId"));
						orderDetail.setMoney(resultSet.getDouble("money"));
						orderDetails.add(orderDetail);
					}
				} catch (Exception e) {
					e.printStackTrace();
					orderDetails = null;
				} finally {
					ConnectDB.disconnect();
				}
				return orderDetails;
			}
// ham tra ve so luong chi tiet don hang dc ban ra theo thoi gian
			 public int getQuantityByDateRange(Timestamp startDate, Timestamp endDate) {
			        int totalQuantity = 0;
			        try {
			            PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("SELECT SUM(quantity) FROM orderDetails WHERE orderId IN " +
			                       "(SELECT id FROM orders WHERE orderDate BETWEEN ? AND ?)");
			            preparedStatement.setTimestamp(1, startDate);
			            preparedStatement.setTimestamp(2, endDate);
			            ResultSet resultSet = preparedStatement.executeQuery();

			            if (resultSet.next()) {
			                totalQuantity = resultSet.getInt(1);
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
			            ConnectDB.disconnect();
			        }
			        return totalQuantity;
			    }
		// ham dang ky
			public boolean create(OrderDetails orderDetail) {
				boolean result = true;
				try {
					PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
							"insert into orderdetails(quantity, orderId,petId,money) values (?,?,?,?)");
					preparedStatement.setInt(1, orderDetail.getQuantity());
					preparedStatement.setDouble(2, orderDetail.getOrderId());
					preparedStatement.setInt(3, orderDetail.getPetId());
					preparedStatement.setDouble(4, orderDetail.getMoney());
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
			public boolean update(OrderDetails orderDetail) {
				boolean result = true;
				try {
					PreparedStatement preparedStatement = ConnectDB.connection()
							.prepareStatement("update orderdetails set quantity = ?, orderId = ?, "
									+ "petId = ?, money = ?"
									+ " where id = ? ");
					preparedStatement.setInt(1, orderDetail.getQuantity());
					preparedStatement.setDouble(2, orderDetail.getOrderId());
					preparedStatement.setInt(3, orderDetail.getPetId());
					preparedStatement.setDouble(4, orderDetail.getMoney());
					preparedStatement.setInt(5, orderDetail.getId());
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
							.prepareStatement("delete from orderdetails where id = ? ");
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

			// tim order dua vao id cua orderdetail
			public OrderDetails findOrderById(int id) {
				OrderDetails orderDetail = null;
				try {
					PreparedStatement preparedStatement = ConnectDB.connection()
							.prepareStatement("select * from orderdetails where id = ? ");
					preparedStatement.setInt(1, id);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						orderDetail = new OrderDetails();
						orderDetail.setId(resultSet.getInt("id"));
						orderDetail.setQuantity(resultSet.getInt("quantity"));
						orderDetail.setOrderId(resultSet.getInt("orderId"));
						orderDetail.setPetId(resultSet.getInt("petId"));
						orderDetail.setMoney(resultSet.getDouble("money"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					orderDetail = null;
				} finally {
					ConnectDB.disconnect();
				}
				return orderDetail;
			}
			// lay ra danh sach orderdetails cua order dua vao id order
			public List<OrderDetails> findAllByOrderId(int orderId) {
				List<OrderDetails> orderDetails = new ArrayList<>();
				try {
					PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from orderdetails where orderId = ?");
					preparedStatement.setInt(1, orderId);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						OrderDetails orderDetail = new OrderDetails();
						orderDetail.setId(resultSet.getInt("id"));
						orderDetail.setQuantity(resultSet.getInt("quantity"));
						orderDetail.setOrderId(resultSet.getInt("orderId"));
						orderDetail.setPetId(resultSet.getInt("petId"));
						orderDetail.setMoney(resultSet.getDouble("money"));
						orderDetails.add(orderDetail);
					}
				} catch (Exception e) {
					e.printStackTrace();
					orderDetails = null;
				} finally {
					ConnectDB.disconnect();
				}
				return orderDetails;
			}
			public int quantity(int categoryID) {
				int quantity = 0;
				try {
					PreparedStatement ps = ConnectDB.connection().prepareStatement("SELECT sum(quantity) sum FROM orderdetails ord, pets p WHERE ord.petId = p.id and p.categoryId = ? ");
					ps.setInt(1, categoryID);
					ResultSet resultSet = ps.executeQuery();
					while(resultSet.next()) {
						quantity = resultSet.getInt("sum");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					quantity = 0;
				}
				return quantity;
			}
			public int quantity1() {
				int quantity = 0;
				try {
					PreparedStatement ps = ConnectDB.connection().prepareStatement("SELECT sum(quantity) sum FROM orderdetails ord, pets p WHERE ord.petId = p.id ");
					ResultSet resultSet = ps.executeQuery();
					while(resultSet.next()) {
						quantity = resultSet.getInt("sum");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					quantity = 0;
				}
				return quantity;
			}
			// lay ra so luong ctdh cua don hang: 
			 public int getQuantityByOrderId(int orderId) {
			        int quantity = 0;
			        String sql = "SELECT SUM(quantity) AS totalQuantity FROM orderdetails WHERE orderId = ?";
			        try {
			            PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(sql);
			            preparedStatement.setInt(1, orderId);
			            ResultSet resultSet = preparedStatement.executeQuery();
			            if (resultSet.next()) {
			                quantity = resultSet.getInt("totalQuantity");
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
			            ConnectDB.disconnect();
			        }
			        return quantity;
			    }
			 /// so luong ctdh theo tung loai: 
			 public int quantityByCategory(int orderId, int categoryId) {
			        int quantity = 0;
			        String sql = "SELECT SUM(quantity) AS totalQuantity FROM orderdetails " +
			                     "JOIN pets ON orderdetails.petId = pets.id WHERE orderId = ? AND categoryId = ?";
			        try {
			            PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(sql);
			            preparedStatement.setInt(1, orderId);
			            preparedStatement.setInt(2, categoryId);
			            ResultSet resultSet = preparedStatement.executeQuery();
			            if (resultSet.next()) {
			                quantity = resultSet.getInt("totalQuantity");
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
			            ConnectDB.disconnect();
			        }
			        return quantity;
			    }
}
