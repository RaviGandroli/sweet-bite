<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove Restaurant</title>
<style>
    /* Your CSS styles */
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        text-align: center;
    }
    .container {
        max-width: 500px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
    }
    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .success-msg {
        color: green;
    }
    .error-msg {
        color: red;
    }
</style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp" />

<div class="container">
    <h2>Remove Restaurant</h2>
    <%-- Display success message if it exists --%>
    <% if (session.getAttribute("succmsg") != null) { %>
        <p class="success-msg"><%= session.getAttribute("succmsg") %></p>
        <% session.removeAttribute("succmsg"); %>
    <% } %>
    
    <%-- Display error message if it exists --%>
    <% if (session.getAttribute("failmsg") != null) { %>
        <p class="error-msg"><%= session.getAttribute("failmsg") %></p>
        <% session.removeAttribute("failmsg"); %>
    <% } %>
    <form action="../addRestaurants" method="POST" enctype="multipart/form-data">
        <label for="username">Restaurant Owner UserName:</label><br>
        <input id="username" name="username" required></input><br>
        
        <label for="password">Restaurant Owner Password:</label><br>
        <input id="password" name="password" required></input><br>
      
        <input type="submit" name="action" value="RemoveRestaurant">
    </form>
</div>
</body>
</html>
