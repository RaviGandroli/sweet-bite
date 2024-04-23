<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navigation Bar</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Style the navbar */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            
        }
        .navbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #333;
            height: 80px;
            padding: 0 20px; /* Add padding to the sides */
        }

        /* Navbar links */
        .navbar a {
            display: block;
            color: white;
            text-align: center;
            text-decoration: none;
            padding: 14px 20px;
        }

        /* On hover, the links will change color */
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Add padding to logout button */
        .navbar a.logout {
            float: right;
        }
    </style>
</head>
<body>
<%
try {
%>
<div class="navbar">
    <a href="/Sweet-Bite/admin/AdminHome.jsp"><i class="fas fa-home"></i> Home</a>
    <a href="/Sweet-Bite/admin/DeliveryAgent.jsp"><i class="fas fa-user-plus"></i> Add Delivery Agent</a>
    <a href="/Sweet-Bite/admin/add_restaurant.jsp"><i class="fas fa-utensils"></i> Add Restaurant</a>
    <a href="/Sweet-Bite/admin/remove_restaurant.jsp"><i class="fas fa-trash-alt"></i> Remove Restaurant</a>
    <a href="/Sweet-Bite/admin/Order_Details.jsp"><i class="fas fa-list"></i> View Orders</a>
    <a href="/Sweet-Bite/logoutservlet" class="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>
<%
} catch (Exception e) {
    // Handle exception gracefully
    e.printStackTrace();
}
%>
</body>
</html>
