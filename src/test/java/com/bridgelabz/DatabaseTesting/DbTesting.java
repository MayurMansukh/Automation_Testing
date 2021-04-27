package com.bridgelabz.DatabaseTesting;
import org.junit.Test;
import java.sql.*;

public class DbTesting {
    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        //Driver loaded
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection created
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Testing", "root", "1234567890");
        //Statement created
        Statement smt = con.createStatement();
        //Query Executed
        ResultSet rs = smt.executeQuery("Select * from user ");

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String userType = rs.getString(3);
            System.out.println(id + " " + name + " " + userType);

        }
    }
}
