package dao;

import model.Payment;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO 
{

    // ================= ADD PAYMENT =================
    public void addPayment(Payment p) 
    {
        String sql = "INSERT INTO payments (member_id, payment_date, membership_fee, trainer_fee, total_amount) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, p.getMemberId());
                ps.setDate(2, Date.valueOf(p.getPaymentDate()));
                ps.setDouble(3, p.getMembershipFee());
                ps.setDouble(4, p.getTrainerFee());
                ps.setDouble(5, p.getTotalAmount());

                ps.executeUpdate();
                System.out.println("Payment recorded successfully.");
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error adding payment: " + e.getMessage());
        }
    }

    // ================= GENERATE PAYMENT =================
    public void generatePayment(int memberId, String planType, boolean hasTrainer) 
    {
        double membershipFee = 0;
        double trainerFee = 0;

        if (planType.equalsIgnoreCase("Basic")) 
        {
            membershipFee = 1000;
        } 
        else if (planType.equalsIgnoreCase("Gold"))
        {
            membershipFee = 2000;
        } 
        else if (planType.equalsIgnoreCase("Platinum"))
        {
            membershipFee = 3000;
        }

        if (hasTrainer)
        {
            trainerFee = 1500;
        }

        Payment p = new Payment(
                memberId,
                LocalDate.now(),
                membershipFee,
                trainerFee
        );

        addPayment(p); // SINGLE INSERT POINT
    }

    // ================= VIEW PAYMENTS =================
    public List<Payment> getPaymentsByMemberId(int memberId) 
    {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE member_id = ?";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) 
                {
                    Payment p = new Payment();
                    p.setId(rs.getInt("payment_id"));
                    p.setMemberId(rs.getInt("member_id"));
                    p.setPaymentDate(rs.getDate("payment_date").toLocalDate());
                    p.setMembershipFee(rs.getDouble("membership_fee"));
                    p.setTrainerFee(rs.getDouble("trainer_fee"));
                    p.setTotalAmount(rs.getDouble("total_amount"));

                    list.add(p);
                }
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching payments: " + e.getMessage());
        }

        return list;
    }
}