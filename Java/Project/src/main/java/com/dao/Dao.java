package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.model.CartModel;
import com.model.ContactModel;
import com.model.ProductModel;
import com.model.AdminModel;
import com.model.NewsletterModel;
import com.model.PaymentModel;
import com.model.SignupModel;
import com.model.WishlistModel;
import java.io.InputStream;

public class Dao {
	public static Connection getconnect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	// sigup
	public static int signup(SignupModel m) {
		Connection con = Dao.getconnect();
		int status = 0;

		try {
			PreparedStatement ps = con
					.prepareStatement("insert into signup(fullname,email,phone,password) values (?,?,?,?)");

			ps.setString(1, m.getFullname());
			ps.setString(2, m.getEmail());
			ps.setString(3, m.getPhone());
			ps.setString(4, m.getPassword());

			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			 
		}

		return status;
	}

	// login
	public static SignupModel login(SignupModel m) {
		Connection con = Dao.getconnect();
		SignupModel m2 = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from signup where email=? and password =?");

			ps.setString(1, m.getEmail());
			ps.setString(2, m.getPassword());

			ResultSet set = ps.executeQuery();

			if (set.next()) {
				int id = set.getInt(1);
				String fullname = set.getString(2);
				String email = set.getString(3);
				String phone = set.getString(4);
				String password = set.getString(5);

				m2 = new SignupModel();
				m2.setId(id);
				m2.setFullname(fullname);
				m2.setEmail(email);
				m2.setPassword(password);
				m2.setPhone(phone);
			} else {
				System.out.println("Invalid Credentials");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return m2;
	}

	// update profile
	public static int updateProfile(SignupModel m) {
		Connection con = Dao.getconnect();
		int status = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update signup set fullname=?, phone=? where email=?");
			ps.setString(1, m.getFullname());
			ps.setString(2, m.getPhone());
			ps.setString(3, m.getEmail());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static List<ProductModel> viewproducts() {

		List<ProductModel> plist = new ArrayList<>();

		Connection con = Dao.getconnect();

		try {
			PreparedStatement ps = con.prepareStatement("select * from products");
			ResultSet set = ps.executeQuery();

			while (set.next()) {

				int id = set.getInt("id");
				String pname = set.getString("p_name");
				String pprice = set.getString("p_price");
				String pdes = set.getString("p_des");
				byte[] imgData = set.getBytes("p_image");
				String encode = Base64.getEncoder().encodeToString(imgData);

				ProductModel pm = new ProductModel();
				pm.setId(id);
				pm.setP_name(pname);
				pm.setP_price(pprice);
				pm.setP_des(pdes);
				pm.setP_image(encode);

				plist.add(pm);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return plist;

	}

	public static List<ProductModel> viewProductsByCategory(String category) {
		List<ProductModel> plist = new ArrayList<>();
		Connection con = Dao.getconnect();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE category = ?");
			ps.setString(1, category); //

			ResultSet set = ps.executeQuery();
			while (set.next()) {
				ProductModel pm = new ProductModel();
				pm.setId(set.getInt("id"));
				pm.setP_name(set.getString("p_name"));
				pm.setP_price(set.getString("p_price"));
				pm.setP_des(set.getString("p_des"));

				byte[] imgData = set.getBytes("p_image");
				pm.setP_image(Base64.getEncoder().encodeToString(imgData));

				pm.setCategory(set.getString("category"));
				plist.add(pm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	}

	public static int contactinsert(ContactModel m) {
		Connection con = Dao.getconnect();
		int status = 0;

		try {
			PreparedStatement ps = con
					.prepareStatement("insert into contact(fullname,email,phone,feedback) values (?,?,?,?)");

			ps.setString(1, m.getFullname());
			ps.setString(2, m.getEmail());
			ps.setString(3, m.getPhone());
			ps.setString(4, m.getFeedback());

			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return status;
	}

	public static ProductModel getwishlistindexwise(int id) {

		ProductModel m = null;

		Connection con = Dao.getconnect();
		try {
			PreparedStatement ps = con.prepareStatement("select * from products where id=?");
			ps.setInt(1, id);

			ResultSet set = ps.executeQuery();

			if (set.next()) {
				m = new ProductModel();
				m.setId(set.getInt(1));
				m.setP_name(set.getString(2));
				m.setP_price(set.getString(3));
				m.setP_des(set.getString(4));
				byte[] imgData = set.getBytes("p_image"); // blob field
				String encode = Base64.getEncoder().encodeToString(imgData);
				m.setP_image(encode);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public static List<WishlistModel> getwishlistbyemail(String email) {
		List<WishlistModel> list = new ArrayList<>();

		Connection con = Dao.getconnect();

		try {
			PreparedStatement ps = con.prepareStatement("select * from wishlist where email=?");
			ps.setString(1, email);

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				int id = set.getInt(1);
				String pname = set.getString(2);
				String pprice = set.getString(3);
				String pdes = set.getString(4);

				String email1 = set.getString(6);
				byte[] imgData = set.getBytes("p_image"); // blob field
				String encode = Base64.getEncoder().encodeToString(imgData);

				WishlistModel m = new WishlistModel();
				m.setId(id);
				m.setP_name(pname);
				m.setP_price(pprice);
				m.setP_des(pdes);
				m.setP_image(encode);
				m.setEmail(email1);

				list.add(m);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static List<CartModel> getcartbyemail(String email) {
		List<CartModel> list = new ArrayList<>();

		Connection con = Dao.getconnect();

		try {
			PreparedStatement ps = con.prepareStatement("select * from cart where email=?");
			ps.setString(1, email);

			ResultSet set = ps.executeQuery();

			while (set.next()) {
				int id = set.getInt(1);
				String pname = set.getString(2);
				String pprice = set.getString(3);
				String pdes = set.getString(4);

				String email1 = set.getString(6);
				byte[] imgData = set.getBytes("p_image"); // blob field
				String encode = Base64.getEncoder().encodeToString(imgData);

				CartModel m = new CartModel();
				m.setId(id);
				m.setP_name(pname);
				m.setP_price(pprice);
				m.setP_des(pdes);
				m.setP_image(encode);
				m.setEmail(email1);

				list.add(m);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static int deletefromwishlist(int id) {
		// TODO Auto-generated method stub
		int status = 0;

		Connection con = Dao.getconnect();

		try {
			PreparedStatement ps = con.prepareStatement("delete from wishlist where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}
	
	public static int deletefromcart(int id) {
	    int status = 0;

	    Connection con = Dao.getconnect();

	    try {
	        PreparedStatement ps = con.prepareStatement("delete from cart where id=?");
	        ps.setInt(1, id);
	        status = ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return status;
	}

	public static List<ContactModel> viewcontact() {
		List<ContactModel> list = new ArrayList<>();
		Connection con = Dao.getconnect();
		try {
			PreparedStatement ps = con.prepareStatement("select * from contact");
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				ContactModel m = new ContactModel();
				m.setId(set.getInt(1));
				m.setFullname(set.getString(2));
				m.setEmail(set.getString(3));
				m.setPhone(set.getString(4));
				m.setFeedback(set.getString(5));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int newsletter(NewsletterModel m) {

		Connection con = Dao.getconnect();
		int status = 0;
		try {
			PreparedStatement ps = con.prepareStatement("insert into newsletter (email) values (?)");
			ps.setString(1, m.getEmail());
			status = ps.executeUpdate();
			//System.out.println("Status of Query: " + status);
		} catch (Exception e) {
			//System.out.println("Database Error: " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	// Payment 

	    public static int savePayment(PaymentModel m) {
	    	Connection con = Dao.getconnect();
			int status = 0;
	        try {
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO razorpay_payments(payment_id, order_id, signature) VALUES (?, ?, ?)"
	            );

	            ps.setString(1, m.getPaymentId());
	            ps.setString(2, m.getOrderId());
	            ps.setString(3, m.getSignature());

	            status = ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	
	
    public static int deleteFromCartById(int cartItemId) {
		Connection con = Dao.getconnect();
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM cart WHERE id = ?"
            );

            ps.setInt(1, cartItemId);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Admin Login
    public static AdminModel adminLogin(AdminModel m) {
        Connection con = Dao.getconnect();
        AdminModel m2 = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from admin where username=? and password =?");
            ps.setString(1, m.getUsername());
            ps.setString(2, m.getPassword());

            ResultSet set = ps.executeQuery();

            if (set.next()) {
                m2 = new AdminModel();
                m2.setId(set.getInt(1));
                m2.setUsername(set.getString(2));
                m2.setPassword(set.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m2;
    }

    // Add Product
    public static int addProduct(ProductModel m, InputStream imageStream) {
        Connection con = Dao.getconnect();
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into products(p_name, p_price, p_des, p_image, category) values (?,?,?,?,?)");
            ps.setString(1, m.getP_name());
            ps.setString(2, m.getP_price());
            ps.setString(3, m.getP_des());
            ps.setBlob(4, imageStream);
            ps.setString(5, m.getCategory());

            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Delete Product
    public static int deleteProduct(int id) {
        Connection con = Dao.getconnect();
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement("delete from products where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Reply to Contact Query
    public static int updateContactReply(int id, String reply) {
        Connection con = Dao.getconnect();
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement("update contact set reply=? where id=?");
            ps.setString(1, reply);
            ps.setInt(2, id);
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Get Product by ID
    public static ProductModel getProductById(int id) {
        ProductModel m = null;
        Connection con = Dao.getconnect();
        try {
            PreparedStatement ps = con.prepareStatement("select * from products where id=?");
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                m = new ProductModel();
                m.setId(set.getInt("id"));
                m.setP_name(set.getString("p_name"));
                m.setP_price(set.getString("p_price"));
                m.setP_des(set.getString("p_des"));
                m.setCategory(set.getString("category"));
                byte[] imgData = set.getBytes("p_image");
                m.setP_image(Base64.getEncoder().encodeToString(imgData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    // Update Product
    public static int updateProduct(ProductModel m, InputStream imageStream) {
        Connection con = Dao.getconnect();
        int status = 0;
        try {
            String sql;
            PreparedStatement ps;
            if (imageStream != null && imageStream.available() > 0) {
                sql = "update products set p_name=?, p_price=?, p_des=?, p_image=?, category=? where id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, m.getP_name());
                ps.setString(2, m.getP_price());
                ps.setString(3, m.getP_des());
                ps.setBlob(4, imageStream);
                ps.setString(5, m.getCategory());
                ps.setInt(6, m.getId());
            } else {
                sql = "update products set p_name=?, p_price=?, p_des=?, category=? where id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, m.getP_name());
                ps.setString(2, m.getP_price());
                ps.setString(3, m.getP_des());
                ps.setString(4, m.getCategory());
                ps.setInt(5, m.getId());
            }
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}
