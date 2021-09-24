<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container margin-top-bottom">
		<br>
		<div class="row">
			<div class="col-md-5">
				<h1>RSS News feeds</h1>
				<br>
				<table class="table table-striped table-bordered" style="width: 50rem;">
					<thead>
						<tr>
							<th scope="col">Sr.No.</th>
							<th scope="col">Feed Image</th>
							<th scope="col">Feed Name</th>
							<th scope="col">View</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${feeds}" var="feed" varStatus="loop">
							<tr>
								<td><c:out value="${loop.index+1}" /></td>
								<td><img src="${feed.image}"></td>
								<td><c:out value="${feed.feed_name}" /></td>
								<td><a href="feeds?id=${feed.id}">${feed.feed_name}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<sec:authorize access="hasAuthority('ADMIN')">
				<div class="col-md-3 offset-md-3">
					<div class="card" style="width: 30rem;">
						<div class="card-header">
							<h3>Add XML RSS Feed</h3>
						</div>
						<div class="card-body">
							<form:form method="POST" action="addForm" modelAttribute="feed">
								<div class="form-group">
									<form:label path="url">XML RSS Feed url: </form:label>
									<form:input path="url" class="form-control"
										placeholder="https://www.15min.lt/rss" type="url" maxlength="255"
										pattern="https://.*" />
								</div>
								<div class="form-group">
									<form:label path="feed_name">XML RSS Feed name: </form:label>
									<form:input path="feed_name" class="form-control"
										placeholder="Feed name 4-20 length" minlength="4" maxlength="20" />
								</div>
								<input type="submit" value="Add Feed" class="btn btn-primary" />
								<small style="color:red;"><i>${message}</i></small>
							</form:form>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
	</div>
</body>
</html>