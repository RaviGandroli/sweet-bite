<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.CartCreator"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.tap.model.CartItem"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.tap.model.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <style>
        /* CSS for styling the entire page */
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: flex-start;
            align-items: flex-start;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            display: flex;
            justify-content: space-between;
            width: 60%;
        }
        .orders-container {
            flex: 1; /* Take remaining space */
        }
        .order-details {
            display: flex;
            align-items: center; /* Align items vertically */
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
            margin-right: 10px; /* Add margin to right */
            margin-left: 50px; /* Add margin to left */
        }
        .order-details img {
            width: 100px; /* Adjust image width */
            height: auto; /* Maintain aspect ratio */
            border-radius: 5px; /* Add border radius */
            margin-right: 20px; /* Add margin to right */
        }
        .order-details-info {
            flex: 1; /* Take remaining space */
        }
        .order-details h2 {
            margin-top: 0;
            font-size: 18px;
            color: #333;
        }
        .order-details p {
            margin: 5px 0;
            color: #666;
        }
        .order-details p.label {
            font-weight: bold;
            color: #333;
        }
        .cart-actions,
        .payment-section {
            width: 25%;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-left: 20px;
            margin-right:40px;
            margin-top: 60px; /* Add some margin */
            position: fixed; /* Make the div fixed */
            right: 20px; /* Distance from the right */
        }
        .cart-actions {
            top: 20px; /* Distance from the top */
            margin-left: 10px; /* Add margin to left */
        }
        .payment-section {
            top: 300px; /* Adjust as needed */
            margin-left: 10px; /* Add margin to left */
        }
        .form-group {
            margin-bottom: 10px;
        }
        .submit {
            text-align: center;
        }
        .order-summary {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-top: 30px;
        }
        .checkout-btn {
            text-align: center;
            margin-top: 20px;
        }
        .payment-section select {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100%;
            margin-top: 5px;
        }
        .payment-section label {
            font-weight: bold;
        }
        .total-amount {
            background-color: #e6f7ff;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
        }
        .total-amount h2 {
            margin: 0;
            color: #007bff;
        }
        .total-amount p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <%@include file="UserNavbar.jsp"%>
    
    <div class="container">
        <div class="orders-container">
            <div class="header">
                <h1>Your Items</h1>
            </div>
            <% 
            try {
                CartCreator cartCreator = (CartCreator) session.getAttribute("cart");
                if (cartCreator != null) {
                    Map<Integer, CartItem> cartItems = cartCreator.getAllItems();
                    Set<Entry<Integer, CartItem>> entrySet = cartItems.entrySet();
                    Iterator<Entry<Integer, CartItem>> iterator = entrySet.iterator();
                    while (iterator.hasNext()) {
                        Entry<Integer, CartItem> entry = iterator.next();
                        CartItem cartItem = entry.getValue();
            %>
            <div class="order-details">
                <div class="order-details-info">
                    <h2><%= cartItem.getName() %></h2>
                    <p class="label">Rating:</p>
                    <p><%= cartItem.getRating() %></p>
                    <p class="label">Price:</p>
                    <p><%= cartItem.getPrice() %></p>
                    <p class="label">Quantity:</p>
                    <p><%= cartItem.getQuantity() %></p>
                </div>
                <img src="./images/<%= cartItem.getImagePath() %>" alt="<%= cartItem.getName() %>">
            </div>
            <% 
                    }
                } else {
                    out.print("<p>No items in the cart</p>");
                }
            } catch (Exception e) {
                out.println("<p>Error retrieving cart information: " + e.getMessage() + "</p>");
            }
            %>
        </div>
        <div class="cart-actions">
            <h2>Update Details</h2>
            <% 
            try {
                User user = (User) session.getAttribute("userobj");
                if (user != null && user.getAddress() != null && user.getPhoneNo() != 0) {
            %>
            <form action="UserUpdate?action=updateProfile">
                <input type="hidden" name="action" value="updateProfile">
                <div class="form-group">
                    <label for="phoneno">Phone no</label>
                    <input type="text" name="phoneno" id="phoneno" value="<%= user.getPhoneNo() %>" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" id="address" value="<%= user.getAddress() %>" required>
                </div>
                <div class="submit">
                    <button type="submit">Update</button>
                </div>
            </form>
            <% } else { 
            %>
            <form action="UserUpdate">
                <input type="hidden" name="action" value="updateProfile">
                <div class="form-group">
                    <label for="phoneno">Phone no</label>
                    <input type="text" name="phoneno" id="phoneno" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" id="address" required>
                </div>
                <div class="submit">
                    <button type="submit">Update</button>
                </div>
            </form>
            <% } %>
            <% } catch (Exception e) {
                out.println("<p>Error retrieving user information: " + e.getMessage() + "</p>");
            }
            %>
            <div class="total-amount">
                <h2>Total Amount:<%=session.getAttribute("totalAmount") %></h2>
                
            </div>
        </div>
        <div class="payment-section">
            <h2>Payment Options</h2>
            <form action="CheckoutServlet">
                <label for="payment">Choose a payment option:</label>
                <select id="payment" name="payment">
                    <option value="debit card">Debit Card</option>
                    <option value="credit card">Credit Card</option>
                    <option value="upi">UPI (Unified Payments Interface)</option>
                    <option value="cash">Cash</option>
                </select>
                <div class="submit">
                    <button type="submit">Proceed to Checkout</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
