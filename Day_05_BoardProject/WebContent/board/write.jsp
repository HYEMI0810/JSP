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
	function(){
		$("#id").on("click",function(){
			location.href="boardList.board"
		})
	}
</script>
</head>
<body>
	<table border="1" align="center">
	<form action="write.board" method="post">
        <tr>
            <td colspan="2" align="center" width=700>자유게시판 글 쓰기</td>
        </tr>
        <tr>
            <td width=100 align="center" colspan="2">
            <input type="text" placeholder="제목을 입력하세요" size=70 name="title"></td>
        </tr>
        <tr>
            <td colspan="2"><textarea cols="100" rows="20" placeholder="내용을 입력하세요." name="contents"></textarea></td>
        </tr>
        <tr>
            <td colspan="2" align="right">
            <input type="submit" value="등록">
            <input type="reset"  value="초기화">
            <a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button" id="list">목록</button></a>
        </tr>
	</form>
    </table>
</body>
</html>