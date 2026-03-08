package com.CafeMgtSys;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// ------------------------ MAIN FRAME ------------------------
class MainFrame {
    JFrame frame;
    JButton btnAdmin, btnUser;

    public MainFrame() {
        frame = new JFrame("Cafe Management System");
        
        btnAdmin = new JButton("Admin");
        btnAdmin.setBounds(50, 60, 120, 40);
        
        btnUser = new JButton("User");
        btnUser.setBounds(220, 60, 120, 40);
        
        // Button Actions
        btnAdmin.addActionListener(new ActionListener() 
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
	            new AdminLogin();
			}
		});

        btnUser.addActionListener(new ActionListener() 
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
	            new UserFrame();
			}
		});

        frame.add(btnAdmin);
        frame.add(btnUser);
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) 
    {
        new MainFrame();
    }
}

//------------------------ ADMIN Login------------------------

class AdminLogin 
{
    JFrame frame;
    JTextField usertf;
    JPasswordField passtf;
    JButton loginbtn;
    JLabel l1, l2;

    public AdminLogin() 
    {
        frame = new JFrame("Admin Login");
      
        usertf = new JTextField();
        usertf.setBounds(120, 30, 150, 25);
        
        passtf = new JPasswordField();
        passtf.setBounds(120, 70, 150, 25);
        
        l1 = new JLabel("Username");
        l1.setBounds(30, 30, 80, 25);
        
        l2 =new JLabel("Password");
        l2.setBounds(30, 70, 80, 25);
       
        loginbtn = new JButton("Login");
        loginbtn.setBounds(120, 110, 100, 30);
        loginbtn.addActionListener(new ActionListener() 
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				login();
			}
		});
        
        frame.add(l1);
        frame.add(usertf);
        frame.add(l2);
        frame.add(passtf);
        frame.add(loginbtn);
        frame.setSize(300,200);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    void login() {

        String user = usertf.getText();
        String pass = new String(passtf.getPassword());
        
        String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
                
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) 
            {
                frame.dispose();
                new AdminFrame(); // open admin panel
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Invalid Login");
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
}

// ------------------------ ADMIN FRAME ------------------------
class AdminFrame 
{
    JFrame frame;
    JTable itemTable;
    DefaultTableModel itemModel;
    JTextField tfName, tfPrice;
    JButton btnAddItem, btnRefresh, btnSalesReport, btnBack;
    JCheckBox availablecb;
    JLabel lblName, lblPrice;

    public AdminFrame()
    {
        frame = new JFrame("Admin Panel");
        
        // Labels & Fields
        lblName = new JLabel("Item Name:");
        lblName.setBounds(20, 20, 100, 25);
        frame.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(120, 20, 150, 25);
        frame.add(tfName);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 60, 100, 25);
        frame.add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(120, 60, 150, 25);
        frame.add(tfPrice);

        // Buttons
        btnAddItem = new JButton("Add Item");
        btnAddItem.setBounds(300, 20, 120, 30);
        frame.add(btnAddItem);

        availablecb = new JCheckBox("Available");
        availablecb.setBounds(300, 60, 150, 30);
        frame.add(availablecb);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(470, 20, 100, 30);
        frame.add(btnRefresh);

        btnSalesReport = new JButton("Sales Report");
        btnSalesReport.setBounds(470, 60, 150, 30);
        frame.add(btnSalesReport);

        btnBack = new JButton("Back");
        btnBack.setBounds(650, 20, 100, 30);
        frame.add(btnBack);

        // Table
        itemModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Available"},0);
        itemTable = new JTable(itemModel);
        JScrollPane scroll = new JScrollPane(itemTable);
        scroll.setBounds(20, 120, 740, 300);
        frame.add(scroll);

        loadItems();

