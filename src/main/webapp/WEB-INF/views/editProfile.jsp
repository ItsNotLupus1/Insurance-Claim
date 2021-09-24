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
<h2 align="center">Edit ${entity.username} Profile</h2>
<br>
	<form:form method="POST" action="updateProfile" modelAttribute="entity" id="js-form">
		<form:input type="hidden" path="holder_id"/>
		<div class= "row"> 
			<div class="col-md-6">
	    		<label for="fname">FirstName</label>
	    		<form:input path="fname" class="form-control" id="first" placeholder="Enter FirstName" />
	  		</div>
	  		<div class="col-md-6">
	    		<label for="lname">LastName</label>
	    		<form:input path="lname" class="form-control" id="last" placeholder="Enter LastName" />
	  		</div>
	  	</div>
	  	<br>
	  	<div class="row">
	  		<div class="col-md-2">
		  		<label for="gender">Gender</label>
		  	</div>
	  		<div class="col-md-4">
		  		<form:radiobutton class="form-check-input" label="Male" path="gender" id="gen" value="Male" />
		  		<br>
		  		<form:radiobutton class="form-check-input" label="Female" path="gender" id="gen" value="Female"/>
			</div>
			<br>
			<div class="col-md-6">
	    		<label for="dobUk" class="control-label">Enter your date of birth</label>
            	<form:input path="dob" id="date" name="dob" class="form-control input-lg js-date--west" placeholder="DD / MM / YYYY"/>
	  		</div>
	  	</div>
	  	<br>
	  	<div class="row">
	  		<div class="col-md-6">
	    		<label for="email">Email</label>
	    		<form:input path="email" class="form-control" id="mail" placeholder="Enter Email" />
	  		</div>
	  		<div class="col-md-6">
	    		<label for="contact">Contact Number</label>
	    		<form:input path="mobile" class="form-control" id="pnum" placeholder="Enter Phone Number" />
	  		</div>
	  	</div>
	  	<br>
	  	<div class="row">
	    	<form:input type="hidden" path="username" class="form-control" id="name" placeholder="Enter Username" />
		    <form:input type="hidden" path="password" class="form-control" id="pword" placeholder="Password" />
		</div>
		<br>
		<div class="row">
		  	<div class="col-md-6">
	    		<label for="Inputcity">City</label>
	    		<form:input path="city" class="form-control" id="city" placeholder="Enter your City" />
	  		</div>
	  		<div class="col-md-6">
	    		<label for="Inputstate">State</label>
	    		<form:input path="state" class="form-control" id="state" placeholder="Enter State" />
	  		</div>
	  	</div>
	  	<br>
	  	<div align="center">
			<button type="submit" class="btn btn-success">Submit</button>
		</div>
	</form:form>
	<script>
		$(document).ready(function() {
			$('.js-date--west').mask('00/00/0000');
		});
	</script>
</div>
</body>
</html>