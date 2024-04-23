<!DOCTYPE html>
<%@page import="com.tap.model.Restaurant"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurant Sections</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
  /* Reset or normalize CSS here if needed */

  /* General styles */
  body {
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 20px;
  }

  h1 {
    text-align: center;
    margin-bottom: 30px;
  }

  p {
    font-size: 16px;
  }

  /* Restaurant styles */
  .restaurant-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 20px;
  }

  .restaurant {
    width: calc(25% - 20px);
    box-sizing: border-box;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }

  .restaurant img {
    width: 100%;
    border-radius: 8px;
    margin-bottom: 15px;
    height: 150px;
  }

  .restaurant h2 {
    margin-top: 0;
    margin-bottom: 10px;
    font-size: 20px;
  }

  .restaurant p {
    margin: 0;
    font-size: 14px;
  }

  /* Offer styles */
  .offer-section {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .offer-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }

  .offer {
    width: calc(33.33% - 20px);
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }

  .offer h3 {
    margin-top: 0;
    margin-bottom: 10px;
    font-size: 18px;
  }

  .offer p {
    margin: 0;
    font-size: 14px;
  }

  /* Fail message style */
  .fail-msg {
    color: red;
    text-align: center;
  }
</style>

</head>
<body>
<%@include file="UserNavbar.jsp"%>

 <c:if test="${not empty failmsg}">
        <h5 class="fail-msg">${failmsg}</h5>
        <c:remove var="failmsg" scope="session" />
    </c:if>

<div>
    <%-- Check if user is logged in --%>
    <% if (session.getAttribute("userobj") != null) { %>
        <%-- Display welcome message with user's name --%>
        <% User user=(User)session.getAttribute("userobj");%>
        <h1>Welcome <%= user.getName() %>!</h1>
        
    <% } else{%>
    <h1>Welcome to Sweet-Bite </h1>
    <p>please login for your account or signup</p>
    <%} %>
</div>

<div class="offer-section">
    <h2>Special Offers</h2>
    <div class="offer-container">
        <!-- Offer 1 -->
        <div class="offer">
            <h3>Get 20% off on your first order!</h3>
            <p>Use code FIRSTORDER20 at checkout.</p>
        </div>
        <!-- Offer 2 -->
        <div class="offer">
            <h3>Buy one get one free on select items!</h3>
            <p>Valid for a limited time only.</p>
        </div>
        <!-- Offer 3 -->
        <div class="offer">
            <h3>Happy Hour Deals!</h3>
            <p>Enjoy discounted prices on drinks from 4pm to 7pm.</p>
        </div>
        <!-- Add more offers as needed -->
    </div>
</div>

<p>Enjoy exploring our selection of restaurants.</p>
<div class="restaurant-container">

    <% 
    try {
        List<Restaurant> restaurants=(List<Restaurant>)session.getAttribute("restaurants");
       
        if (restaurants != null) {
            for (Restaurant r : restaurants) {
    %>
    <div class="restaurant">
        <a href="MenuServlet?restaurantId=<%= r.getRestaurantId()%>"><img src="./images/<%= r.getRestaurantImagePath() %>" alt="<%= r.getRestaurantName() %>"></a>
        <h2><%= r.getRestaurantName() %></h2>
        <p>Rating: <%=r.getRatings() %></p>
        <p>Estimated Time: <%=r.getEta() %></p>
        <p>Cuisine Type: <%= r.getCuisineType() %></p>
    </div>
    <% 
            } 
        } else {
            out.println("No restaurants found.");
        }
    } catch (Exception e) {
        out.println("An error occurred while loading the content.");
        out.println("Please try again later.");
    }
    %>
</div>

</body>
</html>
