<%@page import="com.tap.model.User"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Navbar Example</title>

<!-- Include Font Awesome CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
  /* CSS for Navbar */
  body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f8f9fa; /* Change background color to a light gray */
  }

  .navbar {
    display: flex; /* Use flexbox */
    justify-content: space-between; /* Distribute space between elements */
    align-items: center; /* Center vertically */
    background-color: #343a40; /* Change background color to dark */
    color: #ffffff; /* Change text color to white */
    position: fixed;
    top: 0;
    width: 100%;
    height: 70px; /* Set height of navbar */
    padding-left: 10%; /* Add padding to the left */
    padding-right: 10%; /* Add padding to the right */
    box-sizing: border-box; /* Ensure padding doesn't increase width */
    box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Add shadow */
  }

  .navbar-left {
    display: flex; /* Use flexbox */
    align-items: center; /* Center vertically */
  }

  .navbar-right {
    display: flex; /* Use flexbox */
    align-items: center; /* Center vertically */
  }

  .navbar-left a,
  .navbar-right a {
    color: #ffffff; /* Change text color to white */
    text-decoration: none;
    padding: 10px 15px;
    display: inline-block;
  }

  .navbar-left img {
    height: 40px;
    vertical-align: middle;
  }

  .navbar-left a {
    font-size: 20px;
    margin-left: 10px;
    vertical-align: middle;
  }

  .navbar-right form {
    display: flex; /* Use flexbox */
    align-items: center; /* Center vertically */
  }

  .radio-buttons {
    display: flex; /* Use flexbox */
    flex-direction: column; /* Arrange items in column */
    margin-right: 10px; /* Add spacing to the right */
  }

  .radio-buttons div {
    margin-bottom: 5px; /* Add spacing between radio buttons */
  }

  .search-container {
    display: flex; /* Use flexbox */
    align-items: center; /* Center vertically */
    margin-left: 10px; /* Add spacing to the left */
  }

  .navbar-right input[type="text"] {
    padding: 7px;
    border-radius: 5px;
    background-color: #ffffff; /* Change background color to white */
    color: #333333; /* Change text color to dark */
    border: 1px solid #ced4da; /* Add border */
  }

  .navbar-right a:hover {
    color: orange; /* Change text color on hover */
  }
</style>


</head>
<body>
<% User us = (User)session.getAttribute("userobj"); %>
<div class="navbar">
  <div class="navbar-left">
    <a href="RestaurantServlet"><i class="fas fa-hotel"></i> Sweet-Bite </a>
  </div>
  <div class="navbar-right">
    <form action="/Sweet-Bite/searchServlet">
        <div class="radio-buttons">
            <div>
                <input type="radio" id="searchMenu" name="searchType" value="menu">
                <label for="searchMenu">Menu Items</label>
            </div>
            <div>
                <input type="radio" id="searchRestaurant" name="searchType" value="restaurant">
                <label for="searchRestaurant">Restaurants</label>
            </div>
        </div>
        <div class="search-container">
            <input type="text" placeholder="Search..." name="ch">
        </div>
    </form>
    <a href="HelpLine.jsp"><i class="fas fa-question-circle"></i> Help</a>
    
    <% if(us!=null) { %>
      <a href="UserProfile.jsp"><i class="fas fa-user"></i> <%=us.getUserName() %></a>
    <% } else { %>
      <a href="Login.jsp"><i class="fas fa-sign-in-alt"></i> Sign in</a>
    <% } %>
    
    <a href="viewCart.jsp"><i class="fas fa-shopping-cart"></i> Cart</a>
    <a href="ordersHistoryServlet?action=viewOrders"><i class="fas fa-list-alt"></i> Your Orders</a>
    <a href="logoutservlet"><i class="fas fa-sign-out-alt"></i> Log Out</a>
  </div>
</div>

<!-- Some space at both ends -->
<div style="height: 100px;"></div>
<!-- Content of your page -->

</body>
</html>
