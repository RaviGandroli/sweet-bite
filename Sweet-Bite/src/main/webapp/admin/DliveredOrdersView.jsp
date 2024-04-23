<%@page import="com.tap.daoImpl.UserDaoImpl"%>
<%@page import="com.tap.model.User"%>
<%@page import="com.tap.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.tap.daoImpl.OrderItemDaoImpl"%>
<%@page import="com.tap.model.OrderItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<style>
    /* Your CSS styles */
    table {
        border-collapse: collapse;
        width: 100%;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp" />

<h2>Delivered Order Details</h2>

<table>
  <thead>
    <tr>
      <th>Order ID</th>
      <th>Customer Name</th>
      <th>Total Amount</th>
      <th>Order Date</th>
      <th>Items</th>
    </tr>
  </thead>
  <tbody>
    <% 
        try {
            List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("viewOrderItem");
            List<Order> orders = (List<Order>) session.getAttribute("viewOrders");
            UserDaoImpl udi = new UserDaoImpl();
            for (Order order : orders) {
                if (order.getStatus().equals("delivered")) {
                    User user = udi.getUser(order.getUserId());
                    if (user != null) {
    %>
    <tr>
      <td><%= order.getOrderId() %></td>
      <td><%= user.getName() %></td>
      <td><%= order.getTotalAmout() %></td>
     
      <td>
        <table>
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Quantity</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <%
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getUserId() == order.getUserId() && order.getStatus().equals("delivered")&&order.getOrderId().equals(orderItem.getOrderId() )) {
            %>
            <tr>
              <td><%= orderItem.getItemName() %></td>
              <td><%= orderItem.getQuantity() %></td>
              <td><%= orderItem.getPrice() %></td>
            </tr>
            <%
                    }
                }
            %>
          </tbody>
        </table>
      </td>
    </tr>
    <%
                    }
                }
            }
        } catch (Exception e) {
            // Handle exception gracefully
            e.printStackTrace();
        }
    %>
  </tbody>
</table>

</body>
</html>
