<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Health</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#cancel").click(function(){
			$.get("<%=request.getContextPath()%>/getHealthCancelMethod")
		})
	})
	
</script>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
	<br>
	<h2 align="center">Health Details Form</h2>
	<br>
	<br>
	<form:form modelAttribute="health">
		<div class= "row"> 
			<div class="col-md-6">
	    		<label for="Age">Age</label>
		    	<form:input type="number" path="Age" class="form-control" placeholder="Enter Claimer's Age" />
	  			<form:errors path="Age" cssClass="error" />
	  		</div>
	  		<div class="col-md-6">
	    		<label for="Height">Height</label>
	    		<form:input path="Height" class="form-control" placeholder="Enter Claimer's Height" />
	  			<form:errors path="Height" cssClass="error" />
	  		</div>
		</div>
		<br>
		<div class= "row"> 
			<div class="col-md-6">
	    		<label for="Weight">Weight</label>
	    		<form:input path="Weight" class="form-control" placeholder="Enter Claimer's Weight" />
	  			<form:errors path="Weight" cssClass="error" />
	  		</div>
	  		<div class="col-md-6">
	    		<label for="ExistingIllness">ExistingIllness</label>
	    		<form:input path="ExistingIllness" class="form-control" placeholder="Enter Claimer's ExistingIllness" />
	  			<form:errors path="ExistingIllness" cssClass="error" />
	  		</div>
	  		<form:input type="hidden" path="holder_id" value="${healthEntity.holder_id}"/>
		</div>
		<br>
		<div class= "row"> 
			<div class="col-md-6">
	    		<label for="Injury">Injury</label>
	    		<form:input path="Injury" class="form-control" placeholder="Enter The Cause for Claim" />
	  			<form:errors path="Injury" cssClass="error" />
	  		</div>
		</div>
		<br>
		<div align="center">
			<button type="submit" class="btn btn-success">Next</button>
			<button type="button" id="cancel" class="btn btn-danger">Cancel</button>
		</div>
	</form:form>
</div>
</body>
</html>