<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container {
	padding-top: 50px;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<h1>Welcome ${entity.fname}</h1>
		<br> 
		<br>
		<h2>Claim History</h2>
		<br>
		<c:if test="${empty entity.claim}">
		<h5 style="color:red">No claims yet</h5>
		</c:if>
		<c:if test="${not empty entity.claim}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Sr.No.</th>
						<th scope="col">Claimed Name</th>
						<th scope="col">Reason</th>
						<th scope="col">Claimed Amount</th>
						<th scope="col">Claimed Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${entity.claim}" varStatus="loop">
						<tr>
							<td><c:out value="${loop.index+1}" /></td>
							<td><c:out value="${list.name}" /></td>
							<td><c:out value="${list.reason}" /></td>
							<td><c:out value="${list.claimPrice}" /></td>
							<td><c:out value="${list.status}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>		
		<br>
		<h2>Files History</h2>
		<br>
		<c:if test="${empty entity.fileUpload}">
		<h5 style="color:red">No files uploaded yet</h5>
		</c:if>
		<c:if test="${not empty entity.fileUpload}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Sr.No.</th>
						<th scope="col">File Name</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${entity.fileUpload}" varStatus="loop">
						<tr>
							<td><c:out value="${loop.index+1}" /></td>
							<td><c:out value="${list.fileName}" /></td>
							<td><a href = "download/filename=${list.fileName}&&user=${entity.holder_id}">Download</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>		
	</div>
	
</body>
</html>