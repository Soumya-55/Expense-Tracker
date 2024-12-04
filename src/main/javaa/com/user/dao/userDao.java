
package com.user.dao;

import com.user.model.user;
import com.user.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDao {

    // Add a new user to the database
    public boolean addUser(user user) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            return stmt.executeUpdate() > 0; // Return true if insert is successful
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle exception properly
        }
        return false; // Return false if there was an issue
    }

    // Get a user by their username
    public user getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new user(
                    rs.getInt("user_id"), // Ensure this matches the column name in DB
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle exception properly
        }
        return null; // Return null if no user is found
    }
}
