<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.user.model.user" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css">
    <!-- Add Bootstrap for better styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container mt-5">
        <h1>Welcome, <%= session.getAttribute("loggedUser") != null ? ((user) session.getAttribute("loggedUser")).getUsername() : "Guest" %>!</h1>

        <!-- If no user is found in session, redirect to login -->
        <% if (session.getAttribute("loggedUser") == null) { %>
            <p>You are not logged in. Please <a href="login.html">login</a> to continue.</p>
        <% } else { %>
            <p>Your dashboard is ready! You can access your expenses and settings here.</p>
            <!-- Add more user-specific dashboard content here -->
            <a href="logout.jsp" class="btn btn-danger">Logout</a>
        <% } %>
    </div>

    <!-- Bootstrap JS for functionality -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
