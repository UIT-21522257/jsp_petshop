package com.demo.models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.entities.CommentCustom;
import com.demo.entities.Comments;

import DB.ConnectDB;

public class CommentModel {
	// danh sach comment cua pet
	public List<Comments> findCommentByPet(int petId) {
		List<Comments> comments = new ArrayList<Comments>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from comments where petId=? order by id desc");
			preparedStatement.setInt(1, petId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Comments comment = new Comments();
				comment.setId(resultSet.getInt("id"));
				comment.setUserId(resultSet.getInt("userId"));
				comment.setPetId(resultSet.getInt("petId"));
				comment.setNote(resultSet.getString("note"));
				comment.setCreateDate(resultSet.getTimestamp("createDate"));
				comments.add(comment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			comments = null;
		}finally {
			ConnectDB.disconnect();
		}
		
		return comments	;
	}
	// danh sach comment cua tat ca
	public List<Comments> findAll() {
	    List<Comments> comments = new ArrayList<>();
	    try {
	        PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from comments order by id desc");
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while(resultSet.next()){
	            Comments comment = new Comments();
	            comment.setId(resultSet.getInt("id"));
	            comment.setUserId(resultSet.getInt("userId"));
	            comment.setPetId(resultSet.getInt("petId"));
	            comment.setNote(resultSet.getString("note"));
	            comment.setCreateDate(resultSet.getTimestamp("createDate"));
	            comments.add(comment);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        comments = null;
	    } finally {
	        ConnectDB.disconnect();
	    }

	    // Now, comments list will have comments in reverse order of their IDs.
	    return comments;
	}
	// danh sach comment new cua pet
	public List<CommentCustom> newComment(int petId) {
		List<CommentCustom> comments = new ArrayList<CommentCustom>();
		
			try {
				PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select c.id, c.petId, c.note, c.createDate, a.username from comments c, users a where petId=? and c.userId = a.id");
				preparedStatement.setInt(1, petId);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					CommentCustom comment = new CommentCustom();
					comment.setId(resultSet.getInt("c.id"));
					comment.setUserId(resultSet.getString("a.username"));
					comment.setPetId(resultSet.getInt("c.petId"));
					comment.setNote(resultSet.getString("c.note"));
					comment.setCreateDate(resultSet.getTimestamp("c.createDate"));
					comments.add(comment);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			comments = null;
		}finally {
			ConnectDB.disconnect();
		}
		
		return comments	;
	}
	// tra ve ten cua user comment
	public String NameUserById(int userId) {
		String result = "";
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select users.fullname from comments join users on users.id = comments.userId where userId=?");
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("fullName");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	
	}
	public boolean create(Comments comment) {
		boolean result = true;
		
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into comments (userId, petId, note, createDate) "
							+ "values(?, ?, ?, ?)");
			preparedStatement.setInt(1, comment.getUserId());
			preparedStatement.setInt(2, comment.getPetId());
			preparedStatement.setString(3, comment.getNote());
			preparedStatement.setTimestamp(4,  new Timestamp(comment.getCreateDate().getTime()));
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
	// tra ve danh sach comment moi nhat(4)
	public List<Comments> viewNewComment() {
		List<Comments> comments = new ArrayList<Comments>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM comments ORDER BY createDate DESC LIMIT 4");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Comments comment = new Comments();
				comment.setId(resultSet.getInt("id"));
				comment.setUserId(resultSet.getInt("userId"));
				comment.setPetId(resultSet.getInt("petId"));
				comment.setNote(resultSet.getString("note"));
				comment.setCreateDate(resultSet.getTimestamp("createDate"));
				comments.add(comment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			comments = null;
		}finally {
			ConnectDB.disconnect();
		}
		return comments	;
	}
	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("delete from comments where id = ?");
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
		CommentModel commentModel = new CommentModel();
//		System.out.println(commentModel.create(new Comments(5, 1, "hello pet", new Date())));
				
	}
	
}

