package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.Roles;

import DB.ConnectDB;

public class RoleModel {
	// ham lay ra danh sach cac role
		public List<Roles> findAll() {
			List<Roles> roles = new ArrayList<>();
			try {
				PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from roles");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Roles role = new Roles();
					role.setId(resultSet.getInt("id"));
					role.setName(resultSet.getString("name"));
					roles.add(role);

				}
			} catch (Exception e) {
				e.printStackTrace();
				roles = null;
			} finally {
				ConnectDB.disconnect();
			}
			return roles;
		}
	// check su ton tai cua roleID
		public boolean checkRoleIDExists(int roleId) {
			for (Roles r: findAll()) {
				if(r.getId() == roleId) {
					return true;
				}
			}
			return false;
		}
		public static void main(String[] args) {
			RoleModel roleModel = new RoleModel();
//			System.out.println(roleModel.findAll());
		}
}
