<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delivery Agent Registration</title>
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
    margin: 50px auto; /* Centering the container vertically */
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.container h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #007bff; /* Blue heading */
}
label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}
input[type="text"], input[type="email"], input[type="password"], select {
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
    display: block;
    margin: 20px auto 0; /* Centering the button */
}
input[type="submit"]:hover {
    background-color: #0056b3;
}
.success-msg, .error-msg {
    text-align: center;
    margin-top: 20px;
    padding: 10px;
    border-radius: 5px;
}
.success-msg {
    background-color: #d4edda; /* Green background for success */
    color: #155724; /* Dark green text */
}
.error-msg {
    background-color: #f8d7da; /* Red background for failure */
    color: #721c24; /* Dark red text */
}
</style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp"/>

<div class="container">
    <h2>Delivery Agent Registration</h2>
   <% if (session.getAttribute("failmsg") != null) { %>
       <h5 class="error-msg"><%= session.getAttribute("failmsg") %></h5>
       <% session.removeAttribute("failmsg"); %>
   <% } else if (session.getAttribute("succmsg") != null) { %>
       <h5 class="success-msg"><%= session.getAttribute("succmsg") %></h5>
       <% session.removeAttribute("succmsg"); %>
   <% } %>
    <form action="../deliveryAgent" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>

        <label for="phoneNo">Phone Number:</label><br>
        <input type="text" id="phoneNo" name="phoneNo" required><br>

        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address" required><br>

        <label for="userName">Username:</label><br>
        <input type="text" id="userName" name="userName" required><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>

        <label for="role">Role:</label><br>
        <select id="role" name="role">
            <option value="deliveryBoy">Delivery Boy</option>
        </select><br>

        <input type="submit" value="add">
    </form>
</div>

</body>
</html>
