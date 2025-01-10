package com.user.servlet;

import com.user.dao.UserDAO;
import com.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class LoginServletTest {
    private LoginServlet loginServlet;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        loginServlet = new LoginServlet();
        userDAO = mock(UserDAO.class);  // Mocking UserDAO
        loginServlet.setUserDAO(userDAO);  // Injecting the mock DAO into the servlet
    }

    @Test
    public void testDoPostLoginSuccess() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        // Simulate the request parameters
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("password123");

        // Mock UserDAO behavior
        User mockUser = new User("testuser", "password123", "testuser@example.com");
        when(userDAO.getUserByUsername("testuser")).thenReturn(mockUser);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("dashboard.jsp");  // Assuming redirect on success
    }

    @Test
    public void testDoPostLoginFailure() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simulate the request parameters
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("wrongpassword");

        // Simulate no user found
        when(userDAO.getUserByUsername("testuser")).thenReturn(null);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("login.html");  // Assuming redirect on failure
    }
}
