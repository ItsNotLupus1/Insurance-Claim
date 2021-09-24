<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#uploadFiles").click(function() {
				
				$("#files").append('<div class="form-group"><label>Choose a file</label><input name="fileUpload" type="file" class="form-control-file" size="50"></div>')
				
			});
		});
</script>
<style>
#uploadFiles{
	border: 2px;
	padding: 9px 20px;
	margin: 8px 0;
	size: 120px;
	border-radius: 12px;
}
</style>
</head>
<body>
<%@include file="navbar.jsp"%>
	<div class="container">
		<br>
		<h2 align="center">Upload Medical Files</h2>
		<br>
        <br>
        <form:form method="post" action="doUpload" enctype="multipart/form-data">
        	<div id="files">
        		<div align = "right">
					<button class="btn btn-success" type="button" id="uploadFiles"><i class="fa fa-plus"></i> Add Fields</button>
				</div>
	            <div class="form-group">
	            	<label>Choose a file</label>
	            	<input name="fileUpload" type="file" class="form-control-file" size="50">
	            </div>
	            <div class="form-group">
	            	<label>Choose a file</label>
	            	<input type="file" name="fileUpload" class="form-control-file" size="50" >
	            </div>
	        </div>    
	        <div align = "center">
	            <input type="submit" class="btn btn-success" id="uploadFile"value="Upload" />
	            <input type="button" id="cancel" class="btn btn-danger" value="Cancel" />
	        </div>
	        <input type="hidden" name="holder_id" value="${FilesEntity.holder_id}"/>
        </form:form>
        
    </div>
</body>
</html>