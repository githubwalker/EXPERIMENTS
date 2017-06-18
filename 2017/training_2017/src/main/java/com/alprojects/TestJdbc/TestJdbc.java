package com.alprojects.TestJdbc;

import org.junit.Test;

import java.sql.*;

/**
 * Created by andrew on 19.06.2017.
 */
public class TestJdbc
{
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/University";

    public static void testItself() throws ClassNotFoundException, SQLException
    {
        Class.forName(JDBC_DRIVER);

        try(
                Connection conn = DriverManager.getConnection(DB_URL, "root", "1");
                PreparedStatement stmt = conn.prepareStatement("select * from students where age > ?");
                )
        {
            stmt.setLong(1, 100);
            ResultSet rst = stmt.executeQuery();

            while (rst.next())
            {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                int age = rst.getInt("age");
                System.out.println(String.format("id: %d, name: %s, age: %d", id, name, age));
            }
        }
    }


    public static void testInsert() throws ClassNotFoundException, SQLException
    {
        Class.forName(JDBC_DRIVER);

        try(
                Connection conn = DriverManager.getConnection(DB_URL, "root", "1");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO students(name, age) VALUE (?, ?)");
        )
        {
            stmt.setString(1, "One More Person");
            stmt.setLong(2, 33);
            stmt.execute();
        }
    }

}
