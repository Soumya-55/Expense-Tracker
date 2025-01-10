package com.user.dao;

import com.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOTest {
    private DBConnection dbConnection;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        dbConnection = new DBConnection();
        userDAO = new UserDAO(dbConnection);
    }

    @Test
    public void testAddUser() {
        User newUser = new User("testuser", "password123", "testuser@example.com");
        boolean isAdded = userDAO.addUser(newUser);
        assertTrue(isAdded, "User should be added successfully.");
    }

    @Test
    public void testGetUserByUsername() {
        User user = userDAO.getUserByUsername("testuser");
        assertNotNull(user, "User should be found.");
        assertEquals("testuser", user.getUsername(), "Username should match.");
    }

    @Test
    public void testUserExists() {
        boolean exists = userDAO.userExists("testuser");
        assertTrue(exists, "User should exist in the database.");
    }

    @Test
    public void testUpdateUser() {
        User existingUser = userDAO.getUserByUsername("testuser");
        existingUser.setEmail("newemail@example.com");
        boolean isUpdated = userDAO.updateUser(existingUser);
        assertTrue(isUpdated, "User should be updated successfully.");
    }

    @Test
    public void testDeleteUser() {
        boolean isDeleted = userDAO.deleteUser("testuser");
        assertTrue(isDeleted, "User should be deleted successfully.");
    }

    // Optionally, you can test your DBConnection separately if needed
    @Test
    public void testDatabaseConnection() throws SQLException {
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection, "Database connection should not be null.");
    }
}
