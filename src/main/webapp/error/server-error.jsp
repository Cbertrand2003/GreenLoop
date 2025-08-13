<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>500 - Server Error</title>
</head>
<body>
    <h1>500 - Server Error</h1>
    <p>Something went wrong on our end. Please try again later.</p>
    <a href="<%= request.getContextPath() %>/login.jsp">Back to Login</a>
</body>
</html>
