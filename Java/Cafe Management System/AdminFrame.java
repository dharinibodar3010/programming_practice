package com.CafeMgtSys;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    DefaultTableModel model;
    JTable table;
    JTextField tfName, tfPrice;

    public AdminFrame() {
        setTitle("Admin Dashboard - Manage Cafe");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Top Panel for Inputs and Buttons
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel inputFields = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        tfName = new JTextField(12);
        tfPrice = new JTextField(7);
        inputFields.add(new JLabel("Item Name:")); inputFields.add(tfName);
        inputFields.add(new JLabel("Price:")); inputFields.add(tfPrice);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAdd = new JButton("Add Item");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnReport = new JButton("Sales Report");
        JButton btnBack = new JButton("Logout");

        // Styling Buttons
        btnAdd.setBackground(new Color(46, 204, 113)); btnAdd.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(231, 76, 60)); btnDelete.setForeground(Color.WHITE);
        btnRefresh.setBackground(new Color(52, 152, 219)); btnRefresh.setForeground(Color.WHITE);

        buttonPanel.add(btnAdd); buttonPanel.add(btnDelete); 
        buttonPanel.add(btnRefresh); buttonPanel.add(btnReport); buttonPanel.add(btnBack);

        topPanel.add(inputFields); topPanel.add(buttonPanel);

        // Colorful Table Setup
        model = new DefaultTableModel(new String[]{"ID", "Item Name", "Price", "Status"}, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(174, 214, 241));
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        btnAdd.addActionListener(e -> addItem());
        btnDelete.addActionListener(e -> deleteItem());
        btnRefresh.addActionListener(e -> loadItems());
        btnReport.addActionListener(e -> showSalesReport());
        btnBack.addActionListener(e -> { dispose(); new MainFrame(); });

        loadItems();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadItems() {
        model.setRowCount(0);
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM items");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4) ? "Available" : "No"});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    void addItem() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO items(name, price, available) VALUES(?,?,true)");
            pst.setString(1, tfName.getText());
            pst.setDouble(2, Double.parseDouble(tfPrice.getText()));
            pst.executeUpdate();
            loadItems(); // Refresh after adding
            tfName.setText(""); tfPrice.setText("");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Check Input!"); }
    }

    void deleteItem() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        int id = (int) model.getValueAt(row, 0);
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM items WHERE id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            loadItems(); // Refresh after delete
        } catch (Exception e) { e.printStackTrace(); }
    }

 // Method to generate and show Date-wise Sales Report in a colorful Table
    void showSalesReport() {
        String[] options = {"Week", "Month", "Year"};
        int choice = JOptionPane.showOptionDialog(this, "Select Sales Period", "Sales Report", 
                     0, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice == -1) return; // If user closes the dialog

        // Setting SQL interval based on selection
        String interval = (choice == 0) ? "7 DAY" : (choice == 1) ? "1 MONTH" : "1 YEAR";
        
        // SQL Query to fetch Item-wise Quantity and Total Price
        String sql = "SELECT item_name, COUNT(*) as qty, SUM(price) as total FROM orders " +
                     "WHERE order_datetime >= DATE_SUB(NOW(), INTERVAL " + interval + ") " +
                     "GROUP BY item_name";

        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            // Setting up Table Model for Report
            String[] columnNames = {"Item Name", "Quantity Sold", "Total Sales (Rs.)"};
            DefaultTableModel reportModel = new DefaultTableModel(columnNames, 0);
            double grandTotal = 0;

            while (rs.next()) {
                String name = rs.getString("item_name");
                int qty = rs.getInt("qty");
                double total = rs.getDouble("total");
                grandTotal += total; // Calculating overall total
                
                reportModel.addRow(new Object[]{name, qty, total});
            }

            // Creating the Report Table
            JTable reportTable = new JTable(reportModel);
            reportTable.setRowHeight(25);
            reportTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            reportTable.setSelectionBackground(new Color(174, 214, 241));

            // Styling the Report Table Header
            JTableHeader header = reportTable.getTableHeader();
            header.setBackground(new Color(41, 128, 185)); // Blue Header
            header.setForeground(Color.WHITE);
            header.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JScrollPane scrollPane = new JScrollPane(reportTable);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            // Panel for Header Label showing Grand Total
            JPanel headerPanel = new JPanel(new BorderLayout());
            JLabel lblGrandTotal = new JLabel("TOTAL SALES (" + options[choice] + "): Rs. " + grandTotal);
            lblGrandTotal.setFont(new Font("Arial", Font.BOLD, 16));
            lblGrandTotal.setForeground(new Color(44, 62, 80));
            lblGrandTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            headerPanel.add(lblGrandTotal, BorderLayout.CENTER);

            // Final Report Panel
            JPanel mainReportPanel = new JPanel(new BorderLayout(10, 10));
            mainReportPanel.add(headerPanel, BorderLayout.NORTH); // Total Sales on Top
            mainReportPanel.add(scrollPane, BorderLayout.CENTER); // Table in Middle

            // Displaying the Colorful Report Dialog
            JOptionPane.showMessageDialog(this, mainReportPanel, "Sales Analysis", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating report: " + e.getMessage());
        }
    }
}