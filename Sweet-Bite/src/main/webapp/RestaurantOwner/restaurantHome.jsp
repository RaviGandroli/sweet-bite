<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Home Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-+FU6HQd5kxx84mZ9kQNmIDp4ZhBz80FDvr3gN5VvTizxu3Fh/DP3Z6ZRY7g7NpfD2r9Hg4IGEExqFjIl3zueEg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        /* General styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        /* Navbar styles */
        .navbar {
            width: 100%;
            background-color: #333;
            overflow: hidden;
        }

        /* Navbar links */
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }

        /* On hover, the links will change color */
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Add padding to logout button */
        .navbar a.logout {
            float: right;
        }

        /* Container */
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        /* Card styles */
        .card {
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            cursor: pointer;
            border: none;
            outline: none;
            padding: 20px;
            text-align: center;
            margin: 0 10px;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        /* Icon */
        .icon {
            font-size: 3em;
            color: #333;
        }

        /* Headings */
        h4 {
            font-size: 1.5em;
            margin-bottom: 10px;
            color: #333;
        }

        /* Paragraph */
        p {
            color: #666;
        }

    </style>
</head>
<body>
<jsp:include page="ResNavbar.jsp" />

<div class="container">
    <form action="/Sweet-Bite/RestaurantOwner/add_menu_res.jsp" method="post" class="card">
        <input type="hidden" name="action" value="Add Menu">
        <button type="submit" class="card-content">
            <i class="icon fas fa-plus fa-3x text-success"></i>
            <h4>Add Menu</h4>
        </button>
    </form>
    
    <form action="/Sweet-Bite/RestaurantOwner/remove_menu.jsp" method="post" class="card">
        <input type="hidden" name="action" value="Remove Menu">
        <button type="submit" class="card-content">
            <i class="icon fas fa-minus fa-3x text-danger"></i>
            <h4>Remove Menu</h4>
        </button>
    </form>

    <form action="../addMenuServletbyres" method="post" class="card">
        <input type="hidden" name="action" value="Remove Restaurant">
        <button type="submit" class="card-content">
            <i class="icon fas fa-trash-alt fa-3x text-danger"></i>
            <h4>Remove Restaurant</h4>
        </button>
    </form>
</div>
</body>
</html>
