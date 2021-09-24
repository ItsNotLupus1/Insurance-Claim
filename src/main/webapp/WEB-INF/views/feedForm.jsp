<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container" >
	    <h1>Add XML RSS Feed</h1>
	    <h2><i>${message}</i></h2>
	    <div class="jumbotron">
	        <form:form method="POST" action="addForm" modelAttribute="feed">
	            <div>
	
	                <form:label path="url">XML RSS Feed url: </form:label>
	            </div>
	            <div>
	                <form:input path="url" class="form-control" placeholder="https://www.15min.lt/rss" type="url" maxlength="255"
	                            pattern="https://.*"/>
	            </div>
	
	            <div>
	                <form:label path="feed_name">XML RSS Feed name: </form:label>
	            </div>
	            <div>
	                <form:input path="feed_name" placeholder="Feed name 4-20 length" minlength="4" maxlength="20"/>
	            </div>
	            <br/>
	            <div>
	                <div>
	                    <input type="submit" value="Add Feed" class="btn btn-primary"/>
	                </div>
	            </div>
	        </form:form>
	    </div>
	</div>
</body>
</html>