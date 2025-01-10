package com.user.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("testuser", "password123", "testuser@example.com");
        assertEquals("testuser", user.getUsername(), "Username should be set correctly.");
        assertEquals("password123", user.getPassword(), "Password should be set correctly.");
        assertEquals("testuser@example.com", user.getEmail(), "Email should be set correctly.");
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setEmail("newuser@example.com");

        assertEquals("newuser", user.getUsername(), "Username should be updated correctly.");
        assertEquals("newpassword", user.getPassword(), "Password should be updated correctly.");
        assertEquals("newuser@example.com", user.getEmail(), "Email should be updated correctly.");
    }

    @Test
    public void testToString() {
        User user = new User("testuser", "password123", "testuser@example.com");
        String expectedString = "User{userId=0, username='testuser', password='password123', email='testuser@example.com'}";
        assertEquals(expectedString, user.toString(), "toString method should work correctly.");
    }
}
