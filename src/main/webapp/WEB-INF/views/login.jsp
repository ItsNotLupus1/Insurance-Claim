<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<style>
.container{
    padding-top:50px; 
}
span{
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
<%-- 	<c:if test="${string != null}"> --%>
<!-- 		<div class="alert alert-success alert-dismissible fade show" role="alert"> -->
<%-- 	         ${string} --%>
<!-- 	  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
	
	<h1>Please Login Here</h1>
	<br>
    <c:if test="${not empty error_string}">
        <div style="color:red; font-weight: bold;">
            <span> ${error_string} </span>
        </div>
        <br>
    </c:if>

    <c:url var="post_url" value="/login"/>
    <form action="${post_url}" method="post">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<div class="form-group">
    		<label for="username">UserName</label>
    		<input name="username" class="form-control" id="username" placeholder="Enter Username" />
  		</div>
	  	<div class="form-group">
	   		<label for="password">Password</label>
	   		<div>
	    		<input name="password" type="password" class="form-control" id="password" placeholder="Enter Password" />
	     		<span toggle="#password" class="fa fa-fw fa-eye-slash field-icon toggle-password"></span>
	  		</div>
	  	</div>
      	<input class="btn btn-primary" type="submit" name="Login" value="Login"></input>
    </form>
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
