<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Id Check Result</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<!--  <script>
	$(function(){		
		$("#cancel").on("click",function(){
			opener.document.getElementById("id").value= "";
			window.close();
		})
		$("#close").on("click",function(){
			opener.document.getElementById("id").value= "";
			window.close();
		})
		
		$("#use").on("click",function(){
			window.close();
		})

	})
</script>
</head>
<body>
	<c:choose>
		<c:when test="${result}">
			<div class="alert alert-secondary">
  				이미 사용중인 ID입니다.<br>
  				<button id="close" class="btn btn-outline-secondary">Close</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-secondary">
  				사용 가능한 ID입니다.<br>
  				<button id="use" class="btn btn-outline-secondary">Use</button>
				<button id ="cancel" class="btn btn-outline-secondary">Cancel</button>
			</div>
		</c:otherwise>
	</c:choose>-->

</body>
</html>