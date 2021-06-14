<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
	*{box-sizing: border-box;}
     div{border: 1px solid black;}
     .container{width: 550px;height: 500px; margin: auto;}
     .main{height: 35px; text-align: center;line-height: 35px;}
     .navi{height: 40px; float: left;}
     .navi > div{float: left; height: 40px; line-height: 40px; text-align: center;}
     .seq{width: 30px;}
     .title{width: 250px;}
     .writer{width: 100px;}
     .date{width: 100px;}
     .count{width: 65px;}
     .contents{height: 420px;}  
     .footer{height: 45px; line-height: 45px;}    
     .controller{width: 550px; height: 50px; margin: 10px;}
     .comment{float: left; width: 450px; height: 100%; textalign: left}
     .reply{float: left; width: 98px; height: 100%;}
     #btn{width: 100%; height: 100%;} 	  
</style>
</head>
<body>
	<div class="container">
            <div class="main"><b>자유게시판</b></div>
            <div class="navi">
                <div class="seq" id="seq">${post.seq}</div>
                <div class="title">${post.title}</div>
                <div class="writer">${post.writer }</div>
                <div class="date">${post.writeDate }</div>
                <div class="count">${post.viewCount }</div>
            </div>
            <div class="contents">${post.contents }</div>
            <div class="footer">
                <c:choose>
                    <c:when test="${login.id == post.writer }">
                        <a href = "${pageContext.request.contextPath}/modify.board?post=${post.seq}"><input type="button" value="수정"></a>
                        <input type="button" id="del" value="삭제">
                        <a href="pageContext.request.contextPath}/boardList.board?cpage=1"><input type="button" value="목록"></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button" id="list">목록</button></a>
                    </c:otherwise>
                </c:choose>

                <form action="modifyProc.comm" method="post">
                <div class="controller">
                	<input type="hidden" value="${seq3}" name="seq3">
                    <textarea class="comment" name="comments1">${comments }
                    </textarea>
                    <div class="reply">
                        <input type="submit" id="btn" value="[완료]">
                    </div>
                </div>
                </form>
           
            </div>
    </div>
</body>
</html>