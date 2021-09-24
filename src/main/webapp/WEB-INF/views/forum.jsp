<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<style>
	.myForm{
		background-color: buttonhighlight;
	}
</style>
<script>
	$(document).ready(function(){
		$(".myForm").hide();
		$("#commenting").click(function(){
			$("#commenting").fadeOut();
			$(".myForm").fadeIn(1000);
			$("#sub").hide();
			
			$(".form-signin #area").focus(function(){
				$("#sub").show();
			});

		});

		$("#cancel-commen").click(function(){
			$("#commenting").fadeIn(1500);
			$(".myForm").fadeOut();	
		});
	
// 		$("#reply").hide();
// 		$("#replyButton").click(function(){
// 			$("#reply").show();
// 		});
	});
</script>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<br>
		<h2>${posted.title}
			<c:if test="${entity.holder_id == posted.holder_id}"> 
				<a class="btn btn-primary" href="/updatePost/${posted.post_id}">Edit</a>
			</c:if>
		</h2>
		<small>Posted By <strong>${user.username}</strong></small>
		<hr>
		<p>${posted.desc}</p>
		<br>
		<h3>Comments <c:if test="${not empty user}"><button id="commenting" class="btn btn-primary"><i class="bi bi-file-plus"></i> Add Comment</button></c:if></h3>	
		<br>
		<c:if test="${empty posted.comments}">
			<h5 style="color:red">There are no comments</h5>
		</c:if>
		<c:forEach var="list" items="${posted.comments}" varStatus="loop">
		<div class="panel panel-default">
			<ul class="list-group list-custom-corner">
				<li class="list-group-item side-borderless last-border">
						<div class="media">
							<div class="media-body">
								<div class="media-heading group">
									<h5>${list.holder.username}</h5> 
								</div>
								<p>${list.comment}</p>
<!--								<button id="replyButton">Reply</button> -->
<!-- 								<div id="reply"> -->
<%-- 									<form:form action="postReply" method="POST" modelAttribute="reply"> --%>
<%-- 										<form:input path="reply_sen" class="form-control" /> --%>
<%-- 										<form:input type="hidden" path="post_id" value="${posted.post_id}" /> --%>
<%-- 										<form:input type="hidden" path="comment_id" value="${list.comment_id}" /> --%>
<!-- 										<br> -->
<!-- 										<div align="right"> -->
<!-- 											<input type="submit" value="submit"> -->
<!-- 										</div> -->
<%-- 									</form:form> --%>
<!-- 								</div> -->
								
							</div>
						</div>
				</li>
			</ul>
		</div>
		</c:forEach>
		<br>
		<div class="myForm container">
			<br>
			<form:form class="form-signin" action="/comment" method="POST" modelAttribute="com">
				<form:textarea id="area" path="comment" placeholder="Enter your comment" class="form-control" />
				<form:input type="hidden" path="post_id" value="${posted.post_id}" />
				<br>
				<div id="sub" align="right">
					<input type="submit" value="Submit" class="btn btn-success">
					<input type="button" id="cancel-commen" value="Cancel" class="btn btn-danger"> 
				</div>
			</form:form>
			<br>
		</div>
		<br>
	</div>	
</body>
</html>