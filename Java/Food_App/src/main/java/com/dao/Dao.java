package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.*;

public class Dao {

	// 1. DATABASE CONNECTION
	public static Connection getconnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodie_app", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// 2. USER: SIGNUP
	public static int signup(UserModel um) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("insert into users(fullname, email, phone, password) values (?,?,?,?)");
			ps.setString(1, um.getFullname());
			ps.setString(2, um.getEmail());
			ps.setString(3, um.getPhone());
			ps.setString(4, um.getPassword());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 3. USER: GET BY ID
	public static UserModel getUserById(int id) {
		UserModel user = null;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// 4. USER: LOGIN
	public static UserModel login(UserModel um) {
		UserModel user = null;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, um.getEmail());
			ps.setString(2, um.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// 4. PRODUCT: GET ALL
	public static List<ProductModel> getAllProducts() {
		List<ProductModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from products");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel p = new ProductModel();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImageUrl(rs.getString("image_url"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getString("category"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ProductModel> getProductsByCategory(String category) {
		List<ProductModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from products where category LIKE ?");
			ps.setString(1, category + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel p = new ProductModel();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImageUrl(rs.getString("image_url"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getString("category"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ProductModel> searchProducts(String query) {
		List<ProductModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from products where name LIKE ? or category LIKE ?");
			ps.setString(1, "%" + query + "%");
			ps.setString(2, "%" + query + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel p = new ProductModel();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImageUrl(rs.getString("image_url"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getString("category"));
				list.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 5. PRODUCT: GET BY ID
	public static ProductModel getProductById(int id) {
		ProductModel p = null;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from products where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p = new ProductModel();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImageUrl(rs.getString("image_url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	// 6. CART: ADD TO CART (EASY VERSION)
	public static int addToCart(int userId, int productId, int qty) {
		int status = 0;
		try {
			Connection con = getconnect();
			// Step A: Check if item is already in cart
			PreparedStatement ps1 = con.prepareStatement("select * from cart where user_id=? and product_id=?");
			ps1.setInt(1, userId);
			ps1.setInt(2, productId);
			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {
				// Step B: If exists, Increase Quantity
				int oldQty = rs.getInt("quantity");
				int newQty = oldQty + qty;
				
				PreparedStatement ps2 = con.prepareStatement("update cart set quantity=? where id=?");
				ps2.setInt(1, newQty);
				ps2.setInt(2, rs.getInt("id"));
				status = ps2.executeUpdate();
			} else {
				// Step C: If not exists, Insert New Order
				PreparedStatement ps3 = con.prepareStatement("insert into cart(user_id, product_id, quantity) values (?,?,?)");
				ps3.setInt(1, userId);
				ps3.setInt(2, productId);
				ps3.setInt(3, qty);
				status = ps3.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 7. CART: REMOVE
	public static int removeFromCart(int cartId) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("delete from cart where id=?");
			ps.setInt(1, cartId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 8. CART: UPDATE (FOR QUANTITY BUTTONS)
	public static int updateCartQuantity(int cartId, int qty) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("update cart set quantity=? where id=?");
			ps.setInt(1, qty);
			ps.setInt(2, cartId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 9. CART: FETCH ALL ITEMS
	public static List<CartModel> getCartItems(int userId) {
		List<CartModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select * from cart where user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartModel c = new CartModel();
				c.setId(rs.getInt("id"));
				c.setUserId(rs.getInt("user_id"));
				c.setProductId(rs.getInt("product_id"));
				c.setQuantity(rs.getInt("quantity"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 10. CART: COUNT FOR BADGE
	public static int getCartCount(int userId) {
		int count = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("select sum(quantity) from cart where user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 11. ORDERS: PLACE ORDER
	public static int placeOrder(OrderModel om) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("insert into orders(user_id, total_amount, payment_method, status) values (?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, om.getUserId());
			ps.setDouble(2, om.getTotalAmount());
			ps.setString(3, om.getPaymentMethod());
			ps.setString(4, om.getStatus());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) status = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 12. ORDERS: ADD ITEM
	public static int addOrderItem(OrderItemModel oim) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("insert into order_items(order_id, product_id, quantity, price) values (?,?,?,?)");
			ps.setInt(1, oim.getOrderId());
			ps.setInt(2, oim.getProductId());
			ps.setInt(3, oim.getQuantity());
			ps.setDouble(4, oim.getPrice());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 12b. ORDERS: GET ITEMS BY ORDER ID
	public static List<OrderItemModel> getItemsByOrderId(int orderId) {
		List<OrderItemModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM order_items WHERE order_id=?");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderItemModel item = new OrderItemModel();
				item.setId(rs.getInt("id"));
				item.setOrderId(rs.getInt("order_id"));
				item.setProductId(rs.getInt("product_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 13. PAYMENT: RECORD
	public static int addPayment(int orderId, String transId, String mode, double amount, String payStatus) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("insert into payments(order_id, transaction_id, payment_mode, amount, status) values (?,?,?,?,?)");
			ps.setInt(1, orderId);
			ps.setString(2, transId);
			ps.setString(3, mode);
			ps.setDouble(4, amount);
			ps.setString(5, payStatus);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 14. CART: CLEAR ALL
	public static int clearCart(int userId) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("delete from cart where user_id=?");
			ps.setInt(1, userId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// 15. HISTORY: USER ORDERS (ALL)
	public static List<OrderModel> getUserOrders(int userId) {
		List<OrderModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderModel o = new OrderModel();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setTotalAmount(rs.getDouble("total_amount"));
				o.setPaymentMethod(rs.getString("payment_method"));
				o.setStatus(rs.getString("status"));
				o.setOrderDate(rs.getTimestamp("order_date"));
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 15b. HISTORY: USER ORDERS FILTERED BY DATE/MONTH/YEAR
	public static List<OrderModel> getOrdersByFilter(int userId, String dateStr, String month, String year) {
		List<OrderModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			StringBuilder query = new StringBuilder("SELECT * FROM orders WHERE user_id=?");
			if (dateStr != null && !dateStr.isEmpty()) {
				// Exact date filter: dateStr is "YYYY-MM-DD"
				query.append(" AND DATE(order_date) = ?");
			} else {
				if (year != null && !year.isEmpty()) query.append(" AND YEAR(order_date) = ?");
				if (month != null && !month.isEmpty()) query.append(" AND MONTH(order_date) = ?");
			}
			query.append(" ORDER BY order_date DESC");
			PreparedStatement ps = con.prepareStatement(query.toString());
			int idx = 1;
			ps.setInt(idx++, userId);
			if (dateStr != null && !dateStr.isEmpty()) {
				ps.setString(idx++, dateStr);
			} else {
				if (year != null && !year.isEmpty()) ps.setString(idx++, year);
				if (month != null && !month.isEmpty()) ps.setString(idx++, month);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderModel o = new OrderModel();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setTotalAmount(rs.getDouble("total_amount"));
				o.setPaymentMethod(rs.getString("payment_method"));
				o.setStatus(rs.getString("status"));
				o.setOrderDate(rs.getTimestamp("order_date"));
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 16. ADMIN: GET ALL ORDERS
	public static List<OrderModel> getAllOrders() {
		List<OrderModel> list = new ArrayList<>();
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM orders ORDER BY order_date DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderModel o = new OrderModel();
				o.setId(rs.getInt("id"));
				o.setUserId(rs.getInt("user_id"));
				o.setTotalAmount(rs.getDouble("total_amount"));
				o.setPaymentMethod(rs.getString("payment_method"));
				o.setStatus(rs.getString("status"));
				o.setOrderDate(rs.getTimestamp("order_date"));
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 17. ADMIN: TOTAL REVENUE
	public static double getTotalRevenue() {
		double total = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT SUM(total_amount) FROM orders WHERE status != 'Cancelled'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) total = rs.getDouble(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	// 18. ADMIN: TOTAL ORDERS COUNT
	public static int getTotalOrdersCount() {
		int count = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM orders");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 19. ADMIN: TOTAL USERS COUNT
	public static int getTotalUsersCount() {
		int count = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM users");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 20. CONTACT MESSAGE
	public static int addContactMessage(String name, String email, String subject, String msg) {
		int status = 0;
		try {
			Connection con = getconnect();
			PreparedStatement ps = con.prepareStatement("insert into contact_messages(name, email, subject, message) values (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, subject);
			ps.setString(4, msg);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
