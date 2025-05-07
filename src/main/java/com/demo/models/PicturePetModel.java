package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.demo.entities.Catalogs;
import com.demo.entities.Categorys;
import com.demo.entities.Pets;
import com.demo.entities.PicturePets;
import com.demo.entities.Users;

import DB.ConnectDB;

public class PicturePetModel {
	public List<PicturePets> findPostImageByPetID(int petID){
		List<PicturePets> images = new ArrayList<PicturePets>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from picturepets where petId = ?");
			preparedStatement.setInt(1, petID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PicturePets image = new PicturePets();
				image.setId(resultSet.getInt("id"));
				image.setImage(resultSet.getString("image"));
				image.setPetId(resultSet.getInt("petId"));
				image.setCreated(resultSet.getDate("created"));
				images.add(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
			images = null;
			// TODO: handle exception
		} finally {
			ConnectDB.disconnect();
		}
		
		return images;
	}
	
	public boolean create(PicturePets picturePets) {
		boolean status = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
			.prepareStatement("insert into picturepets(image,petId,created) values(?, ?, ?)");
			preparedStatement.setString(1, picturePets.getImage());
			preparedStatement.setInt(2, picturePets.getPetId());
			preparedStatement.setDate(3, new Date(picturePets.getCreated().getTime()));	
			status = preparedStatement.executeUpdate() > 0;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
			// TODO: handle exception
		} finally {
			ConnectDB.disconnect();
		}
		return status;
	}
	public boolean delete(int id) {
		boolean status = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
			.prepareStatement("DELETE FROM picturepets where petId = ?");

			preparedStatement.setInt(1, id);
			
			status = preparedStatement.executeUpdate() > 0;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
			// TODO: handle exception
		} finally {
			ConnectDB.disconnect();
		}
		return status;
	}
	public static void main(String[] args) {
		PicturePetModel picturePetModel = new PicturePetModel();
		System.out.println(picturePetModel.findPostImageByPetID(1));
	}
}
