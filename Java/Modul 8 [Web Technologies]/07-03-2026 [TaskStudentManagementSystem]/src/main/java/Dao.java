
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class Dao 
{
	public static Connection getconnect()
	{
		Connection con = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
	
	// --- Insert Student ---
	public static int savedata(StudentModel m) 
	{
		Connection con = Dao.getconnect();
		int status = 0;
		
		try 
		{
			String sql = "INSERT INTO details(sname, ssurname, stream, city, edu, percentage, ptech, gender, fees) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getSname());
	        ps.setString(2, m.getSsurname());
	        ps.setString(3, m.getStream());
	        ps.setString(4, m.getCity());
	        ps.setString(5, m.getEdu());
	        ps.setString(6, m.getPercentage());
	        ps.setString(7, m.getPtech());
	        ps.setString(8, m.getGender());
	        ps.setInt(9, m.getFees());
			
			
			status = ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return status;
	}
	
	// --- VIEW ---
	public static List<StudentModel> viewdata() 
	{
	    List<StudentModel> list = new ArrayList<>();
	    
	    Connection con = Dao.getconnect();
	   
	    try  
	    {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM details");
	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) 
	        {
	            StudentModel m = new StudentModel();
	            m.setId(rs.getInt("id"));
	            m.setSname(rs.getString("sname"));
	            m.setSsurname(rs.getString("ssurname"));
	            m.setStream(rs.getString("stream"));
	            m.setCity(rs.getString("city"));
	            m.setEdu(rs.getString("edu"));
	            m.setPercentage(rs.getString("percentage"));
	            m.setPtech(rs.getString("ptech"));
	            m.setGender(rs.getString("gender"));
	            m.setFees(rs.getInt("fees"));
	            list.add(m);
	        }
	    } 
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	// --- DELETE ---
	public static int deletedata(int id)
	{
		Connection con = Dao.getconnect();
		int status = 0;
		try 
		{
			String sql = "delete from details where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			
			status = ps.executeUpdate();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	// --- GET BY ID ---
	public static StudentModel getrecord(int id)
	{
	    StudentModel m = null;
	    Connection con = Dao.getconnect();
	    String sql = "select * from details where id=?";
	    
	    try 
	    {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet set = ps.executeQuery();
	        
	        if(set.next())
	        {
	            m = new StudentModel();
	            m.setId(set.getInt(1));
	            m.setSname(set.getString(2));
	            m.setSsurname(set.getString(3));
	            m.setStream(set.getString(4));    
	            m.setCity(set.getString(5));
	            m.setEdu(set.getString(6));       
	            m.setPercentage(set.getString(7));
	            m.setPtech(set.getString(8));     
	            m.setGender(set.getString(9));   
	            m.setFees(set.getInt(10));      
	        }
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return m;
	}
	
	// --- UPDATE ---
	public static int updatedata(StudentModel m) 
	{
	    Connection con = Dao.getconnect();
	    int status = 0;
	    
	    try 
	    {
	        String sql = "update details set sname=?, ssurname=?, stream=?, city=?, edu=?, percentage=?, ptech=?, gender=?, fees=? where id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, m.getSname());
	        ps.setString(2, m.getSsurname());
	        ps.setString(3, m.getStream());
	        ps.setString(4, m.getCity());
	        ps.setString(5, m.getEdu());
	        ps.setString(6, m.getPercentage());
	        ps.setString(7, m.getPtech());
	        ps.setString(8, m.getGender());
	        ps.setInt(9, m.getFees());
	        ps.setInt(10, m.getId());

	        status = ps.executeUpdate();
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return status;
	}

	public static AdminModel checksignin(AdminModel m)
	{
		AdminModel m2 = null;
		Connection con = Dao.getconnect();
		String sql = "select * from admin where email=? and password=?";
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getPassword());
			ResultSet set = ps.executeQuery();
			
			if(set.next())
			{
				int id = set.getInt(1);
				String email = set.getString(2);
				String password = set.getString(3);
				
				m2 = new AdminModel();
				m2.setId(id);
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
