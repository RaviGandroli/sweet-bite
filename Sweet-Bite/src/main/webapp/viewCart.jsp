<%@ page import="com.tap.model.CartCreator" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map.Entry" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Items</title>
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #343a40;
            color: #ffffff;
            text-align: center;
            padding: 20px 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .menu {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #e0e0e0;
            padding: 20px 0;
        }
        .menu-details {
            flex: 1;
        }
        .menu-details h2 {
            margin: 0;
            font-size: 24px;
            color: #333333;
        }
        .menu-details p {
            margin: 5px 0;
            color: #666666;
        }
        .menu-details img {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
        }
        .menu-actions {
            display: flex;
            align-items: center;
        }
        .menu-actions form {
            margin-right: 10px;
        }
        .menu-actions input[type="text"] {
            width: 40px;
            padding: 5px;
            border: 1px solid #cccccc;
            border-radius: 3px;
        }
        .menu-actions button {
            padding: 8px 15px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .menu-actions button:hover {
            background-color: #0056b3;
        }
        .order {
            text-align: center;
            margin-top: 20px;
        }
        .order button {
            padding: 10px 20px;
            background-color: #28a745;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .order button:hover {
            background-color: #218838;
        }
        .no-items {
            text-align: center;
            color: #999999;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="UserNavbar.jsp" %>
    <div class="header">
        <h1>Your Items</h1>
    </div>

    <div class="container">
        <div class="restaurant-container">
            <% 
                CartCreator cartCreator = (CartCreator) session.getAttribute("cart");
                
                if (cartCreator != null && !cartCreator.getAllItems().isEmpty()) {
                    Map<Integer, CartItem> cartItems = cartCreator.getAllItems();
                    for (Map.Entry<Integer, CartItem> entry : cartItems.entrySet()) {
                        CartItem cartItem = entry.getValue();
            %>
            <div class="menu">
                <div class="menu-details">
                    <h2><%= cartItem.getName() %></h2>
                    <p>Price: <%= cartItem.getPrice() %></p>
                    <img src="./images/<%= cartItem.getImagePath() %>" alt="<%= cartItem.getName() %>">
                </div>
                <div class="menu-actions">
                    <form action="CartServlet">
                        <input type="hidden" value="update" name="action">
                        <input type="hidden" value="<%= cartItem.getItemId() %>" name="menuId">
                        <input type="text" name="quantity" value="<%= cartItem.getQuantity() %>">
                        <button type="submit">Update</button>
                    </form>
                    <a href="CartServlet?action=increase&menuId=<%= cartItem.getItemId() %>"><button>+</button></a>
                    <a href="CartServlet?action=decrease&menuId=<%= cartItem.getItemId() %>"><button>-</button></a>
                    <a href="CartServlet?action=remove&menuId=<%= cartItem.getItemId() %>"><button>Delete</button></a>
                </div>
            </div>
            <% 
                    }
                } else {
            %>
            <div class="no-items">
                No items in the cart
            </div>
            <% } %>
        </div>
        <div class="order">
            <!-- Add links/buttons to proceed to checkout or continue shopping -->
            <a href="Menu.jsp"><button>Add more Items</button></a>
            <a href="orderConfermation.jsp"><button>Order Checkout</button></a>
        </div>
    </div>
</body>
</html>
