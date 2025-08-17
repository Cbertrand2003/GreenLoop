<%@ page import="com.greenloop.model.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Your Cart</h2>
  <c:set var="cart" value="${sessionScope.cart}"/>
  <c:if test="${cart==null || cart.items.empty}">
    <p>Your cart is empty.</p>
  </c:if>
  <c:if test="${cart!=null && not cart.items.empty}">
    <table>
      <tr><th>Product</th><th>Qty</th><th>Price</th><th>Subtotal</th><th></th></tr>
      <c:forEach var="ci" items="${cart.items}">
        <tr>
          <td>${ci.product.name}</td>
          <td>
            <form action="cart" method="post" style="display:inline">
              <input type="hidden" name="action" value="update"/>
              <input type="hidden" name="productId" value="${ci.product.id}"/>
              <input type="number" name="qty" value="${ci.quantity}" min="0"/>
              <button type="submit">Update</button>
            </form>
          </td>
          <td>$${ci.product.price}</td>
          <td>$${ci.subtotal}</td>
          <td>
            <form action="cart" method="post" style="display:inline">
              <input type="hidden" name="action" value="remove"/>
              <input type="hidden" name="productId" value="${ci.product.id}"/>
              <button type="submit">Remove</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
    <p><b>Total:</b> $${cart.total()}</p>
    <a class="btn" href="checkout">Proceed to Checkout</a>
    <form action="cart" method="post" style="display:inline">
      <input type="hidden" name="action" value="clear"/>
      <button type="submit">Clear Cart</button>
    </form>
  </c:if>
</main>
<%@ include file="fragments/footer.jspf" %>
