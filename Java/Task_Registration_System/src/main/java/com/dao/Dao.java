package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.CoursesModel;
import com.model.EnrollmentsModel;
import com.model.PaymentsModel;
import com.model.UsersModel;

public class Dao<PaymentModel> {
	
		public static Connection getconnect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_system", "root", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return con;
		}
		
		// sign up
		public int registerUser(UsersModel m) {
		    int status = 0;
		    try {
		        Connection con = Dao.getconnect(); // 
		        PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email, password) VALUES(?,?,?)");
		        
		        ps.setString(1, m.getName());
		        ps.setString(2, m.getEmail());
		        
		        ps.setString(3, m.getPassword());
		        
		        status = ps.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return status;
		}

		// login
		public static UsersModel login(UsersModel m) {
		    UsersModel user = null;
		   
		    try (Connection con = getconnect()) {
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
		        ps.setString(1, m.getEmail());
		        ps.setString(2, m.getPassword());

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            user = new UsersModel();
		            user.setId(rs.getInt("id"));
		            user.setName(rs.getString("name"));
		            user.setEmail(rs.getString("email"));
		            user.setPassword(rs.getString("password")); 
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return user;
		}
		public static List<CoursesModel> getAllCourses() {
	        List<CoursesModel> list = new ArrayList<>();
	        try (Connection con = Dao.getconnect()) {
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery("SELECT * FROM courses");
	            while (rs.next()) {
	                CoursesModel c = new CoursesModel();
	                c.setId(rs.getInt("id"));
	                c.setName(rs.getString("name"));
	                c.setDescription(rs.getString("description"));
	                c.setPrice(rs.getString("price"));
	                list.add(c);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    public static CoursesModel getCourseById(int id) {
	        CoursesModel c = null;
	        try (Connection con = Dao.getconnect()) {
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM courses WHERE id=?");
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                c = new CoursesModel();
	                c.setId(rs.getInt("id"));
	                c.setName(rs.getString("name"));
	                c.setDescription(rs.getString("description"));
	                c.setPrice(rs.getString("price"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return c;
	    }
	    
	    public static int enroll(EnrollmentsModel e) {
	        int status = 0; 
	        try (Connection con = Dao.getconnect()) {
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO enrollments(user_id, course_id, payment_status) VALUES(?,?,?)"
	            );
	        	ps.setString(1, e.getUser_id());
	            ps.setString(2, e.getCourse_id());
	            ps.setString(3, e.getPayment_status());
	            
	            status = ps.executeUpdate(); 
	        } catch (Exception ex) {
	            ex.printStackTrace(); 
	        }
	        return status;
	    }
	    
	    public static EnrollmentsModel getEnrollmentById(int id) {
	        EnrollmentsModel e = null;
	        try (Connection con = Dao.getconnect()) {
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM enrollments WHERE id=?");
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                e = new EnrollmentsModel();
	                e.setId(rs.getInt("id"));
	                e.setUser_id(rs.getString("user_id"));
	                e.setCourse_id(rs.getString("course_id"));
	                e.setPayment_status(rs.getString("payment_status"));
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return e;
	    }

	    public static void updateStatusByUserId(String userId, String courseId, String status) {
	        try (Connection con = Dao.getconnect()) {
	            
	            PreparedStatement ps = con.prepareStatement(
	                "UPDATE enrollments SET payment_status=? WHERE user_id=? AND course_id=?"
	            );
	            ps.setString(1, status);
	            ps.setString(2, userId);
	            ps.setString(3, courseId);
	            ps.executeUpdate();
	            System.out.println("Status updated to Paid for User: " + userId + " and Course: " + courseId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void makePayment(PaymentsModel p) {
	        try (Connection con = Dao.getconnect()) {
	            PreparedStatement ps = con.prepareStatement("INSERT INTO payments(enrollment_id, amount) VALUES(?,?)");
	            ps.setString(1, p.getEnrollment_id()); 
	            ps.setString(2, p.getAmount());        
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static int register(UsersModel u) {
	        int status = 0; 
	        try (Connection con = Dao.getconnect()) {
	            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email, password) VALUES(?,?,?)");
	            ps.setString(1, u.getName());
	            ps.setString(2, u.getEmail());
	            ps.setString(3, u.getPassword());
	            
	            status = ps.executeUpdate(); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;  
	    }

		
}
