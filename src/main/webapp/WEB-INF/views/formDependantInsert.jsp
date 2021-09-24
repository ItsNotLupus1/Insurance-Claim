<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="dependentis">
	<form:input type="hidden" path="dependantCommand.depends[${dependantNumber}].holder_id" value="${entity.holder_id}" size="40" />
	<hr>
	<div class="row">
		<div class="col-md-6">
			<label for="fname">FirstName</label>
			<form:input class="form-control"
				path="dependantCommand.depends[${dependantNumber}].firstname"
				size="40" />
		</div>
		<div class="col-md-6">
			<label for="lname">LastName</label>
			<form:input class="form-control"
				path="dependantCommand.depends[${dependantNumber}].lastname"
				size="40" />
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-6">
			<label for="relation">Relation</label>
			<form:select class="form-control"
				path="dependantCommand.depends[${dependantNumber}].relation">
				<form:option value="Son" />
				<form:option value="Daughter" />
				<form:option value="Spouse" />
				<form:option value="Mother" />
				<form:option value="Father" />
			</form:select>
		</div>
		<div class="col-md-6">
			<label for="dobUk" class="control-label">Enter your date of
				birth</label>
			<form:input
				path="dependantCommand.depends[${dependantNumber}].date" type="date" id="date"
				name="dob" class="form-control"
				 />
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-6">
			<label for="email">Email</label>
			<form:input class="form-control"
				path="dependantCommand.depends[${dependantNumber}].mail"
				size="40" />
		</div>
		<div align="right">
			<button class="btn btn-primary" type="button" id="removeButton"><i class="fa fa-minus"></i> Remove Dependents</button>
		</div> 
	</div>
	<hr>
	</div>
	<script>	
		$("#removeButton").click(function() {
			$('.dependentis').remove();
		});
	</script>
</body>
</html>