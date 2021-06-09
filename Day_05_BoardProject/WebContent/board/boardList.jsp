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
	 <table border="1" align="center">
        <tr>
            <td align="center" colspan="5">자유게시판</td>
        </tr>
        <tr>
            <td width=10></td>
            <td width = 300 align="center">Title</td>
            <td width = 100 align="center">Writer</td>
            <td width = 100 align="center">Date</td>
            <td width = 50 align="center">View</td>
        </tr>
        <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.seq}</td>
            <td><a href = "${pageContext.request.contextPath}/post.board?post=${item.seq}">${item.title}</a></td>
            <td>${item.writer }</td>
            <td>${item.writeDate }</td>
            <td>${item.viewCount }</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="5" align="center">1 2 3 4 5 6 7 8 9 10</td>
        </tr>
        <tr>
            <td colspan="5" align="right">
            <a href="${pageContext.request.contextPath}/board/write.jsp"><button>글쓰기</button></a>
            <a href="index.jsp"><button>Back</button></a></td>
        </tr>

    </table>
</body>
</html>