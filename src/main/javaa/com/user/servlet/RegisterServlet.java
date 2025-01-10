package com.user.servlet;

import com.user.dao.userDao;
import com.user.model.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private userDao userDao;

    @Override
    public void init() {
        userDao = new userDao(); // Initialize userDao
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate password match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("user_registration.html").forward(request, response);
            return;
        }

        // Validate password strength
        if (password.length() < 6) {
            request.setAttribute("errorMessage", "Password must be at least 6 characters.");
            request.getRequestDispatcher("user_registration.html").forward(request, response);
            return;
        }

        // Check if user already exists
        user existingUser = userDao.getUserByUsername(username);
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Username already taken. Please choose another.");
            request.getRequestDispatcher("user_registration.html").forward(request, response);
            return;
        }

        // Register the new user
        user newUser = new user(username, password, email);
        boolean isAdded = userDao.addUser(newUser);

        if (isAdded) {
            // Redirect to login page after successful registration
            response.sendRedirect("login.html");
        } else {
            // Error during registration
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("user_registration.html").forward(request, response);
        }
    }
}
