<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>403 - Access Denied</title>
</head>
<body>
    <h1>403 - Access Denied</h1>
    <p>You do not have permission to access this page.</p>
    <a href="<%= request.getContextPath() %>/login.jsp">Return to Login</a>
</body>
</html>
