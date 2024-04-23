<%@page import="com.tap.model.Restaurant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
List<Restaurant> restaurants=(List<Restaurant>)request.getAttribute("restaurants");
List<Restaurant> restaurant=(List<Restaurant>)session.getAttribute("res");

for(Restaurant r:restaurants){
	out.print("you got res");
}
for(Restaurant r1:restaurant){
	out.print("got it");
}
  %>

</body>
</html>