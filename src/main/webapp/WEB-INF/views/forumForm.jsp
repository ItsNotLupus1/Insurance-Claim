<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<h2 align="center">Add Discussion</h2>
		<br>
		<form:form method="POST" action="/addPost" modelAttribute="forum">
			<div class="form-group">
				<label>Title</label>
				<form:input path="title" class="form-control" placeholder="Enter appropriate title" required="required" />
			</div>
			<div class="form-group">
				<label>Description</label>
				<form:textarea path="desc" class="form-control" placeholder="Enter appropriate title" required="required" />
			</div>
			<form:input type="hidden" path="holder_id" value="${entity.holder_id}" />
			<c:if test="${not empty entity.posts}">
				<form:input type="hidden" path="post_id" value="${forum.post_id}" />
			</c:if>
			<div align="center">
				<input type="submit" class="btn btn-success" value="Submit">
				<a href="/cancel" class="btn btn-danger">Cancel</a>
			</div>
		</form:form>
	</div>
</body>
</html>