<%@page import="com.tap.model.User"%>
<%@page isErrorPage="true" %> <!-- Indicates that this JSP page handles errors -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <style>
        /* Your CSS styles */
    </style>
</head>
<body>
<jsp:include page="AgentNavbar.jsp"/>

<div class="container">
    <h4 class="text-center text-primary">Edit Profile</h4>
    
    <!-- Display error message if an exception occurred -->
    <c:if test="${not empty error}">
        <h5 class="error-msg">${error}</h5>
    </c:if>
    
    <!-- Display success message if the update was successful -->
    <c:if test="${not empty succmsg}">
        <h5 class="success-msg">${succmsg}</h5>
        <c:remove var="succmsg" scope="session" />
    </c:if>
    
    <%  
        try {
            // Retrieve user object from session
            User us = (User)session.getAttribute("userobj");
    %>
    
    <!-- Display form for updating user profile -->
    <form id="updateForm" action="/Sweet-Bite/UserUpdate" method="post">
        <input type="hidden" value="<%= us.getUserId() %>" name="id">
        <input type="hidden" value="editprofileagent" name="name">
        
        <!-- Form fields for user details -->
        <div class="form-group">
            <label for="username">Name</label>
            <input type="text" name="username" id="username" value="<%= us.getName() %>" required>
        </div>
        
        <!-- Other form fields -->
        <!-- ... -->
        
        <div class="submit">
            <button type="submit">Update</button>
        </div>
    </form>
    
    <%  
        } catch (Exception e) {
            // If an exception occurs, set error message
            request.setAttribute("error", "An error occurred while processing your request. Please try again later.");
        }
    %>
    
    <!-- If there is no success message, display a generic error message -->
    <c:if test="${empty succmsg && empty error}">
        <h5 class="error-msg">Something went wrong. Please try again later.</h5>
    </c:if>
    
</div>

</body>
</html>
