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
		<h2 align="center">Add Hosp Form</h2>
		<br>
		<form:form method="POST" action="saveHosp" modelAttribute="hospital"
			id="js-form" onsubmit="return validate()">
			
			<div class="form-group">
				<label for="hname">Hospital Name</label>
				<form:input path="hname" class="form-control" id="hname" placeholder="Enter the Hospital Name" />
				<form:errors path="hname" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label for="city">City</label>
				<form:input path="city" class="form-control" id="city" placeholder="Enter the City" />
				<form:errors path="city" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label for="state">State</label>
				<form:input path="state" class="form-control" id="state" placeholder="Enter the State" />
				<form:errors path="state" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label for="country">Country</label>
				<form:input path="country" class="form-control" id="country" placeholder="Enter the Country" />
				<form:errors path="country" cssClass="error" />
			</div>
			
			<div class="form-group">
				<label for="address">Address</label>
				<form:input path="address" class="form-control" id="address" placeholder="Enter the Address" />
				<form:errors path="address" cssClass="error" />
			</div>
			
			
			<div align="center">
				<button type="submit" class="btn btn-success">Submit</button>
			</div>
		</form:form>
	</div>
</body>
</html>