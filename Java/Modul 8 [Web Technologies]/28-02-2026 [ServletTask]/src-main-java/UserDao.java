package com.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao 
{
    // ===== SIGNUP =====
    public static int signup(UserModel user) 
    {
        int status = 0;
        try
        {
            Connection con = DBConnection.con;
            String sql = "insert into users (username, email, password) values (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            status = ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    // ===== SIGNIN =====
    public static UserModel signin(String email, String password) 
    {
        UserModel user = null;
        try 
        {
            Connection con = DBConnection.con;
            String sql = "select * from users where email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // optional, needed for session maybe
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return user;
    }
}