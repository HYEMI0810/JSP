<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<script>
	<c:choose>
		<c:when test="${modi_result > 0}">
			alert("수정완료");
			location.href="Index.jsp";
		</c:when>
		<c:otherwise>
			return;
		</c:otherwise>
	</c:choose>
</script>

</body>
</html>