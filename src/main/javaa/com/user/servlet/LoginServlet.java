package com.user.servlet;

import com.user.dao.userDao;
import com.user.model.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private userDao userDao;

    @Override
    public void init() {
        userDao = new userDao(); // Initialize userDao
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Fetch user from DB
        user existingUser = userDao.getUserByUsername(username);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", existingUser); // Store the user object in session
            response.sendRedirect("dashboard.jsp"); // Redirect to dashboard page after successful login
        } else {
            // Login failed
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response); // Redirect back to login page (now login.jsp) with error
        }
    }
}
