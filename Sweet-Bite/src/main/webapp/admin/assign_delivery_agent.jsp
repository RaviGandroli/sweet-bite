<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delivery Agent Login</title>
    <style>
        /* Your CSS styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"], input[type="otp"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp"/>

<div class="container">
    <h2>Delivery Agent Login</h2>
    
    <form action="../assign_order" method="post">
        <input type="hidden" name="orderId" value="<%= request.getParameter("orderId") %>">
        <label for="agentName">Agent Name:</label><br>
        <input type="text" id="agentName" name="agentName" required><br>
        
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="otp">OTP:</label><br>
        <input type="otp" id="otp" name="otp" required><br><br>
        
        <input type="submit" value="assign">
    </form>
</div>

</body>
</html>
