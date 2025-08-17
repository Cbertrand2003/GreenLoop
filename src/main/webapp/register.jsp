<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Create Account</h2>
  <c:if test="${not empty requestScope.error}"><div class="alert error">${error}</div></c:if>
  <form method="post" action="auth">
    <input type="hidden" name="action" value="register"/>
    <label>Name <input name="name" required/></label>
    <label>Email <input name="email" type="email" required/></label>
    <label>Password <input name="password" type="password" minlength="6" required/></label>
    <label>Role
      <select name="role">
        <option value="CUSTOMER">Customer</option>
        <option value="VENDOR">Vendor</option>
      </select>
    </label>
    <button type="submit">Register</button>
  </form>
</main>
<%@ include file="fragments/footer.jspf" %>
