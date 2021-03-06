package com.bridgelabz.DatabaseTesting;
import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class DbTesting {
    Connection con;

    @BeforeTest
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        //Driver loaded
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection created
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Testing", "root", "1234567890");
        //Statement created
        return con;
    }

    @Test
    public void get_table_data() throws ClassNotFoundException, SQLException {
        con = this.getConnection();
        Statement smt = con.createStatement();
        //Query Executed
        ResultSet rs = smt.executeQuery("select * from user");
        int count = 0;
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String userType = rs.getString(3);
            System.out.println(id + " " + name + " " + userType);
            count++;
        }
        Assert.assertEquals(5,count);
    }

    @Test
    public void insert_values_into_table() throws ClassNotFoundException, SQLException {
        con = this.getConnection();
        PreparedStatement pst=con.prepareStatement("insert into user values(?,?,?)");

        pst.setInt(1,4);
        pst.setString(2,"Alex");
        pst.setString(3,"prime");
        pst.executeUpdate();
    }

    @Test
    public void update_values_into_table() throws ClassNotFoundException, SQLException {
        con = this.getConnection();
        PreparedStatement pst=con.prepareStatement("UPDATE user SET name = ? WHERE user_id = ?");

        pst.setString(1,"ramesh");
        pst.setInt(2,3);
        pst.executeUpdate();
    }

    @Test
    public void delete_row_from_table() throws ClassNotFoundException, SQLException {
        con = this.getConnection();
        PreparedStatement pst=con.prepareStatement("DELETE FROM user WHERE user_id = ?");
        pst.setInt(1,4);
        pst.executeUpdate();

    }
}

