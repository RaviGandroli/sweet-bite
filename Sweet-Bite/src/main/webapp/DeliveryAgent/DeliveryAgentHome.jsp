<!DOCTYPE html>
<%@page import="com.tap.daoImpl.UserDaoImpl"%>
<%@page import="com.tap.model.User"%>
<%@page import="com.tap.model.Restaurant"%>
<%@page import="com.tap.model.Order"%>
<%@page import="com.tap.daoImpl.OrderDaoImpl"%>
<%@page import="com.tap.daoImpl.RestaurantDaoImpl"%>
<%@page import="com.tap.model.DeliveryItems"%>
<%@page import="java.util.List"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delivery Agent Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        .container {
            max-width: 960px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #333;
            margin-top: 20px;
            margin-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            text-align: left;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        select, button[type="submit"] {
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        select {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        .navigation {
            list-style-type: none;
            padding: 0;
            margin: 0;
            background-color: #007bff;
            overflow: hidden;
        }

        .navigation li {
            float: left;
        }

        .navigation li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .navigation li a:hover {
            background-color: #0056b3;
        }

        .logout-btn {
            text-align: center;
            margin-top: 20px;
        }

        .logout-btn a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        .logout-btn a:hover {
            background-color: #0056b3;
        }

        /* Additional CSS for form */
        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .submit button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .submit button:hover {
            background-color: #0056b3;
        }

        .success-msg, .error-msg {
            text-align: center;
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
<jsp:include page="AgentNavbar.jsp"/>
<div class="container">
    <!-- Welcome message -->
    <% User us=(User)session.getAttribute("userobj"); %>
    <h1>Welcome, <%= us.getName() %>!</h1>

    <!-- Delivery Schedule table -->
    <h2>Delivery Schedule For You</h2>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Customer Name</th>
            <th>Address</th>
            <th>OTP</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <!-- Java code to retrieve delivery schedule -->
        <%
            try {
                List<DeliveryItems> deliveryItems = (List<DeliveryItems>) session.getAttribute("deliveryItems");
                System.out.println(deliveryItems);
                if (deliveryItems != null) {
                    RestaurantDaoImpl rdi = new RestaurantDaoImpl();
                    OrderDaoImpl odi = new OrderDaoImpl();
                    UserDaoImpl udi = new UserDaoImpl();
                    for (DeliveryItems delivery : deliveryItems) {
                        Order order = odi.getOrder(delivery.getOrderId());
                        if (order != null && !order.getStatus().equalsIgnoreCase("delivered")) {
                            Restaurant restaurant = rdi.getRestaurant(order.getRestaurantId());
                            User user = udi.getUser(order.getUserId());
                            System.out.println(user);
        %>
        <tr>
            <td><%= delivery.getOrderId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getAddress() %></td>
            <td><%= delivery.getOTP() %></td>
            <td><%= order.getStatus() %></td>
            <td>
                <form action="/Sweet-Bite/updateStatus" method="post">
                    <input type="hidden" name="action" value="any">
                    <input type="hidden" name="orderId" value="<%=order.getOrderId()%>">
                    <label class="enter-time">Enter estimated time:</label>
                    <input type="text" name="eta" value="<%=order.getETA()%>">
                    <select name="status" <%= order.getStatus().equals("Delivered") ? "disabled" : "" %>>
                        <option value="<%= order.getStatus() %>"><%= order.getStatus() %></option>
                        <option value="priparing">Preparing</option>
                        <option value="dispatched">Dispatched</option>
                        <option value="ontheWay">On the way</option>
                        <option value="delivered">Delivered</option>
                    </select>
                    <button type="submit" >Update Status</button>
                </form>
            </td>
        </tr>
        <%
                        }
                    }
                } else {
                    // Handle case when deliveryItems is null
                    out.println("<tr><td colspan=\"6\">No delivery schedule available</td></tr>");
                }
            } catch (Exception e) {
                // Handle exception gracefully
                out.println("<tr><td colspan=\"6\">Error occurred while retrieving delivery schedule</td></tr>");
                e.printStackTrace();
            }
        %>
    </table>
</div>
</body>
</html>
