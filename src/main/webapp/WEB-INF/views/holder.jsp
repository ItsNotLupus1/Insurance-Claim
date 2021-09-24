<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<title>Register</title>
<style>

.container{
    padding-top:50px; 
}
.error{
	color:red;
	font-weight: bold;
}
.field-icon {
  float: right;
   color:black;
  margin-left: -25px;
    margin-right: 25px;
 
  margin-top: -30px;
  position: relative;
  z-index: 2;
}
</style>
</head>

<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<h2 align="center">Policy Holder Form</h2>
		<br>
		<form:form method="POST" action="result" modelAttribute="entity" id="js-form">
			<div class= "row"> 
				<div class="col-md-6">
		    		<label for="fname">First Name</label>
		    		<form:input path="fname" class="form-control" id="first" placeholder="Enter First Name" />
		    		<form:errors class="alert alert-danger error" path="fname" element="div"/>
		    		<br>
		    		<br>
		  		</div>
		  		<div class="col-md-6">
			    	<label for="lname">Last Name</label>
			    	<form:input path="lname" class="form-control" id="last" placeholder="Enter Last Name" />
		    		<form:errors class="alert alert-danger error error" path="lname" element="div" />
		  		</div>
		  	</div>
		  	<br>
		  	<div class="row">
		  		<div class="col-md-6">
				  	<label for="gender">Gender</label>
				  	<div class="form-check-inline">
				  		<form:radiobutton class="form-check-input" label="Male" path="gender" id="gen1" value="Male" />
				  	</div>
				  	<div class="form-check-inline">
				  		<form:radiobutton class="form-check-input" label="Female" path="gender" id="gen2" value="Female"/>
				  	</div>
			  		<form:errors class="alert alert-danger error" path="gender" element="div" />
			  	</div>
				<div class="col-md-6">
		    		<label for="dobUk" class="control-label">Enter your date of birth</label>
	            	<form:input path="dob" type="date" id="date" name="dob" class="form-control"/>
		  			<form:errors class="alert alert-danger error" path="dob" element="div" />
		  		</div>
		  	</div>
		  	<br>
		  	<div class="row">
		  		<div class="col-md-6">
		  			<label for="email">Email</label>
		    		<form:input path="email" class="form-control" id="mail" placeholder="Enter Email" />
		    		<c:if test="${error_e != null}">
		            	<div class="alert alert-danger error"><i class="fas fa-exclamation"></i> ${error_e} </div>
	    			</c:if>
		    		<form:errors class="alert alert-danger error" path="email" element="div" />
		    		<br>
		  		</div>
		  		<div class="col-md-6">
		  			<label for="contact">Contact Number</label>
		    		<form:input type="number" path="mobile" class="form-control" id="pnum" placeholder="Enter Phone Number" />
		    	   	<form:errors class="alert alert-danger error" path="mobile" element="div" />
		  		</div>
		  	</div>
		  	<br>
		  	<div class="row">
		  		<div class="col-md-6">
			    	<label for="uname">User Name</label>
			    	<form:input path="username" class="form-control" id="name" placeholder="Enter Username" />
		    		<c:if test="${error_u != null}">
		            	<div class="alert alert-danger error"><i class="fas fa-exclamation"></i> ${error_u} </div>
	    			</c:if>
		    		<form:errors class="alert alert-danger error" path="username" element="div" />
		    		<br>
		  		</div>
			  	<div class="col-md-6">
				   	<label for="password">Password</label>
			    	<input name="password" type="password" class="form-control" id="password" placeholder="Enter Password" />
		     		<span toggle="#password" class="fa fa-fw fa-eye-slash field-icon toggle-password"></span>
			    	<form:errors class="alert alert-danger error" path="password" element="div" />
			  	</div>
			</div>
			<br>
			<div class="row">
			  	<div class="col-md-6">
			    	<label for="Inputcity">City</label>
			    	<form:input path="city" class="form-control" id="city" placeholder="Enter your City" />
		    		<form:errors class="alert alert-danger error" path="city" element="div" />
		    		<br>
		  		</div>
		  		<div class="col-md-6">
			    	<label for="Inputstate">State</label>
			    	<form:input path="state" class="form-control" id="state" placeholder="Enter State" />
		    		<form:errors class="alert alert-danger error" path="state" element="div" />
		  		</div>
		  	</div>
		  	<br>
		  	<div align="center">
				<form:button type="submit" class="btn btn-success" value="Submit">Submit</form:button>
			</div>
			<br>
		</form:form>
	</div>
	<script>
	  	$(".toggle-password").click(function() {
	
			$(this).toggleClass(" fa-eye-slash fa-eye");
		  	var input = $($(this).attr("toggle"));
		  	if (input.attr("type") == "password") {
		    input.attr("type", "text");
		  	} else {
		    	input.attr("type", "password");
		 	}
		});
    </script>
</body>
</html>