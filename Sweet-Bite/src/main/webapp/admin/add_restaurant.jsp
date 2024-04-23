<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Restaurant</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            position: relative;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="file"] {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 10px;
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
        .success-msg, .error-msg {
            position: absolute;
            top: 10px;
            left: 50%;
            transform: translateX(-50%);
            text-align: center;
            width: 100%;
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
    <h1>Add Restaurant</h1>
    
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
        <label for="restaurantName">Restaurant Name:</label>
        <input type="text" id="restaurantName" name="restaurantName" required>

        <label for="image">Restaurant Image:</label>
        <input type="file" id="image" name="image" accept="image/*" required>

        <label for="cuisineType">Cuisine Type:</label>
        <input type="text" id="cuisineType" name="cuisineType" required>

        <label for="address">Address:</label>
        <textarea id="address" name="address" rows="4" required></textarea>

        <label for="username">Restaurant Owner UserName:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Restaurant Owner Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" name="action" value="AddRestaurant">
    </form>
</div>

</body>
</html>
