<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<style>
input.hidden {
	position: absolute;
	left: -9999px;
}

.container {
	padding-top: 50px;
}

#profile-image1 {
	cursor: pointer;
	width: 100px;
	height: 100px;
	border: 2px solid #03b1ce;
}

.row{
	margin-left:300px;
}

.main-body{

}

.bot-border {
	border-bottom: 1px #f8f8f8 solid;
	margin: 5px 0 5px 0
}

.tabs{
    	margin: 20px;
    }
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
    <div class="main-body">
          		<ul class="nav nav-pills nav-fill" id="myTab">
          			<li class="nav-item"><a href="#tab1" class="nav-link active">Profile</a></li>
          			<li class="nav-item"><a href="#tab2" class="nav-link">Dependents</a></li>
          		</ul>
          		<br>
          		<br>
          		<div class="tab-content">
	            	<div class="tab-pane fade show active" id= "tab1">
	            		<div>
    						<h2>User Profile <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="bi bi-pencil-square"></i> Edit</button></h2>
    					</div>
    					<br>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Full Name</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.fname} ${entity.lname}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Gender</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.gender}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Date of Birth</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.dob}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Email</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.email}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Username</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.username}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Phone</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.mobile}
	                    </div>
	                  </div>
	                  <hr>
	                  <div class="row">
	                    <div class="col-sm-4">
	                      <h6 class="mb-0">Address</h6>
	                    </div>
	                    <div class="col-sm-8 text-secondary">
	                      ${entity.city}, ${entity.state}
	                    </div>
	              	</div>
	              </div>
	            <div class="tab-pane fade" id = "tab2">
		            <br>
		            <c:if test="${empty entity.dependents}">
						<h5 style="color:red">There are no dependents added here !</h5>
					</c:if>
					<c:if test="${not empty entity.dependents}">
			        	<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Sr.No.</th>
									<th scope="col">First Name</th>
									<th scope="col">Last Name</th>
									<th scope="col">Relationship</th>
									<th scope="col">Email</th>
									<th scope="col">Date of Birth</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${entity.dependents}">
									<tr>
										<td><c:out value="${list.id}" /></td>
										<td><c:out value="${list.firstname}" /></td>
										<td><c:out value="${list.lastname}" /></td>
										<td><c:out value="${list.relation}" /></td>
										<td><c:out value="${list.mail}" /></td>
										<td><c:out value="${list.date}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
			</div>
           </div>
		<br>
		<br>
       	
       	<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-scrollable modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Edit Details</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      	<form:form method="POST" action="updateProfile" modelAttribute="entity">
			      	<form:input type="hidden" path="holder_id"/>
			      	<div class="form-group">
		    			<label for="fname">FirstName</label>
		    			<form:input path="fname" class="form-control" id="first" placeholder="Enter FirstName" />
		  			</div>
		  			<div class="form-group">
		    			<label for="lname">LastName</label>
		    			<form:input path="lname" class="form-control" id="last" placeholder="Enter LastName" />
		  			</div>
		  			<div class="row">
		  				<div class="col-md-2">
			  				<label for="gender">Gender</label>
			  			</div>
			  			<div class="col-md-4">
				  			<form:radiobutton class="form-check-input" label="Male" path="gender" id="gen" value="Male" />
				  			<br>
				  			<form:radiobutton class="form-check-input" label="Female" path="gender" id="gen" value="Female"/>
						</div>
					</div>
					<div class="form-group">
		    			<label for="dobUk" class="control-label">Enter your date of birth</label>
	            		<form:input path="dob" type="date" name="dob" class="form-control"/>
		  			</div>
			  		<div class="form-group">
			    		<label for="contact">Contact Number</label>
			    		<form:input path="mobile" class="form-control" id="pnum" placeholder="Enter Phone Number" />
			  		</div>
					<div class="form-group">
		    			<label for="Inputcity">City</label>
		    			<form:input path="city" class="form-control" id="city" placeholder="Enter your City" />
		  			</div>
			  		<div class="form-group">
			    		<label for="Inputstate">State</label>
			    		<form:input path="state" class="form-control" id="state" placeholder="Enter State" />
			  		</div>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			        <button type="submit" class="btn btn-primary">Save changes</button>
			  	</form:form>
		      </div>
		    </div>
		  </div>
		</div>
       	
	<script>
		$(document).ready(function() {
			$("#myTab li a").click(function(){
				$(this).tab('show');
			}); // #tab1 will show
		});
	</script>
</body>
</html>