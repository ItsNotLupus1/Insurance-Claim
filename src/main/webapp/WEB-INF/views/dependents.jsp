<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#addDependantButton{
	border: 2px;
	padding: 9px 20px;
	margin: 8px 0;
	size: 120px;
	border-radius: 12px;
}
.error{
	color: red;
}
</style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
<br>
<h2 align="center">Dependents Form</h2>
<br>
	<br>
	<form:form method="POST" action="processClassForm" name="classForm" id="classForm" modelAttribute="dependantCommand">
		<spring:bind path="dependantCommand.depends[0].holder_id">
			<form:input type="hidden" value="${entity.holder_id}" path="${status.expression}" size="40"/>
		</spring:bind>
		<div align = "right">
			<button class="btn btn-success" type="button" id="addDependantButton"><i class="fa fa-plus"></i> Add Dependents</button>
		</div>
		<div class= "row"> 
			<div class="col-md-6">
	    		<label for="fname">FirstName</label>
	    		<spring:bind path="dependantCommand.depends[0].firstname">
					<form:input id="fname" class="form-control" placeholder="Enter the first name" path="${status.expression}" size="40" />
					<form:errors path="${status.expression}" cssClass="error"/>
	  			</spring:bind>
	  		</div>
	  		<div class="col-md-6">
	    		<label for="lname">LastName</label>
	    		<spring:bind path="dependantCommand.depends[0].lastname">
					<form:input class="form-control" placeholder="Enter the last name" path="${status.expression}" size="40" />
					<form:errors path="${status.expression}" cssClass="error"/>
	  			</spring:bind>
	  		</div>
	  	</div>
	  	<br>
	  	<div class="row">
	  		<div class="col-md-6">
		  		<label for="relation">Relation</label>
		  		<spring:bind path="dependantCommand.depends[0].relation">
			  		<form:select class="form-control" path="${status.expression}">
			  			<form:option value="Son" />
							<form:option value="Daughter" />
							<form:option value="Spouse" />
							<form:option value="Mother" />
							<form:option value="Father" />
							<form:option value="Brother" />
							<form:option value="Sister" />
							<form:option value="Grand Father" />
							<form:option value="Grand Mother" />
			  		</form:select>
			  		<form:errors path="${status.expression}" cssClass="error"/>
			  	</spring:bind>	
			</div>
			<br>
			<div class="col-md-6">
	    		<label for="dobUk" class="control-label">Enter your date of birth</label>
	  			<spring:bind path="dependantCommand.depends[0].date">
					<form:input id="date" type="date" path="${status.expression}" size="40" class="form-control" />
					<form:errors path="${status.expression}" cssClass="error"/>
	  			</spring:bind>
	  		</div>
	  	</div>
	  	<br>
	  	<div class="row">
	  		<div class="col-md-6">
	    		<label for="email">Email</label>
	    		<spring:bind path="dependantCommand.depends[0].mail">
					<form:input class="form-control" placeholder="Enter the email address" path="${status.expression}" size="40" />
					<form:errors path="${status.expression}" cssClass="error"/>
	  			</spring:bind>
	  		</div>
	  	</div>
	  	<br>
	  	<div id="submitRow" align="center">
			<button type="submit" class="btn btn-success">Submit</button>
		</div>
	</form:form>
	</div>
	<script>
		
		$(document).ready(function() {
			var dependantsPosition = 0;
			$("#addDependantButton").click(function() {
				dependantsPosition++;
		 
				$.get("<%=request.getContextPath()%>/appendDependantsView", { fieldId: dependantsPosition},
					function(data){
						$("#submitRow").before(data);
				});
			});
		});
	</script>
</body>
</html>