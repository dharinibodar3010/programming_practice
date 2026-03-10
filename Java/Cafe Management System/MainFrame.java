package com.CafeMgtSys;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Cafe Management System");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Using GridBagLayout for better UI centering

        // Panel to hold buttons
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        
        JButton btnAdmin = new JButton("Admin Portal");
        JButton btnUser = new JButton("User Portal");

        // Action listener to open Admin Login window
        btnAdmin.addActionListener(e -> { 
            dispose(); // Close current frame
            new AdminLogin(); 
        });

        // Action listener to open User window
        btnUser.addActionListener(e -> { 
            dispose(); 
            new UserFrame(); 
        });

        panel.add(btnAdmin);
        panel.add(btnUser);
        add(panel);
        
        setLocationRelativeTo(null); // Center window on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame(); // Entry point of the application
    }
}