        // Button Events
        btnAddItem.addActionListener(e -> addItem());
        availablecb.addActionListener(e -> availability());
        btnRefresh.addActionListener(e -> loadItems());
        btnSalesReport.addActionListener(e -> showSalesReport());
        btnBack.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        frame.setSize(800, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void loadItems() 
    {
        itemModel.setRowCount(0);
        
        String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM items");
            while(rs.next()) {
                itemModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                });
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    void addItem() 
    {
    	JTable table = null;
    	DefaultTableModel model;
    	JScrollPane scroll = null;
    	
        String name = tfName.getText();
        String priceText = tfPrice.getText();
        
        if(name.isEmpty() || priceText.isEmpty()) 
        {
            JOptionPane.showMessageDialog(frame, "Enter Name and Price");
            return;
        }
        
        String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            double price = Double.parseDouble(priceText);
            PreparedStatement pst = con.prepareStatement("INSERT INTO items(name, price, available) VALUES(?,?,true)");
            pst.setString(1, name);
            pst.setDouble(2, price);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(frame,"Item Added Successfully");
            tfName.setText(""); 
            tfPrice.setText("");
            loadItems();
            
            if (scroll == null)
            {
            	scroll = new JScrollPane(table);
            	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            	
            	scroll.setPreferredSize(new Dimension(400, 200));
            	frame.add(scroll, BorderLayout.CENTER);
            	frame.revalidate();
            	frame.repaint();
            }
            
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    void availability() 
    {
        // Read values from text fields
        String name = tfName.getText();
        String priceText = tfPrice.getText();
        
        if(name.isEmpty() || priceText.isEmpty()) 
        {
            JOptionPane.showMessageDialog(frame, "Enter Name and Price");
            return;
        }

        double price = Double.parseDouble(priceText);
        boolean avail = availablecb.isSelected(); // checkbox
         
        String host = "jdbc:mysql://localhost:3306/";
        String db = "cafe";
        String url = host + db;

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");

            // Use PreparedStatement to avoid SQL injection
            String sql = "INSERT INTO items(name, price, available) VALUES(?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setBoolean(3, avail); // checkbox value

            int ans = pst.executeUpdate();
            if(ans > 0) {
                JOptionPane.showMessageDialog(frame, "Item Added Successfully");
                tfName.setText("");
                tfPrice.setText("");
                availablecb.setSelected(false); // reset checkbox
            }

        } 
        catch (Exception e2)
        {
            e2.printStackTrace();
        }		
    }

    void showSalesReport() 
    {
        String[] options = {"Week","Month","Year"};
        int choice = JOptionPane.showOptionDialog(frame,"Select Report Type","Sales Report",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
        if(choice==-1) return;
        String query="";
        
        switch(choice)
        {
            case 0: query="SELECT item_name, SUM(price) as total, COUNT(*) as quantity FROM orders WHERE order_datetime >= DATE_SUB(NOW(), INTERVAL 7 DAY) GROUP BY item_name"; break;
            case 1: query="SELECT item_name, SUM(price) as total, COUNT(*) as quantity FROM orders WHERE order_datetime >= DATE_SUB(NOW(), INTERVAL 1 MONTH) GROUP BY item_name"; break;
            case 2: query="SELECT item_name, SUM(price) as total, COUNT(*) as quantity FROM orders WHERE order_datetime >= DATE_SUB(NOW(), INTERVAL 1 YEAR) GROUP BY item_name"; break;
        }
        
        String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
           
            // Table Model
            String columnNames[] = {"Item", "Quantity", "Total"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            double grandTotal = 0;
 
            while(rs.next())
            {	
            	String item = rs.getString("item_name");
            	int quantity = rs.getInt("quantity");
            	double total = rs.getDouble("total");
            	grandTotal += total;
            	
            	model.addRow(new Object[] {item, quantity, total});
            }
            
            JTable table = new JTable(model);
            table.setFillsViewportHeight(true);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setPreferredSize(new java.awt.Dimension(450, 250));
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 10));
            
            JLabel grandLabel = new JLabel("Grand Total = " + grandTotal);
            grandLabel.setHorizontalAlignment(SwingConstants.CENTER);
            grandLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
            
            panel.add(grandLabel, BorderLayout.NORTH); // top
            panel.add(scroll, BorderLayout.CENTER); // table below
            
            JOptionPane.showMessageDialog(frame, scroll, "Sales Report",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        { 
        	e.printStackTrace();
        }
    }
}

// ------------------------ USER FRAME ------------------------
class UserFrame {

    JFrame frame;
    JLabel lblTable, lblItem, lblMenu;
    JTextField tfTable, tfItem;
    JTextArea menuArea;
    JTable orderTable;
    DefaultTableModel model;

    JButton btnSearch, btnAdd, btnRemove, btnOrder, btnBill, btnBack;

    double totalBill = 0;
    int totalItems = 0;

    public UserFrame() 
    {

        frame = new JFrame("Cafe User Panel");
        frame.setLayout(null);

        lblTable = new JLabel("Table No:");
        lblTable.setBounds(20, 20, 80, 25);
        frame.add(lblTable);

        tfTable = new JTextField();
        tfTable.setBounds(100, 20, 100, 25);
        frame.add(tfTable);

        lblMenu = new JLabel("Menu");
        lblMenu.setBounds(20, 60, 100, 25);
        frame.add(lblMenu);

        menuArea = new JTextArea();
        menuArea.setEditable(false);
        JScrollPane menuScroll = new JScrollPane(menuArea);
        menuScroll.setBounds(20, 90, 250, 150);
        frame.add(menuScroll);

        lblItem = new JLabel("Item Name:");
        lblItem.setBounds(20, 260, 100, 25);
        frame.add(lblItem);

        tfItem = new JTextField();
        tfItem.setBounds(100, 260, 170, 25);
        frame.add(tfItem);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(20, 300, 100, 30);
        frame.add(btnSearch);

        btnAdd = new JButton("Add Item");
        btnAdd.setBounds(140, 300, 130, 30);
        frame.add(btnAdd);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(20, 340, 250, 30);
        frame.add(btnBack);

        model = new DefaultTableModel(
                new Object[]{"Item", "Price"}, 0);
        orderTable = new JTable(model);
        JScrollPane tableScroll = new JScrollPane(orderTable);
        tableScroll.setBounds(300, 20, 260, 220);
        frame.add(tableScroll);

        btnRemove = new JButton("Remove Item");
        btnRemove.setBounds(300, 250, 260, 30);
        frame.add(btnRemove);

        btnOrder = new JButton("Place Order");
        btnOrder.setBounds(300, 300, 120, 35);
        frame.add(btnOrder);

        btnBill = new JButton("Generate Bill");
        btnBill.setBounds(440, 300, 120, 35);
        frame.add(btnBill);

        frame.setSize(600, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loadMenu();

        // EVENTS
        btnSearch.addActionListener(e -> searchItem());
        btnAdd.addActionListener(e -> addItem());
        btnRemove.addActionListener(e -> removeItem());
        btnOrder.addActionListener(e -> placeOrder());
        btnBill.addActionListener(e -> generateBill());
        btnBack.addActionListener(e -> {frame.dispose(); 
        								new MainFrame();
        						       });
        
    }

    


	// Load Menu
    void loadMenu() 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM items WHERE available=true");

            menuArea.setText("");
            while (rs.next()) {
                menuArea.append(rs.getString("name") +
                        " - Rs." + rs.getDouble("price") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Item
    void searchItem() 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM items WHERE name='" +
                            tfItem.getText() + "' AND available=true");

            if (rs.next())
                JOptionPane.showMessageDialog(frame,
                        "Item Found\nRs. " + rs.getDouble("price"));
            else
                JOptionPane.showMessageDialog(frame,
                        "Item Not Available");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Item
    void addItem() 
    {
    	String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM items WHERE name='" +
                            tfItem.getText() + "' AND available=true");

            if (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getDouble("price")
                });
                totalBill += rs.getDouble("price");
                totalItems++;
            } else {
                JOptionPane.showMessageDialog(frame, "Item Not Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove Item
    void removeItem() {
        int row = orderTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Select Item");
            return;
        }

        totalBill -= (double) model.getValueAt(row, 1);
        totalItems--;
        model.removeRow(row);
    }

    // Place Order
    void placeOrder() {

        if (tfTable.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter Table No");
            return;
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "No Items Added");
            return;
        }

        String[] options = {"COD", "ONLINE"};
        int ch = JOptionPane.showOptionDialog(
                frame, "Select Payment Mode", "Payment",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (ch == -1) return;

        String payment = options[ch];

        String host = "jdbc:mysql://localhost:3306/";
		String db = "cafe";
		String url = host+db;
		
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();

            for (int i = 0; i < model.getRowCount(); i++) {
                st.executeUpdate(
                        "INSERT INTO orders(table_no,item_name,price,payment_mode,order_datetime) VALUES(" +
                                tfTable.getText() + ",'" +
                                model.getValueAt(i, 0) + "'," +
                                model.getValueAt(i, 1) + ",'" +
                                payment + "',NOW())");
            }

            JOptionPane.showMessageDialog(frame, "Order Placed Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate Bill
    void generateBill() {
        JOptionPane.showMessageDialog(frame,
                "------ CAFE BILL ------\n" +
                        "Total Items : " + totalItems +
                        "\nTotal Bill : Rs." + totalBill);
    }

}
