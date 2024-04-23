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
            margin-bottom: 20px;
        }
        .header h1 {
            font-size: 36px;
            margin: 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .restaurant-container {
            margin-bottom: 20px;
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
            margin-bottom: 10px;
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
        .total {
            text-align: right;
            margin-top: 20px;
            padding-top: 10px;
            border-top: 1px solid #e0e0e0;
            font-size: 20px;
            background-color: #ffffff;
            padding-right: 20px;
        }
        .order {
            text-align: center;
            margin-top: 20px;
        }
        .order button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .order button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%@ include file="UserNavbar.jsp" %>
    <div class="header">
        <h1>Do you want to confirm your order?</h1>
    </div>

    <div class="container">
        <div class="restaurant-container">
            <% 
                CartCreator cartCreator = (CartCreator) session.getAttribute("cart");
                double totalAmount = 0.0;
                if (cartCreator != null) {
                    Map<Integer, CartItem> cartItems = cartCreator.getAllItems();
                    Set<Entry<Integer, CartItem>> entrySet = cartItems.entrySet();
                    Iterator<Entry<Integer, CartItem>> iterator = entrySet.iterator();
                    while (iterator.hasNext()) {
                        Entry<Integer, CartItem> entry = iterator.next();
                        CartItem cartItem = entry.getValue();
                        totalAmount += cartItem.getPrice(); // Accumulate total amount
            %>
            <div class="menu">
                <div class="menu-details">
                    <h2><%= cartItem.getName() %></h2>
                    <p>Price: <%= cartItem.getPrice() %></p>
                    <p>Quantity: <%= cartItem.getQuantity() %></p>
                    <img src="./images/<%= cartItem.getImagePath() %>" alt="<%= cartItem.getName() %>">
                </div>
                <div class="menu-actions">
                    <!-- Actions -->
                </div>
            </div>
            <% 
                    }
                } else {
                    out.print("No cart items available");
                }
            %>
            <div class="total">
                Total Amount: <%= totalAmount %>
            </div>
        </div>
        <div class="order">
            <!-- Add links/buttons to proceed to checkout or continue shopping -->
            <a href="Checkout.jsp"><button>Proceed to Order Confirmation</button></a>
        </div>
    </div>
</body>
</html>
