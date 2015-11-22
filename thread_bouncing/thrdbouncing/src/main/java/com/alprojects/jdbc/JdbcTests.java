package com.alprojects.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTests {
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://localhost/new_schema";


	public static void testMySql() {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, "andrew", "170032");
			stmt = conn.createStatement();
			String sql = "select * from new_table";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				System.out.println("id = " + Integer.toString(id) + "; name = "
						+ name);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void testParametrizedStatement() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, "andrew", "170032");
			
			conn.setAutoCommit(false);
			// stmt = conn.createStatement();
			stmt = conn.prepareStatement( "update new_table set name = ? where id = ?" );
			stmt.setString(1, "TestName");
			stmt.setInt(2, 5);
			
			stmt.executeUpdate();
			conn.commit();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void performTests() {
		testParametrizedStatement();
		testMySql();
	}
}
