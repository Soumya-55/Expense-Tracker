<%@ page session="true" %>
<%
    session.invalidate(); // Invalidate the session
    response.sendRedirect("login.html"); // Redirect to login page
%>
