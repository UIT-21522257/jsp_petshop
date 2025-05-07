package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demo.entities.Catalogs;
import com.demo.entities.Categorys;
import com.demo.entities.Users;

import DB.ConnectDB;

public class CatalogModel {
	public List<Catalogs> findAll() {
		List<Catalogs> catalogs = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from catalogs");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Catalogs catalog = new Catalogs();
				catalog.setId(resultSet.getInt("id"));
				catalog.setName(resultSet.getString("name"));
				catalog.setCategoryId(resultSet.getInt("categoryId"));
				catalogs.add(catalog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			catalogs = null;
		} finally {
			ConnectDB.disconnect();
		}
		return catalogs;
	}

	public List<Catalogs> findAllByCategory(int id) {
		List<Catalogs> catalogs = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from catalogs where categoryId = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Catalogs catalog = new Catalogs();
				catalog.setId(resultSet.getInt("id"));
				catalog.setName(resultSet.getString("name"));
				catalog.setCategoryId(resultSet.getInt("categoryId"));
				catalogs.add(catalog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			catalogs = null;
		} finally {
			ConnectDB.disconnect();
		}
		return catalogs;
	}
	public boolean create(Catalogs catalog) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(
					"insert into catalogs(name, categoryId) values (?,?)");
			preparedStatement.setString(1, catalog.getName());
			preparedStatement.setInt(2, catalog.getCategoryId());
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

	public boolean update(Catalogs catalogs) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update catalogs set name = ?, categoryId = ? where id = ? ");
			preparedStatement.setString(1, catalogs.getName());
			preparedStatement.setInt(2, catalogs.getCategoryId());
			preparedStatement.setInt(3, catalogs.getId());
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
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("delete from catalogs where id = ? ");
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

	public Catalogs findCatalogById(int id) {
		Catalogs catalog = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from catalogs where id = ? ");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				catalog = new Catalogs();
				catalog.setId(resultSet.getInt("id"));
				catalog.setName(resultSet.getString("name"));
				catalog.setCategoryId(resultSet.getInt("categoryId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			catalog = null;
		} finally {
			ConnectDB.disconnect();
		}
		return catalog;
	}

	

	public boolean checkExist(int catalogId, List<Catalogs> catalogs) {
		for (Catalogs catalog : catalogs) {
			if (catalog.getId() == catalogId)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		CatalogModel catalogModel = new CatalogModel();
		System.out.println(catalogModel.findAllByCategory(2));
	}
}
