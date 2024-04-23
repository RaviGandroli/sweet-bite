<%@page import="com.tap.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Account - Sweet-Bite</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #333;
        }

        /* Container Styles */
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* User Details Styles */
        .user-details {
            text-align: center;
            margin-bottom: 20px;
        }

        /* Edit Profile Button Styles */
        .edit-profile-button {
            text-align: center;
            margin-top: 20px;
        }

        .edit-profile-button a {
            text-decoration: none;
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .edit-profile-button a:hover {
            background-color: #0056b3;
        }

        /* Last List Styles */
        .last-list {
            text-align: center;
            margin-top: 20px;
        }

        .last-list ul {
            list-style: none;
            padding: 0;
        }

        .last-list ul li {
            margin-bottom: 10px;
        }

        .last-list ul li a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .last-list ul li a:hover {
            text-decoration: underline;
        }

        /* Content Styles */
        .content {
            padding: 20px;
            text-align: center;
        }

        /* Feedback Form Styles */
        .feedback {
            margin-top: 20px;
            text-align: center;
        }

        .feedback textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s ease;
        }

        .feedback textarea:focus {
            border-color: #007bff;
        }

        .feedback button {
            background-color: #fc8019;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .feedback button:hover {
            background-color: #e27000;
        }
    </style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>

<div class="container">
    <div class="user-details">
        <% 
         us = null;
        if (session.getAttribute("userobj") != null) {
            us = (User) session.getAttribute("userobj");
        }
        %>
        <h1>Welcome, <%= (us != null) ? us.getName() : "" %></h1>
        <p>Phone: <%= (us != null) ? us.getPhoneNo() : "" %></p>
        <p>Email: <%= (us != null) ? us.getEmail() : "" %></p>
    </div>
    <div class="edit-profile-button">
        <a href="EditProfile.jsp"><i class="fas fa-user-edit"></i>Edit Profile</a>
    </div>
</div>

<div class="container">
    <div class="last-list">
        <ul>
            <li><a href="ordersHistoryServlet?action=orderhistory">Order History</a></li>
            <li><a href="Setting.jsp">Settings</a></li>
        </ul>
    </div>
    <div class="content">
        <h2>Welcome To Sweet-Bite</h2>
        <p>Welcome to Sweet-Bite! You are heartily welcomed here. This is one of the best food delivery websites. If you have any queries, feel free to send them using the form below. Thank you for being with us.</p>
        <div class="feedback">
            <h2>Send Feedback</h2>
            <form action="SubmitFeedbackServlet" method="post">
                <label for="feedback">Your Message:</label>
                <textarea id="feedback" name="feedback" placeholder="Type your message here..." required></textarea>
                <button type="submit">Send</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
