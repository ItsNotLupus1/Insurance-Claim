<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
	    <h1>Feed ${feed.feed_name}</h1>
	    <p>URL address:<a href="${feed.url}">${feed.url}</a></p>
	    <p>Update time and date: ${feed.last_update}</p>
	    <p>Article count: ${item_count}</p>
	    <p>Most recent articles title and URL: </p>
	
	    <table class="table table-hover">
	        <tr>
	            <th>Title</th>
	            <th>URL</th>
	        </tr>
	        <c:forEach items="${itemTopFive}" var="item">
	            <tr>
	                <td>${item.title}</td>
	                <td><a href="${item.link}">${item.link}</a></td>
	
	            </tr>
	        </c:forEach>
	    </table>
	</div>
</body>
</html>