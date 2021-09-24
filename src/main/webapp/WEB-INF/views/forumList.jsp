<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<br>
		<h2>Discussion Forum (${count})</h2>
		<br> 
		<c:if test="${not empty entity}">
		<a href="postForm" class="btn btn-primary">Add Post</a>
		</c:if> 
		<br>
		<br>
		<table class="table table-striped table-bordered"
			style="width: 50rem;">
			<thead>
				<tr>
					<th scope="col">Sr.No.</th>
					<th scope="col">Title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${forums}" varStatus="loop">
					<tr>
						<td><c:out value="${loop.index+1}" /></td>
						<td><a href="/forum/${list.post_id}">${list.title}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>