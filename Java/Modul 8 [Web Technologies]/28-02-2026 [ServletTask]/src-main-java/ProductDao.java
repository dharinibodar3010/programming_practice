package com.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // ===== ADD PRODUCT =====
    public static int addProduct(ProductModel p) 
    {
        int status = 0;
       
        try 
        {
            Connection con = DBConnection.con;
            String sql = "INSERT INTO products(pname,pprice,pdes) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getPname());
            ps.setString(2, p.getPprice());
            ps.setString(3, p.getPdes());

            status = ps.executeUpdate(); // 1 = success, 0 = fail
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return status;
    }

    // ===== GET ALL PRODUCTS =====
    public static List<ProductModel> ViewProducts() 
    {
        List<ProductModel> list = new ArrayList<>();
       
        try 
        {
            Connection con = DBConnection.con;
            String sql = "SELECT * FROM products";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                ProductModel p = new ProductModel();
                p.setId(rs.getInt("id"));
                p.setPname(rs.getString("pname"));
                p.setPprice(rs.getString("pprice"));
                p.setPdes(rs.getString("pdes"));
                list.add(p);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return list;
    }

    // ===== UPDATE PRODUCT =====
    public static int updateProduct(ProductModel p) 
    {
        int status = 0;
        
        try 
        {
            Connection con = DBConnection.con;
            String sql = "UPDATE products SET pname=?, pprice=?, pdes=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getPname());
            ps.setString(2, p.getPprice());
            ps.setString(3, p.getPdes());
            ps.setInt(4, p.getId());

            status = ps.executeUpdate(); // 1 = success, 0 = fail
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return status;
    }

    // ===== DELETE PRODUCT =====
    public static int deleteProduct(int id) 
    {
        int status = 0;
        
        try 
        {
            Connection con = DBConnection.con;
            String sql = "DELETE FROM products WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            status = ps.executeUpdate(); // 1 = success, 0 = fail
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return status;
    }

    public static ProductModel getrecord(int id)
	{
		ProductModel m = null;
		
		try 
		{
			Connection con = DBConnection.con;
			String sql = "select * from products where id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			
			if(set.next())
			{
				m = new ProductModel();
				m.setId(set.getInt(1));
				m.setPname(set.getString(2));
				m.setPprice(set.getString(3));
				m.setPdes(set.getString(4));
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}
}
