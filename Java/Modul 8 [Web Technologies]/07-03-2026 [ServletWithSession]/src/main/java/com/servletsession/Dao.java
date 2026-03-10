package com.servletsession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
    
    public static Connection getConnection() 
    {
        Connection con = null;
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static int savedata(Model m) 
    {
        int status = 0;
        
        try (Connection con = Dao.getConnection()) 
        {
            String sql = "insert into info(username, email, password) values (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getUsername());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getPassword());
            
            status = ps.executeUpdate();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return status;
    }

    public static Model logindata(Model m) 
    {
        Model m2 = null;
        String sql = "select * from info where email = ? and password = ?";
        
        try (Connection con = Dao.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) 
        {   
            ps.setString(1, m.getEmail());
            ps.setString(2, m.getPassword());
            
            ResultSet set = ps.executeQuery();
            
            if (set.next()) 
            {
                m2 = new Model();
                m2.setId(set.getInt("id")); 
                m2.setUsername(set.getString("username"));
                m2.setEmail(set.getString("email"));
                m2.setPassword(set.getString("password"));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return m2;
    }
}