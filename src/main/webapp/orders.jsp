<%@ page import="com.greenloop.model.User,com.greenloop.model.Order,com.greenloop.service.OrderService,java.util.*" %>
<%@ include file="fragments/header.jspf" %>
<main class="container">
  <h2>Your Orders</h2>
  <%
    User u = (User) session.getAttribute("user");
    List<Order> orders = java.util.Collections.emptyList();
    if(u != null){
      try { orders = new com.greenloop.service.OrderService().forCustomer(u.getId()); } catch(Exception e){}
    }
  %>
  <c:if test="${param.success==1}">
    <div class="alert success">Order placed successfully! ID: ${param.orderId}</div>
  </c:if>
  <c:choose>
    <c:when test="<%= orders.isEmpty() %>">No orders yet.</c:when>
    <c:otherwise>
      <table>
        <tr><th>ID</th><th>Status</th><th>Total</th><th>Invoice</th></tr>
        <%
          for(Order o : orders){
        %>
        <tr>
          <td><%= o.getId() %></td>
          <td><%= o.getStatus() %></td>
          <td>$<%= o.getTotal() %></td>
          <td>
            <c:choose>
              <c:when test="${not empty o.invoicePath}">
                <a href="<%= request.getContextPath()+"/"+o.getInvoicePath() %>" target="_blank">Download</a>
              </c:when>
              <c:otherwise>—</c:otherwise>
            </c:choose>
          </td>
        </tr>
        <% } %>
      </table>
    </c:otherwise>
  </c:choose>
</main>
<%@ include file="fragments/footer.jspf" %>
