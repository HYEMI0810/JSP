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
            <td>${item.writer}</td>
            <td>${item.writeDate}</td>
            <td>${item.viewCount }</td>
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
            	<a href="${pageContext.request.contextPath}/board/write.jsp"><button>글쓰기</button></a>
            	<a href="Index.jsp"><button type="button">Back</button></a>
            </td>
        </tr>
        <tr>
        	<form action="search.board" method="post">
            	<td colspan="5" align="right">
            		<select name=search>
            			<option>제목</option>
            			<option>내용</option>
            			<option>작성자</option>
            			<option>제목 + 내용</option>
            		</select>
            		<input type="text" name="text" placeholder="검색어를 입력하세요">
            		<input type = "hidden" name ="cpage" value =1>
            		<button>[검색]</button>
            	</td>
            </form>
        </tr>

    </table>
</body>
</html>