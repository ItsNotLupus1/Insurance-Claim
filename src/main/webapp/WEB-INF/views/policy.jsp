<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Policy</title>
</head>
<body>
<%@include file="navbar.jsp"%>
	<div class="container">
	<br>
	<c:if test = "${not empty entity.policy}">
		<h2>Policy Details</h2>
		<br>
			<div class="col-md-8">
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">Policy Name</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${entity.policy.polName}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">Policy No.</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${entity.policy.polNum}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">Insurance Company</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${insurance.ins_name}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">Assured Amount</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${entity.policy.assAmount}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">Start Date</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${date}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-4">
                      <h6 class="mb-0">End Date</h6>
                    </div>
                    <div class="col-sm-8 text-secondary">
                      ${date1}
                    </div>
                  </div>
                  <hr>
                 </div>
	</c:if>
	<c:if test="${empty entity.policy}">
	<h2 align="center">Policy Details Form</h2>
	<br>
	<form:form method="POST" action="savePolicy" modelAttribute="policyAction">
		<div class="form-group">
	    	<label for="insComp">Insurance Company</label>
		    <form:select path="ins_id" class="form-control">
		    	<c:forEach var="insurance" items="${policy}">
		    		<form:option value="${insurance.ins_id}">${insurance.ins_name}</form:option>
		    	</c:forEach>
		    </form:select>
	  	</div>
	  	<div class="form-group">
	    	<label for="polNum">Policy Number</label>
	    	<form:input path="polNum" class="form-control" placeholder="Enter given policy number" />
	  	</div>
		<div class="form-group">
	    	<label for="polName">Policy Name</label>
	    	<form:input path="polName" class="form-control" placeholder="Enter Policy Name" />
	  	</div>
	  	<div class="form-group">
	    	<label for="assAmount">Assured Amount</label>
	    	<form:input path="assAmount" class="form-control" placeholder="Enter Assured amount" />
	  	</div>

		<div class="form-group">
	    	<label for="startDate">Start Date</label>
	    	<form:input type="date" path="startDate" id="date" name="dob" class="form-control" placeholder="DD / MM / YYYY"/>
	  	</div>
		<div class="btn-group">
			<button type="submit" class="btn btn-success">Submit</button>
		</div>
	</form:form>
	</c:if>
</div>
	<script>
		$(document).ready(function() {
		  $('.js-date--west').mask('00/00/0000');
		});
	</script>
</body>
</html>