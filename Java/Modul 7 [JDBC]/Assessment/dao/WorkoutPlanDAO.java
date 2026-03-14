package dao;

import model.WorkoutPlan;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanDAO 
{
    // ASSIGN WORKOUT PLAN
    public void addWorkoutPlan(WorkoutPlan w) 
    {
        String sql = "INSERT INTO workout_plans (member_id, cardio, strength, flexibility) VALUES (?, ?, ?, ?)";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, w.getMemberId());
                ps.setString(2, w.getCardio());
                ps.setString(3, w.getStrength());
                ps.setString(4, w.getFlexibility());

                ps.executeUpdate();
                System.out.println("Workout plan assigned successfully.");
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error adding workout plan: " + e.getMessage());
        }
    }

    // VIEW WORKOUT PLAN BY MEMBER ID
    public List<WorkoutPlan> getWorkoutByMemberId(int memberId) 
    {
        List<WorkoutPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM workout_plans WHERE member_id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) 
                {
                    WorkoutPlan w = new WorkoutPlan();
                    w.setId(rs.getInt("plan_id"));
                    w.setMemberId(rs.getInt("member_id"));
                    w.setCardio(rs.getString("cardio"));
                    w.setStrength(rs.getString("strength"));
                    w.setFlexibility(rs.getString("flexibility"));

                    list.add(w);
                }
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching workout plan: " + e.getMessage());
        }

        return list;
    }
    
    // Update workout plans (Cardio, Strength, Flexibility)
    public void updateWorkoutPlanByMemberId(WorkoutPlan w) 
    {
        String sql = "UPDATE workout_plans SET cardio=?, strength=?, flexibility=? WHERE member_id=?";

        try {
            // Load MySQL Driver (MySQL 8+)
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setString(1, w.getCardio());
                ps.setString(2, w.getStrength());
                ps.setString(3, w.getFlexibility());
                ps.setInt(4, w.getMemberId());

                int rows = ps.executeUpdate();

                if (rows > 0) 
                {
                    System.out.println("Workout plan updated successfully");
                } 
                else 
                {
                    System.out.println("No workout plan found for this member");
                }

            } 
            catch (SQLException e) 
            {
                System.out.println("SQL Error while updating workout plan: " + e.getMessage());
            }

        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        }
    }
}

