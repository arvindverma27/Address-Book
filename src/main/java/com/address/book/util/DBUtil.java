package com.address.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private final String URL = "jdbc:mysql://localhost:3306/address_book";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(DRIVER);

		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		System.out.println("Connection Created");

		return connection;

	}

	public void close(PreparedStatement pstmt, Connection connection) throws SQLException {

		if (pstmt != null)

			pstmt.close();

		if (connection != null)

			connection.close();

	}

	public void close(PreparedStatement pstmt, Connection connection, ResultSet rs) throws SQLException {

		if (pstmt != null)

			pstmt.close();

		if (connection != null)

			connection.close();

		if (rs != null)
			rs.close();

	}

	public static void main(String[] args) {

		DBUtil dbUtil = new DBUtil();

		try {
			dbUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
