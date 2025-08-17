<%@ page import="com.greenloop.model.Product,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Vendor Dashboard</h2>
  <h3>Create / Update Product</h3>
  <form method="post" action="products">
    <input type="hidden" name="action" value="create"/>
    <label>Name <input name="name" required/></label>
    <label>Description <input name="description" required/></label>
    <label>Price <input name="price" type="number" step="0.01" required/></label>
    <label>Stock <input name="stock" type="number" min="0" required/></label>
    <label>Certification <input name="cert"/></label>
    <button type="submit">Create</button>
  </form>

  <h3>Your Products</h3>
  <c:forEach var="p" items="${vendorProducts}">
    <div class="card">
      <b>${p.name}</b> ($${p.price}) · Stock: ${p.stock} · ${p.sustainabilityCert}
      <form method="post" action="products" style="display:inline">
        <input type="hidden" name="action" value="delete"/>
        <input type="hidden" name="id" value="${p.id}"/>
        <button type="submit">Delete</button>
      </form>
    </div>
  </c:forEach>
</main>
<%@ include file="fragments/footer.jspf" %>
