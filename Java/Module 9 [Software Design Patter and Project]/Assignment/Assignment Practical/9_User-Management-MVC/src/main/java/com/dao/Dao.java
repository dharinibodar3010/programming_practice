package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.UserModel;

public class Dao 
{
	public static Connection getConnection() 
	{
        Connection con = null;
       
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb", "root", "" ); 
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return con;
    }
	
	// INSERT (CREATE)
		public static int savedata(UserModel m)
		{
			Connection con = Dao.getConnection();
			int status = 0;
			try 
			{
				String sql = "insert into users(name,email,password)values(?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,m.getName());
				ps.setString(2,m.getEmail());
				ps.setString(3,m.getPassword());
				
				status = ps.executeUpdate();
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return status;
		}
		
		// VIEW (READ)
		public static List<UserModel> viewdata()
	    {
	        List<UserModel> list = new ArrayList<UserModel>();

	        try
	        {
	            Connection con = Dao.getConnection();

	            String sql = "select * from users";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ResultSet rs = ps.executeQuery();

	            while(rs.next())
	            {
	            	UserModel m = new UserModel();

	                m.setId(rs.getInt("id"));
	                m.setName(rs.getString("name"));
	                m.setEmail(rs.getString("email"));
	                m.setPassword(rs.getString("password"));
	               
	                list.add(m);
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    // DELETE
	    public static int deletedata(int id)
	    {
	        int status = 0;

	        try
	        {
	            Connection con = Dao.getConnection();

	            String sql = "delete from users where id=?";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1,id);

	            status = ps.executeUpdate();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        return status;
	    }
	    
	 // UPDATE
	    public static int updatedata(UserModel m)
	    {
	        int status = 0;

	        try
	        {
	            Connection con = Dao.getConnection();

	            String sql = "update users set name=?,email=?,password=? where id=?";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1,m.getName());
	            ps.setString(2,m.getEmail());
	            ps.setString(3,m.getPassword());
	            ps.setInt(4,m.getId());

	            status = ps.executeUpdate();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        return status;
	    }
	 
	    public static UserModel login(UserModel m)
		{
			UserModel m2 = null;
			Connection con = Dao.getConnection();
			String sql = "select * from users where email=? and password=?";
			try 
			{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, m.getEmail());
				ps.setString(2, m.getPassword());
				ResultSet set = ps.executeQuery();
				
				if(set.next())
				{
					int id = set.getInt(1);
					String name = set.getString(2);
					String email = set.getString(3);
					String password = set.getString(4);
					
					m2 = new UserModel();
					m2.setId(id);
					m2.setName(name);		
					m2.setEmail(email);
					m2.setPassword(password);
				}
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return m2;
		}
}
