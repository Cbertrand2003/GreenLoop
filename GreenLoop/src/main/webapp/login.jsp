<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - GreenLoop</title>
</head>
<body>
    <h2>Login to GreenLoop</h2>

    <% String error = request.getParameter("error"); %>
    <% if (error != null) { %>
        <p style="color: red;">
            <% if ("invalid".equals(error)) { %>
                Invalid username or password.
            <% } %>
        </p>
    <% } %>

    <form method="post" action="login">
        <label>Username:</label><br/>
        <input type="text" name="username" required/><br/><br/>

        <label>Password:</label><br/>
        <input type="password" name="password" required/><br/><br/>

        <input type="submit" value="Login"/>
    </form>
</body>
</html>
