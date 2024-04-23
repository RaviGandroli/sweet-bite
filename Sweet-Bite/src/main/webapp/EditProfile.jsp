<%@page import="com.tap.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <style>
        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        
        /* Container Styles */
        .container {
            width: 400px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        /* Form Group Styles */
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            font-weight: bold;
        }
        
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        
        /* Submit Button Styles */
        .submit button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        
        .submit button:hover {
            background-color: #0056b3;
        }
        
        /* Success and Failure Message Styles */
        .success-msg {
            color: green;
            text-align: center;
        }
        
        .error-msg {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>
<div class="container">
    <%  us = (User)session.getAttribute("userobj"); %>
    <h4 class="text-center text-primary">Edit Profile</h4>
    <c:if test="${not empty failmsg}">
        <h5 class="error-msg">${failmsg}</h5>
        <c:remove var="failmsg" scope="session" />
    </c:if>
    <c:if test="${not empty succmsg}">
        <h5 class="success-msg">${succmsg}</h5>
        <c:remove var="succmsg" scope="session" />
    </c:if>
    <form id="updateForm" action="UserUpdate" method="post">
        <input type="hidden" value="<%= us.getUserId() %>" name="id">
        <input type="hidden" value="editprofile" name="name">
        <div class="form-group">
            <label for="username">Name</label>
            <input type="text" name="username" id="username" value="<%= us.getName() %>" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" id="email" value="<%= us.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" value="<%= us.getPassword() %>" required>
        </div>
        <div class="form-group">
            <label for="phoneno">Phone no</label>
            <input type="number" name="phoneno" id="phoneno" value="<%= us.getPhoneNo() %>" required>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" name="address" id="address" value="<%= us.getAddress() %>" required>
        </div>
        <div class="submit">
            <button type="submit">Update</button>
        </div>
    </form>
</div>
</body>
</html>
