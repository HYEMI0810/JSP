<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
$(function(){
	$("#del").on("click",function(){
		location.href="delete.board?board="+$("#seq").val();
	})
})
</script>
</head>
<body>
	<table border="1" align="center">
	<form action="" method="post">
        <tr>
            <td colspan="2" align="center" width=700>자유게시판</td>
        </tr>
        <tr>
            <td>${post.seq}</td>
            <input type = hidden id =seq value="${post.seq}">
            <td>${post.title}</td>
            <td>${post.writer }</td>
            <td>${post.writeDate }</td>
            <td>${post.viewCount }</td>
        </tr>
        <tr>
            <td colspan="2"><textarea cols="100" rows="20">${post.contents }</textarea></td>
        </tr>
        <tr>
            <td colspan="2" align="right">
            <c:choose>
            	<c:when test="${login.id == post.writer }">
           			<input type="submit" value="수정">
           			<button type="button" id="del">삭제</button>
           			<button type="button">목록</button></td>
           		 </c:when>
           		 <c:otherwise>
            		<button type="button">목록</button></td>
            	 </c:otherwise>
            </c:choose>
        </tr>
	</form>
    </table>
</body>
</html>