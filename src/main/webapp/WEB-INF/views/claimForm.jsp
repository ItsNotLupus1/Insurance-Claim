<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".copy").hide();
		$(".add-more").click(function(){ 
	          var html = $(".copy").html();
	          $(".after-add-more").after(html);
	      });


	      $("body").on("click",".remove",function(){ 
	          $(this).parents(".control-group").remove();
	      });
	});
</script>
<style>
<!-- /* #uploadFiles { */ -->
<!-- /* 	border: 2px; */ -->
<!-- /* 	padding: 9px 20px; */ -->
<!-- /* 	margin: 8px 0; */ -->
<!-- /* 	size: 20px; */ -->
<!-- /* 	border-radius: 12px; */ -->
<!-- /* } */ -->
.submit{
	margin-left: 15%;
}
</style>
<meta charset="UTF-8">
<title>Claim Your Policy</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<br>
		<c:if test="${empty entity.policy}">
			<div class="card" style="width: 35rem;">
				<div class="card-header">
					<h3>
						<span class="fa fa-lock"></span> Not Available
					</h3>
				</div>
				<div class="card-body">
					<h5 class="card-title">Register your Policy</h5>
					<p class="card-text">Please register your policy to claim them
						soon</p>
					<a href="policyForm" class="btn btn-primary">Policy</a>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty entity.policy}">
			<form:form method="post" action="claimedForm" modelAttribute="model"
				enctype="multipart/form-data">
				<div class="card" style="width: 45rem;">
					<div class="card-header">
						<h2>Claimer Details</h2>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label for="fname">Name</label>
							<spring:bind path="model.claim.name">
								<form:select path="${status.expression}" class="form-control">
									<optgroup label="Holder">
										<form:option value="${entity.fname}" />									
									</optgroup>
									<optgroup label="Dependents">
										<c:forEach var="dependent" items="${entity.dependents}">
											<form:option value="${dependent.firstname}" />
										</c:forEach>									
									</optgroup>
								</form:select>
							</spring:bind>
						</div>
						<div class="form-group">
							<label for="lname">Reason</label>
							<spring:bind path="model.claim.reason">
								<form:input path="${status.expression}" class="form-control"
									id="last" placeholder="Enter the Reason" />
							</spring:bind>
						</div>
						<div class="form-group">
							<label for="fname">Claim Amount</label> <span>&#8377;</span>
							<spring:bind path="model.claim.claimPrice">
								<form:input path="${status.expression}" type="number"
									class="form-control" id="first"
									placeholder="Enter a specific amount to be claimed" />
							</spring:bind>
							<spring:bind path="model.claim.holder_id">
								<form:input type="hidden" path="${status.expression}"
									value="${entity.holder_id}" />
							</spring:bind>
							<spring:bind path="model.claim.status">
								<form:input type="hidden" path="${status.expression}"
									value="pending" />
							</spring:bind>
						</div>
					</div>
				</div>
				<br>
				<div class="card" style="width: 45rem;">
					<div class="card-header">
						<h2>Health Details</h2>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label for="Age">Age</label>
							<spring:bind path="model.health.Age">
								<form:input type="number" path="${status.expression}"
									class="form-control" placeholder="Enter Claimer's Age" />
							</spring:bind>
						</div>
						<div class="form-group">
							<label for="Height">Height</label>
							<spring:bind path="model.health.Height">
								<form:input path="${status.expression}" class="form-control"
									placeholder="Enter Claimer's Height" />
							</spring:bind>
						</div>
						<div class="form-group">
							<label for="Weight">Weight</label>
							<spring:bind path="model.health.Weight">
								<form:input path="${status.expression}" class="form-control"
									placeholder="Enter Claimer's Weight" />
							</spring:bind>
						</div>
						<div class="form-group">
							<label for="ExistingIllness">ExistingIllness</label>
							<spring:bind path="model.health.ExistingIllness">
								<form:input path="${status.expression}" class="form-control"
									placeholder="Enter Claimer's ExistingIllness" />
							</spring:bind>
						</div>
						<spring:bind path="model.health.holder_id">
							<form:input type="hidden" path="${status.expression}"
								value="${entity.holder_id}" />
						</spring:bind>
						<div class="form-group">
							<label for="Injury">Injury</label>
							<spring:bind path="model.health.Injury">
								<form:input path="${status.expression}" class="form-control"
									placeholder="Enter The Cause for Claim" />
							</spring:bind>
						</div>
					</div>
				</div>
				<br>
				<div class="card" style="width: 45rem;">
					<div class="card-header">
						<h2>File Details</h2>
					</div>
					<div class="card-body">
						<div id="files">
							<div class="input-group control-group after-add-more">
								<!-- <label>Choose a file</label>  -->
								<input name="fileUpload" type="file" class="form-control" size="50">
								</div>
							</div>
						</div>
						<input type="hidden" name="holder_id" value="${entity.holder_id}" />
					</div>
				</div>
				<br>
				<div class="submit">
					<input type="submit" value="Submit" class="btn btn-success">
				</div>
			</form:form>
			<div class="copy hide">
				<div class="control-group input-group" style="margin-top:10px">
			    	<input name="fileUpload" type="file" class="form-control" size="50">
			        <div class="input-group-btn"> 
			        	<button class="btn btn-danger remove" type="button"><i class="glyphicon glyphicon-remove"></i> Remove</button>
			        </div>
			    </div>
	       	</div>
		</c:if>
		<br>
	</div>
</body>
</html>