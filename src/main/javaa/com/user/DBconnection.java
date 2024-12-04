package com.user;
//
//package src.main.javaa.default package;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBconnection {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/user-web-app"; // Database name should be user-web-app. 
//    private static final String USER = "root"; // MySQL username
//    private static final String PASSWORD = "Somya@2236"; // MySQL password
//
//    private static Connection connection = null;
//
//    public static Connection getConnection() throws SQLException {
//        if (connection == null) {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }}

// Adjust according to your project structure
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/user-web-app"; // Database URL
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = "Somya@2236"; // MySQL password

    private static Connection connection = null;

    // Get connection method
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) { // Check if the connection is closed
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Register JDBC driver
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); // Establish connection
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); // Handle class not found exception
                throw new SQLException("JDBC Driver not found.", e); // Wrap in SQLException
            }
        }
        return connection;
    }

    // Close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close(); // Close the connection
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQL exception during closing
            }
        }
    }
}
