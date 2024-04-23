<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweet-Bite Register</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 20px;
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
    }

    .extra {
        text-align: center;
        margin-bottom: 20px;
    }

    form {
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    input[type="text"],
    input[type="password"],
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    .error-message {
        color: red;
        margin-top: 10px;
        text-align: center;
    }
</style>
</head>
<body>
<h2>Sweet-Bite Register</h2>
<div class="extra">
    <p>New User <a href="Register.jsp">register here</a></p>
</div>
<div class="form-container">
    <form action="register" method="post">
        Name : <input type="text" name="fullname" placeholder="Full Name" required><br>
        Username : <input type="text" name="username" placeholder="Username" required><br>
        Email : <input type="text" name="email" placeholder="Email" required><br>
        Password : <input type="password" name="password" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$" placeholder="Password" required><br>
        <input type="submit" value="Register">
    </form>
</div>
<div class="error-message">
    <%-- Display error message here if needed --%>
</div>
</body>
</html>
