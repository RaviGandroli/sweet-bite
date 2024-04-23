<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <style>
        /* CSS for the settings page */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #fff;
        }
        h2 {
            margin: 0 0 15px;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        select, input[type="checkbox"] {
            margin-bottom: 10px;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            width: 100%;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error-msg, .success-msg {
            text-align: center;
            margin-top: 10px;
        }
        .error-msg {
            color: red;
        }
        .success-msg {
            color: green;
        }
        
        /* CSS for the navbar */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #343a40;
            color: #ffffff;
            position: fixed;
            top: 0;
            width: 100%;
            height: 70px;
            padding-left: 10%;
            padding-right: 10%;
            box-sizing: border-box;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .navbar-left a {
            color: #ffffff;
            text-decoration: none;
            padding: 10px 15px;
            display: inline-block;
            font-size: 20px;
            margin-left: 10px;
            vertical-align: middle;
        }
        .navbar-right a {
            color: #ffffff;
            text-decoration: none;
            padding: 10px 15px;
            display: inline-block;
        }
        .navbar-right a:hover {
            color: orange;
        }
    </style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>

<div class="container">
    <h2>Settings</h2>
    <c:if test="${not empty failmsg}">
        <h5 class="error-msg">${failmsg}</h5>
        <c:remove var="failmsg" scope="session" />
    </c:if>
    <c:if test="${not empty succmsg}">
        <h5 class="success-msg">${succmsg}</h5>
        <c:remove var="succmsg" scope="session" />
    </c:if>
    <form action="updateSettings" method="post">
        <h2>Language and Theme</h2>
        <div class="setting-group">
            <label for="language">Language:</label>
            <select name="language" id="language">
                <option value="en">English</option>
                <option value="fr">French</option>
                <option value="es">Spanish</option>
            </select>
        </div>
        <div class="setting-group">
            <label for="theme">Theme:</label>
            <select name="theme" id="theme">
                <option value="light">Light</option>
                <option value="dark">Dark</option>
            </select>
        </div>
        <h2>Privacy and Security</h2>
        <div class="setting-group">
            <label for="privacy">Privacy Settings:</label>
            <select id="privacy" name="privacy">
                <option value="public">Public</option>
                <option value="private">Private</option>
            </select>
        </div>
        <h2>Notification Settings</h2>
        <div class="setting-group">
            <label for="emailNotification">Email Notifications:</label>
            <select id="emailNotification" name="emailNotification">
                <option value="on">On</option>
                <option value="off">Off</option>
            </select>
        </div>
        <button type="submit">Save Settings</button>
    </form>
</div>

</body>
</html>
