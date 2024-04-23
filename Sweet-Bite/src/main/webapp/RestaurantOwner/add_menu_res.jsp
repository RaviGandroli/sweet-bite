<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Menu</title>
<style type="text/css">
/* styles.css */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
}

.container {
    max-width: 500px;
    margin: 50px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.container h2 {
    text-align: center;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    font-weight: bold;
}

.form-group input[type="text"],
.form-group textarea,
.form-group input[type="file"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 16px;
}

.form-group textarea {
    resize: vertical;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin-top: 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
    font-size: 16px;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

</style>
</head>
<body>
<jsp:include page="ResNavbar.jsp" />

<div class="container">
    <h2>Add Menu</h2>
     <!-- Display error message if an exception occurred -->
    <c:if test="${not empty failmsg}">
        <h5 class="error-msg">${failmsg}</h5>
        <c:remove var="failmsg"  />
    </c:if>
    
    <!-- Display success message if the update was successful -->
    <c:if test="${not empty succmsg}">
        <h5 class="success-msg">${succmsg}</h5>
        <c:remove var="succmsg"  />
    </c:if>
    <form action="../addMenuServletbyres" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="menuName">Menu Name:</label>
            <input type="text" id="menuName" name="menuName" required>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
        </div>

        <div class="form-group">
            <label for="imagePath">Image Path:</label>
            <input type="file" id="imagePath" name="imagePath" accept="image/*" required>
        </div>

        <input type="submit" name="action"  value="Add Menu">
    </form>
</div>

</body>
</html>
