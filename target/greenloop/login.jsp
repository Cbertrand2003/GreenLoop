<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Login</h2>
  <c:if test="${not empty param.registered}"><div class="alert success">Registration successful. Please log in.</div></c:if>
  <c:if test="${not empty requestScope.error}"><div class="alert error">${error}</div></c:if>
  <form method="post" action="auth">
    <input type="hidden" name="action" value="login"/>
    <label>Email <input name="email" type="email" required/></label>
    <label>Password <input name="password" type="password" required/></label>
    <button type="submit">Login</button>
  </form>
</main>
<%@ include file="fragments/footer.jspf" %>
