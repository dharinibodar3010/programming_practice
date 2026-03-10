package com.CafeMgtSys;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class UserFrame extends JFrame {
    // UI Components
    JComboBox<String> itemCombo;
    DefaultTableModel orderModel;
    JTable orderTable;
    JTextField tfTable, tfSearch;
    JLabel lblTotal;
    double totalAmt = 0;

    public UserFrame() {
        // --- Frame Settings ---
        setTitle("Cafe Management - Customer Order");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(new Color(248, 249, 250)); // Soft background color

        // --- LEFT PANEL: Ordering Controls ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(380, 650));

        // Table Booking Section
        JLabel lblTable = new JLabel("1. Book Table Number:");
        lblTable.setFont(new Font("Arial", Font.BOLD, 14));
        tfTable = new JTextField();
        tfTable.setMaximumSize(new Dimension(300, 35));

        // Live Search Section
        JLabel lblSearch = new JLabel("2. Search Item (Type to filter):");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
        tfSearch = new JTextField();
        tfSearch.setMaximumSize(new Dimension(300, 35));
        
        // --- LIVE SEARCH LOGIC: Triggers on every key press ---
        tfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadItems(tfSearch.getText()); // Filter menu as you type
                if(itemCombo.getItemCount() > 0) {
                    itemCombo.setPopupVisible(true); // Automatically show options
                }
            }
        });

        // Menu Dropdown
        itemCombo = new JComboBox<>();
        itemCombo.setMaximumSize(new Dimension(300, 40));
        loadItems(""); // Initial load of all items

        // Action Buttons
        JButton btnAdd = createStyledButton("Add to List", new Color(46, 204, 113));
        JButton btnRemove = createStyledButton("Remove Selected", new Color(231, 76, 60));
        JButton btnPlaceOrder = createStyledButton("Confirm Order & Get Bill", new Color(52, 152, 219));
        JButton btnBack = createStyledButton("Back to Main Menu", new Color(149, 165, 166));

        // Adding components to Left Panel
        leftPanel.add(lblTable); leftPanel.add(tfTable);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(lblSearch); leftPanel.add(tfSearch);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(itemCombo);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(btnAdd);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(btnRemove);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(btnPlaceOrder);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(btnBack);

        // --- RIGHT PANEL: Attractive Table UI ---
        orderModel = new DefaultTableModel(new String[]{"Item Name", "Price (Rs.)"}, 0);
        orderTable = new JTable(orderModel) {
            // Zebra Stripe Rendering for attractive look
            public Component prepareRenderer(DefaultTableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 245, 249));
                }
                return c;
            }
        };

        // Table Styling
        orderTable.setRowHeight(35);
        orderTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        orderTable.setSelectionBackground(new Color(174, 214, 241));
        
        // Header Styling
        JTableHeader header = orderTable.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Selected Order List"));

        // Total Amount Label
        lblTotal = new JLabel("Grand Total: Rs. 0.0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 22));
        lblTotal.setForeground(new Color(192, 57, 43));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(lblTotal, BorderLayout.SOUTH);

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // --- BUTTON ACTIONS ---

        // Add item to the table and update total
        btnAdd.addActionListener(e -> {
            String selected = (String) itemCombo.getSelectedItem();
            if (selected != null) {
                String name = selected.split(" - Rs. ")[0];
                double price = Double.parseDouble(selected.split("Rs. ")[1]);
                orderModel.addRow(new Object[]{name, price});
                totalAmt += price;
                lblTotal.setText("Grand Total: Rs. " + totalAmt);
            }
        });

        // Remove item from table and subtract price from total
        btnRemove.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            if (row != -1) {
                totalAmt -= (double) orderModel.getValueAt(row, 1);
                orderModel.removeRow(row);
                lblTotal.setText("Grand Total: Rs. " + totalAmt);
            } else {
                JOptionPane.showMessageDialog(this, "Select an item to remove!");
            }
        });

        // Confirm order and generate final bill
        btnPlaceOrder.addActionListener(e -> handleOrder());

        // Go back to main menu
        btnBack.addActionListener(e -> { dispose(); new MainFrame(); });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method for styling buttons
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(320, 45));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    // Load and filter items from DB
    void loadItems(String keyword) {
        itemCombo.removeAllItems();
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT name, price FROM items WHERE available=true AND name LIKE ?");
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itemCombo.addItem(rs.getString("name") + " - Rs. " + rs.getDouble("price"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Save order and show Bill Dialog
    void handleOrder() {
        if (tfTable.getText().isEmpty() || orderModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Enter Table No and Add at least one item!");
            return;
        }

        // Payment Mode Selection
        String[] paymentOptions = {"Cash on Delivery (COD)", "Online UPI / Card"};
        int payChoice = JOptionPane.showOptionDialog(this, "Select Payment Method", "Payment",
                0, JOptionPane.QUESTION_MESSAGE, null, paymentOptions, paymentOptions[0]);

        if (payChoice == -1) return;
        String mode = (payChoice == 0) ? "COD" : "Online";

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO orders(table_no, item_name, price, payment_mode, order_datetime) VALUES(?,?,?,?,NOW())");
            
            StringBuilder bill = new StringBuilder("     *** CAFE BILL RECEIPT ***\n");
            bill.append("Table No: ").append(tfTable.getText()).append("\n");
            bill.append("------------------------------------------------\n");
            bill.append("Item Name\t\tPrice\n");
            bill.append("------------------------------------------------\n");

            for (int i = 0; i < orderModel.getRowCount(); i++) {
                String name = orderModel.getValueAt(i, 0).toString();
                double price = (double) orderModel.getValueAt(i, 1);
                
                // Saving to Database
                pst.setString(1, tfTable.getText());
                pst.setString(2, name);
                pst.setDouble(3, price);
                pst.setString(4, mode);
                pst.executeUpdate();

                // Adding to Bill UI
                bill.append(name).append("\t\tRs. ").append(price).append("\n");
            }

            bill.append("------------------------------------------------\n");
            bill.append("GRAND TOTAL: \tRs. ").append(totalAmt).append("\n");
            bill.append("Payment Mode: \t").append(mode).append("\n");
            bill.append("------------------------------------------------\n");
            bill.append("      Thank You! Visit Again!");

            // Show Bill in a pop-up
            JOptionPane.showMessageDialog(this, new JTextArea(bill.toString()), "Bill Details", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear current order for next customer
            orderModel.setRowCount(0);
            totalAmt = 0;
            lblTotal.setText("Grand Total: Rs. 0.0");
            tfTable.setText("");

        } catch (Exception e) { e.printStackTrace(); }
    }
}