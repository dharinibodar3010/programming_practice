package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//GymManagerApp part -4

public class DBConnection 
{
	
	private static final String host = "jdbc:mysql://localhost:3306/";
	private static final String db = "gym_db";
	private static final String url = host+db;
		
    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(url, "root", "");
    }
}
