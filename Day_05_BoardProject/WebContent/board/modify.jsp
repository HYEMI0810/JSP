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
     .title1{width: 250px;}
     .writer{width: 100px;}
     .date{width: 100px;}
     .count{width: 65px;}
     .con{height: 420px;}  
     .footer{height: 45px; line-height: 45px;}       
</style>
</head>
<body>
	<div class="container">
        <form action="${pageContext.request.contextPath}/modifyProc.board" method="POST">
            <div class="main"><b>자유게시판</b></div>
            <div class="navi">
                <div class="seq">
                <input type="hidden" name = "seq" value = "${post.seq}"></div>
                <div class="title1">
                <textarea cols="30" name="title">${post.title}</textarea></div>
                <div class="writer">${post.writer }</div>
                <div class="date">${post.writeDate }</div>
                <div class="count">${post.viewCount }</div>
            </div>
            <div class="con">
            <textarea cols="72" rows="25" name="contents">${post.contents }</textarea>
            </div>
            <div class="footer">
            	<input type="submit" value="완료">
                <a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button" id="list">목록</button></td></a>
            </div>
        </form>
    </div>
</body>
</html>