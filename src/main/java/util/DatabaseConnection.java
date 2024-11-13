package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_manager"; // Update with your database name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "vhivfrc#7878"; // Replace with your MySQL password

    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    /**
     * Gets the singleton connection instance. If it doesn't exist, it creates a new one.
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error connecting to the database.");
                throw e;
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it's open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Reset the connection to allow re-connection if needed
            } catch (SQLException e) {
                System.err.println("Error closing the database connection.");
                e.printStackTrace();
            }
        }
    }
}
