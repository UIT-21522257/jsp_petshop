package com.demo.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.entities.Log;

import DB.ConnectDB;

public class LogModel {
	public List<Log> findAll() {
	    List<Log> logs = new ArrayList<Log>();
	    try {
	        String query = "SELECT l.* FROM logs l " +
	                       "INNER JOIN users u ON l.userId = u.id " +
	                       "WHERE u.roleId = 2";
	        PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            Log log = new Log();
	            log.setId(resultSet.getInt("id"));
	            log.setIp(resultSet.getString("ip"));
	            log.setLevel(resultSet.getString("level"));
	            log.setNational(resultSet.getString("national"));
	            log.setTime(resultSet.getTimestamp("time"));
	            log.setBeforeValue(resultSet.getString("beforeValue"));
	            log.setAfterValue(resultSet.getString("afterValue"));
	            log.setUserId(resultSet.getInt("userId"));
	            logs.add(log);
	        }
	    } catch (SQLException e) {
	        logs = null;
	        e.printStackTrace();
	    }
	    return logs;
	}

	
	public boolean create(Log log) {
		boolean status = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("INSERT INTO logs(ip, level, national, time, beforeValue, afterValue, userId) VALUES (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, log.getIp());
			preparedStatement.setString(2, log.getLevel());
			preparedStatement.setString(3, log.getNational());
			preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
			preparedStatement.setString(5, log.getBeforeValue());
			preparedStatement.setString(6, log.getAfterValue());
			preparedStatement.setInt(7, log.getUserId());	
			status = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = false;
			e.printStackTrace();
		}
		return status;
	}
}
