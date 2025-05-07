package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.entities.Address;
import com.demo.entities.Users;

import DB.ConnectDB;

public class AddressModel {
	public boolean create(Address address) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"insert into address(country, district,ward,address, idUser) values (?,?,?,?,?)");
			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getDistrict());
			preparedStatement.setString(3, address.getWard());
			preparedStatement.setString(4, address.getAddress());
			preparedStatement.setInt(5, address.getIdUser());
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
	public boolean update(Address address) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update address set country = ?, district = ?, "
							+ "ward = ?, address = ?, idUser = ?"
							+ " where id = ? ");
			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getDistrict());
			preparedStatement.setString(3, address.getWard());
			preparedStatement.setString(4, address.getAddress());
			preparedStatement.setInt(5, address.getIdUser());
			preparedStatement.setInt(6, address.getId());
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	// tim address dua vao idUser(dung cho information)-> cai dau tien
		public Address findAddressByIdUser(int idUser) {
			Address address = null;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("select * from address where idUser = ? limit 1");
				preparedStatement.setInt(1, idUser);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					address = new Address();
					address.setId(resultSet.getInt("id"));
					address.setCountry(resultSet.getString("country"));
					address.setDistrict(resultSet.getString("district"));
					address.setWard(resultSet.getString("ward"));
					address.setAddress(resultSet.getString("address"));
					address.setIdUser(resultSet.getInt("idUser"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				address = null;
			} finally {
				ConnectDB.disconnect();
			}
			return address;
		}
		// tim address dua vao idAddress
		public Address findAddressById(int id) {
			Address address = null;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("select * from address where id = ?");
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					address = new Address();
					address.setId(resultSet.getInt("id"));
					address.setCountry(resultSet.getString("country"));
					address.setDistrict(resultSet.getString("district"));
					address.setWard(resultSet.getString("ward"));
					address.setAddress(resultSet.getString("address"));
					address.setIdUser(resultSet.getInt("idUser"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				address = null;
			} finally {
				ConnectDB.disconnect();
			}
			return address;
		}
		
		public static void main(String[] args) {
			System.out.println(new AddressModel().findAddressByIdUser(5));
		}
}
