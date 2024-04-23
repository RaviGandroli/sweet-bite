<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navigation Bar</title>
    <!-- Add Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* navbar.css */
        /* Style the navbar */
        .navbar {
            overflow: hidden;
            background-color: #333;
        }

        /* Navbar links */
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
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
    <a href="/Sweet-Bite/RestaurantOwner/restaurantHome.jsp"><i class="fas fa-home"></i> Home</a>
    <a href="/Sweet-Bite/RestaurantOwner/add_menu_res.jsp"><i class="fas fa-plus"></i> Add Menu</a>
    <a href="/Sweet-Bite/RestaurantOwner/remove_menu.jsp"><i class="fas fa-minus"></i> Remove Menu</a>
    <a href="/Sweet-Bite/RestaurantOwner/EditProfileRes.jsp"><i class="fas fa-edit"></i> Edit profile</a>
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
