package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.Categorys;
import com.demo.entities.Pets;

import DB.ConnectDB;

public class CategoryModel {
	public List<Categorys> findAll() {
		List<Categorys> categories = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from categorys");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Categorys category = new Categorys();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
			categories = null;
		} finally {
			ConnectDB.disconnect();
		}
		return categories;
	}
	// ham tim ra pets dua vao id
		public Categorys findCategoryById(int id) {
			Categorys category = null;
			try {
				PreparedStatement preparedStatement = ConnectDB.connection()
						.prepareStatement("select * from categorys where id = ? ");
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					category = new Categorys();
					category.setId(resultSet.getInt("id"));
					category.setName(resultSet.getString("name"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				category = null;
			} finally {
				ConnectDB.disconnect();
			}
			return category;
		}
		public boolean checkExist(int categoryId, List<Categorys> categories) {
			for (Categorys category : categories) {
				if(category.getId() == categoryId)
					return true;
			}
			return false;
		}
}
