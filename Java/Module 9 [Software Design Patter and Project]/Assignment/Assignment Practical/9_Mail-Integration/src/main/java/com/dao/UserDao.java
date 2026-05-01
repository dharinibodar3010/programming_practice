package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.UserModel;

public class UserDao {
	public static Connection getconnect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;
	}

	public static int registerUser(UserModel um) {
		Connection con = UserDao.getconnect();
		int status = 0;

		try {
			String sql = "insert into user1(name, email, password) values (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, um.getName());
			ps.setString(2, um.getEmail());
			ps.setString(3, um.getPassword());

			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return status;
	}

	public static boolean checkEmail(String email) {

		Connection con = UserDao.getconnect();
		boolean exists = false;

		try {

			String sql = "select * from user1 where email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet set = ps.executeQuery();

			if (set.next()) {
				exists = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exists;
	}

}
