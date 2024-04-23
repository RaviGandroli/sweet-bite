<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Menu</title>
    <style>
        /* Your CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            text-align: center;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<jsp:include page="ResNavbar.jsp" />

<div class="container">
    <h2>Remove Menu</h2>
    <form action="../addMenuServletbyres" method="POST" enctype="multipart/form-data">
        <label for="menuname">Menu Name:</label><br>
        <textarea id="menuname" name="menuname" required></textarea><br>
        
        
      
        <input type="submit" name="action" value="Remove Menu">
    </form>
</div>
</body>
</html>
