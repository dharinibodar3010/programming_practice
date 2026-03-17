package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Model;
import com.usermodel.SignupModel;

public class Dao 
{
	// CONNECTION
	public static Connection getconnect()
	{
		Connection con = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","");
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	// INSERT (CREATE)
	public static int savedata(Model m)
	{
		Connection con = Dao.getconnect();
		int status = 0;
		try 
		{
			String sql = "insert into users(name,email,address,phone)values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,m.getName());
			ps.setString(2,m.getEmail());
			ps.setString(3,m.getAddress());
			ps.setString(4,m.getPhone());
			
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
	public static List<Model> viewdata()
    {
        List<Model> list = new ArrayList<Model>();

        try
        {
            Connection con = Dao.getconnect();

            String sql = "select * from users";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Model m = new Model();

                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setAddress(rs.getString("address"));
                m.setPhone(rs.getString("phone"));

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
            Connection con = Dao.getconnect();

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

    // GET USER BY ID (EDIT)
    public static Model getrecord(int id)
    {
        Model m = new Model();

        try
        {
            Connection con = Dao.getconnect();

            String sql = "select * from users where id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setAddress(rs.getString("address"));
                m.setPhone(rs.getString("phone"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return m;
    }

    // UPDATE
    public static int updatedata(Model m)
    {
        int status = 0;

        try
        {
            Connection con = Dao.getconnect();

            String sql = "update users set name=?,email=?,address=?,phone=? where id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,m.getName());
            ps.setString(2,m.getEmail());
            ps.setString(3,m.getAddress());
            ps.setString(4,m.getPhone());
            ps.setInt(5,m.getId());

            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }
    
    public static int signupdata(SignupModel m)
	{
		Connection con = Dao.getconnect();
		int status = 0;
		try 
		{
			String sql = "insert into userlogin(username,email,password)values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,m.getUsername());
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
	
	public static SignupModel checksignin(SignupModel m)
	{
		SignupModel m2 = null;
		Connection con = Dao.getconnect();
		String sql = "select * from userlogin where email=? and password=?";
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getPassword());
			ResultSet set = ps.executeQuery();
			
			if(set.next())
			{
				
				int id = set.getInt(1);
				String uname = set.getString(2);
				String email = set.getString(3);
				String pass = set.getString(4);
				
				
				m2 = new SignupModel();
				m2.setId(id);
				m2.setUsername(uname);
				m2.setEmail(email);
				m2.setPassword(pass);				
				
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


