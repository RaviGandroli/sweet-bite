<!DOCTYPE html>
<html>
<head>
    <title>Order Status</title>
    <style>
        /* Your CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            text-align: center;
        }
        
        .button {
            display: inline-block;
            padding: 20px 40px;
            font-size: 18px;
            text-align: center;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            margin: 20px;
        }
        
        .button:hover {
            background-color: #45a049;
        }
        
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="AdminNavbar.jsp" />

<div class="container">
    <h1>Order Status</h1>
    <div>
        <%
            try {
        %>
        <a href="../viewOrders?href=prepareOrder" class="button">Preparing Order</a>
        <a href="../viewOrders?href=dispatchedOrders" class="button">Dispatched Order</a>
        <a href="../viewOrders?href=onTheWayOrder" class="button">On-the-way Order</a>
        <a href="../viewOrders?href=deliveredOrder" class="button">Delivered Order</a>
        <%
            } catch (Exception e) {
                // Handle exception gracefully
                e.printStackTrace();
            }
        %>
    </div>
</div>
</body>
</html>
