package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Trainer;
import util.DBConnection;

public class TrainerDAO 
{
    // Add Trainer
    public void addTrainer(Trainer t) 
    {
        String sql = "INSERT INTO trainers (name, specialty, schedule) VALUES (?, ?, ?)";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setString(1, t.getName());
                ps.setString(2, t.getSpecialty());
                ps.setString(3, t.getSchedule());
                ps.executeUpdate();

                System.out.println("Trainer added successfully");
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // View Trainers
    public List<Trainer> getAllTrainers() 
    {
        List<Trainer> list = new ArrayList<>();
        String sql = "SELECT * FROM trainers";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next()) 
                {
                    Trainer t = new Trainer();
                    t.setTrainerId(rs.getInt("trainer_id"));
                    t.setName(rs.getString("name"));
                    t.setSpecialty(rs.getString("specialty"));
                    t.setSchedule(rs.getString("schedule"));
                    list.add(t);
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return list;
    }

    // Assign Trainer to Member
    public void assignTrainerToMember(int memberId, String planType)
    {
        String findTrainer =
                "SELECT trainer_id FROM trainers WHERE specialty=? LIMIT 1";

        String insert =
                "INSERT INTO member_trainer (member_id, trainer_id, assigned_date) VALUES (?, ?, ?)";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps1 = con.prepareStatement(findTrainer)) 
            {
                ps1.setString(1, planType);
                ResultSet rs = ps1.executeQuery();

                if (rs.next()) 
                {
                    int trainerId = rs.getInt("trainer_id");

                    try (PreparedStatement ps2 = con.prepareStatement(insert)) 
                    {
                        ps2.setInt(1, memberId);
                        ps2.setInt(2, trainerId);
                        ps2.setDate(3, Date.valueOf(java.time.LocalDate.now()));
                        ps2.executeUpdate();
                    }

                    System.out.println("Trainer assigned successfully");
                } 
                else 
                {
                    System.out.println("No trainer available for this plan");
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}