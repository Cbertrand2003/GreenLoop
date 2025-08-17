<%@ page import="java.util.*,com.greenloop.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Products</h2>
  <c:if test="${empty requestScope.products}">
    <p>No products yet.</p>
  </c:if>
  <div class="grid">
    <c:forEach var="p" items="${products}">
      <div class="card">
        <h3>${p.name}</h3>
        <p>${p.description}</p>
        <p><b>$${p.price}</b> · <i>${p.sustainabilityCert}</i></p>
        <form method="post" action="cart">
          <input type="hidden" name="action" value="add"/>
          <input type="hidden" name="productId" value="${p.id}"/>
          <input type="number" name="qty" value="1" min="1"/>
          <button type="submit">Add to cart</button>
        </form>
      </div>
    </c:forEach>
  </div>

  <h3>Write a Review</h3>
  <form method="post" action="reviews">
    <label>Product ID <input name="productId" type="number" required/></label>
    <label>Rating (1-5) <input name="rating" type="number" min="1" max="5" required/></label>
    <label>Comment <input name="comment" required/></label>
    <button type="submit">Submit</button>
  </form>
</main>
<%@ include file="fragments/footer.jspf" %>
