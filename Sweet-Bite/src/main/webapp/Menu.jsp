<!DOCTYPE html>
<%@page import="com.tap.model.Menu"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }

        .restaurant-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .menu {
            width: calc(30% - 40px);
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            overflow: hidden;
            transition: transform 0.3s ease;
        }

        .menu:hover {
            transform: translateY(-5px);
        }

        .menu-details {
            padding: 20px;
            flex: 1;
        }

        .menu h2 {
            margin: 0;
            font-size: 20px;
            color: #333;
        }

        .menu p {
            margin: 5px 0;
            color: #666;
        }

        .menu p.label {
            color: #333;
            font-weight: bold;
        }

        .menu button {
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .menu button:hover {
            background-color: #0056b3;
        }

        .menu-img {
            flex-shrink: 0;
            position: relative;
            overflow: hidden;
        }

        .menu img {
            width: 100%;
            height: auto;
            transition: transform 0.3s ease;
        }

        .menu-img:hover img {
            transform: scale(1.1);
        }

        .menu-img:before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.3);
            opacity: 0;
            transition: opacity 0.3s ease;
        }

        .menu-img:hover:before {
            opacity: 1;
        }

        .menu-img form {
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
            opacity: 0;
            transition: opacity 0.3s ease;
        }

        .menu:hover .menu-img form {
            opacity: 1;
        }

        .menu-img input[type="text"] {
            width: 50px;
            padding: 5px;
            text-align: center;
        }

        /* Additional styling for better visual appeal */

        /* Add gradient background for menu cards */
        .menu {
            background: linear-gradient(to bottom, #ffffff 0%, #f2f2f2 100%);
        }

        /* Add box shadow on hover */
        .menu:hover {
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        /* Add hover effect on the Add to Cart button */
        .menu button:hover {
            background-color: #0056b3;
            transform: translateY(-1px);
        }
    </style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>
<div class="restaurant-container">
    <% 
    try {
        List<Menu> menus = (List<Menu>) session.getAttribute("menus"); 
        if (menus != null) {
            for (Menu menu : menus) {
    %>
    <div class="menu">
        <div class="menu-details">
            <h2><%= menu.getMenuName() %></h2>
            <p class="label">Rating:</p>
            <p><%= menu.getRatings() %></p>
            <p class="label">Available:</p>
            <p><%= menu.isAvailable() ? "Yes" : "No" %></p>
            <p class="label">Price:</p>
            <p><%= menu.getPrice() %></p>
            <p class="label">Description:</p>
            <p><%= menu.getDescription() %></p>
        </div>
        <div class="menu-img">
            <img src="./images/<%= menu.getImagepath() %>" alt="<%= menu.getMenuName() %>">
            <form action="CartServlet">
                <input type="hidden" value="add" name="action">
                <input type="hidden" value="<%=menu.getMenuId()%>" name="menuId">
                <input type="text" name="quantity" value="1">
                <input type="submit" value="Add to Cart">
            </form>
        </div>
    </div>
    <% 
            }
        } else {
            out.print("No menu is available");
        }
    } catch (Exception e) {
        out.print("An error occurred while loading the menus.");
    }
    %>
</div>
</body>
</html>
