package com.CafeMgtSys;
import javax.swing.*;
import java.sql.*;

public class AdminLogin extends JFrame {
    JTextField usertf;
    JPasswordField passtf;

    public AdminLogin() {
        setTitle("Admin Login");
        setSize(350, 250);
        setLayout(null);

        JLabel l1 = new JLabel("Username:"); l1.setBounds(50, 40, 80, 25);
        usertf = new JTextField(); usertf.setBounds(140, 40, 140, 25);

        JLabel l2 = new JLabel("Password:"); l2.setBounds(50, 80, 80, 25);
        passtf = new JPasswordField(); passtf.setBounds(140, 80, 140, 25);

        JButton loginbtn = new JButton("Login");
        loginbtn.setBounds(110, 140, 100, 35);

        // Call login logic on button click
        loginbtn.addActionListener(e -> login());

        add(l1); add(usertf); add(l2); add(passtf); add(loginbtn);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
        try (Connection con = DBConnection.getConnection()) {
            // Secure login using PreparedStatement
            PreparedStatement ps = con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1, usertf.getText());
            ps.setString(2, new String(passtf.getPassword()));
            
            if (ps.executeQuery().next()) { 
                dispose(); 
                new AdminFrame(); // Open Admin Dashboard on success
            } else { 
                JOptionPane.showMessageDialog(this, "Invalid Username or Password"); 
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}