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
            <td width=10></td>
            <td width = 300 align="center">Title</td>
            <td width = 100 align="center">Writer</td>
            <td width = 100 align="center">Date</td>
            <td width = 50 align="center">View</td>
        </tr>
        <c:forEach var="item" items="${list}">
        <tr>
			<td>${item.seq }
			<td><a href = "${pageContext.request.contextPath}/post.board?post=${item.seq}">${item.title}</a>
			<td>${item.writer }
			<td>${item.writeDate }
			<td>${item.viewCount }
		</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
            	<c:forEach var="i" items="#{navi }" varStatus="s">
            		<c:choose>
            			<c:when test="${i == '>'}">
            				<a href = "${pageContext.request.contextPath}/boardList.board?cpage=${navi[s.index -1]+1}">${i}</a>
            			</c:when>
            			<c:when test="${i == '<'}">	
            				<a href = "${pageContext.request.contextPath}/boardList.board?cpage=${navi[s.index +1]-1}">${i}</a>
            			</c:when>
            			<c:otherwise>
            				<a href = "${pageContext.request.contextPath}/boardList.board?cpage=${i}">${i}</a>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
            	<a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button">Back</button></a>
            </td>
		</tr>
        
	</table>
</body>
</html>