<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Reset CSS */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto 0; /* Added margin top */
            padding: 20px;
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            grid-gap: 20px;
        }

        .card {
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.3s ease;
            cursor: pointer;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: 1px solid #eee;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .card-content {
            padding: 20px;
        }

        .icon {
            font-size: 3em;
            color: #555;
        }

        h4 {
            font-size: 1.5em;
            margin-bottom: 10px;
            color: #333;
        }

        p {
            color: #777;
        }

        /* Remove default link styling */
        a {
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp"/>

<div class="container">
    <div class="col">
        <a href="/Sweet-Bite/admin/add_restaurant.jsp" class="card">
            <div class="card-content">
                <i class="icon fas fa-plus text-primary"></i>
                <h4>Add Restaurant</h4>
                <p>Add new restaurants to the platform</p>
            </div>
        </a>
    </div>

    <div class="col">
        <a href="/Sweet-Bite/admin/remove_restaurant.jsp" class="card">
            <div class="card-content">
                <i class="icon fas fa-minus text-danger"></i>
                <h4>Remove Restaurant</h4>
                <p>Remove restaurants from the platform</p>
            </div>
        </a>
    </div>

    <div class="col">
        <a href="/Sweet-Bite/admin/Order_Details.jsp" class="card">
            <div class="card-content">
                <i class="icon fas fa-list text-warning"></i>
                <h4>View Orders</h4>
                <p>View all incoming orders</p>
            </div>
        </a>
    </div>

    <div class="col">
        <a href="/Sweet-Bite/admin/DeliveryAgent.jsp" class="card">
            <div class="card-content">
                <i class="icon fas fa-tasks text-success"></i>
                <h4>Add Delivery Agent</h4>
                <p>Add a new delivery agent to the platform</p>
            </div>
        </a>
    </div>
</div>
</body>
</html>
