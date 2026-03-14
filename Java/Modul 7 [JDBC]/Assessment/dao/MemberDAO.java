package dao;

import model.Member;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//GymManagerApp part - 3

public class MemberDAO 
{
    // ADD MEMBER
    public void addMember(Member m) 
    {
        String sql = "INSERT INTO members (name, age, contact, plan_type, registration_date, branch_id) VALUES (?, ?, ?, ?, ?, ?)";

        try 
        {
            // Load MySQL Driver
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {

                ps.setString(1, m.getName());
                ps.setInt(2, m.getAge());
                ps.setString(3, m.getContact());
                ps.setString(4, m.getPlanType());
                ps.setDate(5, Date.valueOf(m.getRegistrationDate()));
                ps.setInt(6, m.getBranchId());   //BRANCH ID HERE

                ps.executeUpdate();
                System.out.println("Member added successfully.");

            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    // GET ALL MEMBERS
    public List<Member> getAllMembers() 
    {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try 
        {
            // Load MySQL Driver
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next()) 
                {
                    Member m = new Member();
                    m.setId(rs.getInt("member_id"));
                    m.setName(rs.getString("name"));
                    m.setAge(rs.getInt("age"));
                    m.setContact(rs.getString("contact"));
                    m.setPlanType(rs.getString("plan_type"));
                    m.setRegistrationDate(
                            rs.getDate("registration_date").toLocalDate());
                    m.setBranchId(rs.getInt("branch_id"));                
                    
                    members.add(m);
                }
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching members: " + e.getMessage());
        }

        return members;
    }
    
 // SEARCH MEMBER BY ID
    public void searchById(int id) 
    {
        String sql = "SELECT * FROM members WHERE member_id = ?";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) 
                {
                    System.out.println("ID | Name | Age | Contact | Plan | Date");
                    System.out.println(
                            rs.getInt("member_id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getInt("age") + " | " +
                            rs.getString("contact") + " | " +
                            rs.getString("plan_type") + " | " +
                            rs.getDate("registration_date") + " | " +
                            rs.getInt("branch_id")
                    );
                } 
                else 
                {
                    System.out.println("Member not found.");
                }
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Search error: " + e.getMessage());
        }
    }

 // SEARCH MEMBER BY CONTACT
    public void searchByContact(String contact) 
    {
        String sql = "SELECT * FROM members WHERE contact = ?";

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setString(1, contact);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) 
                {
                    System.out.println("ID | Name | Age | Contact | Plan | Date");
                    System.out.println(
                            rs.getInt("member_id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getInt("age") + " | " +
                            rs.getString("contact") + " | " +
                            rs.getString("plan_type") + " | " +
                            rs.getDate("registration_date") + " | " +
                            rs.getInt("branch_id")
                    );
                } 
                else 
                {
                    System.out.println("Member not found.");
                }
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Search error: " + e.getMessage());
        }
    }
    
    public List<Member> getMembersByBranchId(int branchId) 
    {
        List<Member> list = new ArrayList<>();

        String sql = "SELECT * FROM members WHERE branch_id = ?";

        try
        {
        	 Class.forName("com.mysql.jdbc.Driver");
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, branchId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                Member m = new Member(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("contact"),
                        rs.getString("plan_type"),
                        rs.getDate("registration_date").toLocalDate()
                );

                m.setId(rs.getInt("member_id"));
                m.setBranchId(rs.getInt("branch_id"));

                list.add(m);
            }
        }

        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching branch-wise members");
            e.printStackTrace();
        }

        return list;
    }
    

    // UPDATE MEMBER
    public void updateMember(Member m) 
    {
        String sql =
            "UPDATE members SET name=?, age=?, contact=?, plan_type=?, branch_id=? " +
            "WHERE member_id=?";

        try 
        {
            // Load MySQL Driver
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setString(1, m.getName());
                ps.setInt(2, m.getAge());
                ps.setString(3, m.getContact());
                ps.setString(4, m.getPlanType());
                ps.setInt(5, m.getBranchId());
                ps.setInt(6, m.getId());

                int rows = ps.executeUpdate();

                if (rows > 0) 
                {
                    System.out.println("Member updated successfully");
                } 
                else 
                {
                    System.out.println("Member not found");
                }

            } 
            catch (SQLException e) 
            {
                System.out.println("SQL Error while updating member: " + e.getMessage());
            }

        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        }
    }
    
    // DELETE MEMBER 
    public void deleteMember(int memberId) 
    {
        String sql = "DELETE FROM members WHERE member_id = ?";

        try
        {
            // Load MySQL Driver
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, memberId);
                int rows = ps.executeUpdate();

                if (rows > 0) 
                {
                    System.out.println("Member deleted successfully.");
                } 
                else 
                {
                    System.out.println("Member not found.");
                }

            } 
            catch (SQLException e) 
            {
                System.out.println("SQL Error deleting member: " + e.getMessage());
            }

        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        }
    }
}
      
        
   



