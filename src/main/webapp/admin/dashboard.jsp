<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<html>
<head><title>Admin Dashboard</title></head>
<body>
<h1>Welcome, Admin <%= user.getUsername() %>!</h1>
<a href="<%= request.getContextPath() %>/logout">Logout</a>
</body>
</html>
