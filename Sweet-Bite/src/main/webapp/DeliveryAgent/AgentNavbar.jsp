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
        .navigation {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        /* Navbar links */
        .navigation li {
            float: left;
        }

        .navigation li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }

        /* On hover, the links will change color */
        .navigation li a:hover {
            background-color: #ddd;
            color: black;
        }
    </style>
</head>
<body>
<ul class="navigation">
    <li><a href="/Sweet-Bite/DeliveryAgent/DeliveryAgentHome.jsp"><i class="fas fa-home"></i> Home</a></li>
    <li><a href="/Sweet-Bite/DeliveryAgent/EditProfileAgent.jsp"><i class="fas fa-user-edit"></i> Edit Profile</a></li>
    <li><a href="/Sweet-Bite/updateStatus?action=history"><i class="fas fa-history"></i> Delivery History</a></li>
    <li><a href="/Sweet-Bite/logoutservlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
</ul>
</body>
</html>
