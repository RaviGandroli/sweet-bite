<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sweet-Bite Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 300px;
            text-align: center; /* Center the contents */
        }
        .login-container h2 {
            margin-top: 0;
            margin-bottom: 20px;
            color: #fc8019;
        }
        .login-container a{
            color:maroon;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .extra {
            text-align: center;
            margin-bottom: 20px;
        }
        .extra a {
            color: #007bff;
            text-decoration: none;
        }
        .extra a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
        .login-container input[type="submit"] {
            width: 100%;
            background-color: #fc8019;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .login-container input[type="submit"]:hover {
            background-color: #e27000;
        }
        /* Style for the red mark */
        .invalid-feedback {
            color: red;
            font-size: 12px;
            margin-top: -10px;
            margin-bottom: 5px;
            text-align: left;
            display: none; /* Initially hide the feedback */
        }
        /* Style for success and fail messages */
        .success-msg, .fail-msg {
            color: green; /* Success message color */
            font-weight: bold;
            margin-bottom: 10px;
        }
        .fail-msg {
            color: red; /* Fail message color */
        }
    </style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>
<div class="login-container">
    <c:if test="${not empty failmsg}">
        <h5 class="fail-msg">${failmsg}</h5>
        <c:remove var="failmsg" scope="session" />
    </c:if>
    <c:if test="${not empty succmsg}">
        <h5 class="success-msg">${succmsg}</h5>
        <c:remove var="succmsg" scope="session" />
    </c:if>
    <h2>Sweet-Bite Login</h2>
    <div class="extra">
        <p>New User <a href="Register.jsp">register here</a></p>
    </div>
    <form action="login?action=login" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <div class="invalid-feedback" id="usernameFeedback"></div> <!-- Red mark for username -->
        <input type="password" name="password" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$" placeholder="Password" required>
        <div class="invalid-feedback" id="passwordFeedback"></div> <!-- Red mark for password -->
        <input type="submit" value="Login">
    </form>
</div>

<script>
    // Function to display or hide the red mark based on input validity
    function toggleInvalidFeedback(input, feedbackId) {
        var feedback = document.getElementById(feedbackId);
        if (!input.validity.valid) {
            feedback.textContent = "Invalid input.";
            feedback.style.display = "block";
        } else {
            feedback.textContent = "";
            feedback.style.display = "none";
        }
    }

    // Event listener for username input
    var usernameInput = document.querySelector('input[name="username"]');                                   
    usernameInput.addEventListener('input', function() {
        toggleInvalidFeedback(this, 'usernameFeedback');
    });

    // Event listener for password input
    var passwordInput = document.querySelector('input[name="password"]');
    passwordInput.addEventListener('input', function() {
        toggleInvalidFeedback(this, 'passwordFeedback');
    });
</script>

</body>
</html>
