<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  	<div class="collapse navbar-collapse" id="navbarText">
	    <ul class="navbar-nav mr-auto">
	    	<li class="nav-item">
	    		<sec:authorize access="isAnonymous()">
	        		<a class="nav-link" href="/">Home</a>
	        	</sec:authorize>
	        	<sec:authorize access="isAuthenticated()">
	        		<a class="nav-link" href="/home">Home</a>
	        	</sec:authorize>
	        	
	      	</li>
	      	<li class="nav-item">
	      		<sec:authorize access="isAnonymous()">
	        		<a class="nav-link" href="/register">Register</a>
	        	</sec:authorize>
	        	<sec:authorize access="isAuthenticated()">
	        		<a class="nav-link" href="/dependents">Dependents</a>
	        	</sec:authorize>
	      	</li>
	      	<li class="nav-item">
	      		<sec:authorize access="isAuthenticated()">
	        		<a class="nav-link" href="/claim">PolicyClaim</a>
	        	</sec:authorize>
	      	</li>
	      	<li class="nav-item">
	      		<sec:authorize access="isAuthenticated()">
	        		<a class="nav-link" href="/policyForm">Policy</a>
	        	</sec:authorize>
	      	</li>
<!-- 	      	<li class="nav-item"> -->
<%-- 	      		<sec:authorize access="isAuthenticated()"> --%>
<!-- 	      			<a class="nav-link" href = "getFile">File</a> -->
<%-- 	      		</sec:authorize> --%>
<!-- 	      	</li> -->
	      	<c:if test="${not empty entity.policy }">
		      	<li class="nav-item">
		      		<sec:authorize access="isAuthenticated()">
		        			<a class="nav-link" href="/getFullList">Hospital List</a>
		        	</sec:authorize>
		      	</li>
	      	</c:if>
	      	<li class="nav-item">
	      		<sec:authorize access="hasAuthority('ADMIN')">
	      			<a class="nav-link" href = "/hospForm">Hospital Form</a>
	      		</sec:authorize>
	      	</li>
	    </ul>
	  		<ul class="navbar-nav">
	  			<li class="nav-item dropdown">
	  				<sec:authorize access="isAnonymous()">
		        		<a class="nav-link" href="/login">Login</a>
		        	</sec:authorize>
		        	<sec:authorize access="isAuthenticated()">
		        		<div class="btn-group">
			        		<a class="nav-link" href="/profile">${entity.username}</a>
			        		<button type="button" class="btn dropdown-toggle dropdown-toggle-split" style="color:white;" data-toggle="dropdown">
	            				<span class="sr-only">Toggle Dropdown</span>
	        				</button>
				        	<div class="dropdown-menu dropdown-menu-right">
				        		<form action="/logout" method="get">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="submit" class="btn btn-link" style="color:black" name="Logout"
										value="Logout"></input>
								</form>
								<hr>
								<a class="nav-link" style="color:black" href="/feedList">All Feeds</a>
				        	</div>
				        </div>
			        </sec:authorize>
		      	</li>
	  		</ul>
  	</div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
<script>
var currentPage = location.href.match(/(.*?)([^\/]+)$/).pop();
$(Array.prototype.filter.call($('.navbar-nav li a:nth-child(1)'), function(item)
{
    return item.href.endsWith(currentPage)
})).parents('li').addClass('active');
</script>

</body>
</html>