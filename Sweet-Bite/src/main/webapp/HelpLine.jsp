<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help Line</title>
<style>
    body, html {
        height: 100%;
        width:100%;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f2f2f2;
    }
    section {
        background-color: #ffffff;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
        max-width: 400px;
        width: 80%;
    }
    .container {
        text-align: center;
    }
    h3 {
        color: #333333;
        font-size: 24px;
        margin-bottom: 10px;
    }
    h4 {
        color: #555555;
        font-size: 20px;
        margin-bottom: 5px;
    }
    h5 {
        color: #777777;
        font-size: 18px;
        margin-bottom: 20px;
    }
    .home {
        background-color: orange;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        color: #ffffff;
        text-decoration: none;
        transition: background-color 0.3s ease;
        font-size: 16px;
    }
    .home:hover {
        background-color: #ff8c00;
    }
</style>
</head>
<body>
<%@include file="UserNavbar.jsp"%>
<section class="container">
    <%-- Exception handling --%>
    <%@ page isErrorPage="true" %>
    <% try { %>
        <h3>24*7 Service</h3>
        <h4>Help Line Number</h4>
        <h5>8088401908</h5>
        <a href="RestaurantServlet" class="home">Home</a>
    <% } catch (Exception e) { %>
        <!-- Error message displayed if exception occurs -->
        <p>An error occurred while loading the content.</p>
        <p>Please try again later.</p>
    <% } %>
</section>
</body>
</html>
