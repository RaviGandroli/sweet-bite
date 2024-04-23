<%@page import="com.tap.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Tracking</title>
<style>
    /* CSS for styling the page */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    .container {
        width: 50%;
        margin: 50px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
        color: #333;
    }
    .user-info {
        margin-bottom: 20px;
        text-align: center;
    }
    .user-info p {
        font-size: 18px;
        color: #555;
        margin-bottom: 10px;
    }
    .order-status {
        text-align: center;
    }
    .order-status button {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    .order-status button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>

    <div class="container">
        <h2>Order Tracking</h2>
        <div class="user-info">
            <% 
                // Retrieve user's name from session and handle exceptions
                try {
                    User user = (User) session.getAttribute("userobj");
                    if (user != null && user.getUserName() != null) {
            %>
            <p>Welcome, <span style="font-weight: bold;"><%= user.getUserName()%></span>! Your order has been received.</p>
            <p>We are preparing your order. It will be on its way soon!</p>
            <a href="ordersHistoryServlet?action=viewOrders"><button>View Order History</button></a>
            <% 
                    }
                } catch (Exception e) {
                    // Handle exceptions gracefully
                    out.println("<p>Error retrieving user information</p>");
                }
            %>
        </div>
        
    </div>
</body>
</html>
