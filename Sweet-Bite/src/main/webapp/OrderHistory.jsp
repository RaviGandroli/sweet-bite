<%@ page import="com.tap.model.User" %>
<%@ page import="com.tap.daoImpl.UserDaoImpl" %>
<%@ page import="com.tap.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.daoImpl.OrderItemDaoImpl" %>
<%@ page import="com.tap.model.OrderItem" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>History Of Your Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        .inner-table {
            width: 100%;
            border-collapse: collapse;
        }

        .inner-th, .inner-td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        .inner-th {
            background-color: #f2f2f2;
        }

        .no-orders {
            text-align: center;
            margin-top: 20px;
            font-style: italic;
        }
    </style>
</head>
<body>
<%@ include file="UserNavbar.jsp" %>
<h2>Dispatched Order Details</h2>
<%
try {
    List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("viewOrderItem");
    List<Order> orders = (List<Order>) session.getAttribute("viewOrders");

    if (orders != null && orderItems != null && !orders.isEmpty()) {
%>
<table>
    <thead>
        <tr>
            <th>Order ID</th>
            <th>Total Amount</th>
            <th>Order Status</th>
            <th>Items</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (Order order : orders) {
                if (order.getStatus().equals("delivered")) {
        %>
        <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getTotalAmout() %></td>
            <td><%= order.getStatus() %></td>
            <td>
                <table class="inner-table">
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
                                if (order.getUserId()==orderItem.getUserId()&&order.getOrderId().equals(orderItem.getOrderId() )) {
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
        %>
    </tbody>
</table>
<%
    } else {
%>
<div class="no-orders">
    No  orders found.
</div>
<%
    }
} catch (Exception e) {
    // Handle any exceptions here
    e.printStackTrace();
%>
<div class="no-orders">
    An error occurred while retrieving  orders.
</div>
<%
}
%>
</body>
</html>
