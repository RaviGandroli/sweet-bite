<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .error-container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h1 {
            font-size: 24px;
            color: #333;
            margin-top: 0;
        }
        p {
            font-size: 16px;
            line-height: 1.6;
            color: #666;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
        <p>Sorry, an unexpected error occurred while processing your request. Please try again later.</p>
        <!-- Optionally, provide links or buttons to navigate back -->
        <p><a href="/">Home</a></p>
    </div>
</body>
</html>
