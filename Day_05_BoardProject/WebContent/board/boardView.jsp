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
	$(document).on("click","#delete",function(){
		if(confirm("정말삭제?")){
			location.href="${pageContext.request.contextPath}/delete.comm?seq="+$(this).val();
		}else{
			return;
		}
	})
})
</script>
<style>
	*{box-sizing: border-box;}
     div{border: 1px solid black;}
     .container{width: 550px;height: 500px; margin: auto;}
     .main{height: 35px; text-align: center;line-height: 35px;}
     .navi{height: 40px; float: left;}
     .navi > div{float: left; height: 40px; line-height: 40px; text-align: center;}
     .seq{width: 30px;}
     .title{width: 250px;overflow:hidden;word-wrap:break-word;}
     .writer{width: 100px;}
     .date{width: 100px;}
     .count{width: 65px;}
     .contents{height: 365px;}  
     .file{height: 55px;overflow: scroll;}
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
            <div class="file">
            <c:forEach	var="item" items="${file }">
            	<a href ="files/${item.sysName}"> ${item.oriName} </a>
            </c:forEach>
            </div>
            <div class="footer">
                <c:choose>
                    <c:when test="${login.id == post.writer }">
                        <a href = "${pageContext.request.contextPath}/modify.board?post=${post.seq}"><input type="button" value="수정"></a>
                        <input type="button" id="del" value="삭제">
                    </c:when>
                </c:choose>
                  <a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button" id="list">목록</button></a>
                
                <form action="comment.comm" method="post">
                <div class="controller">
                    <textarea class="comment" name="comments">
                    </textarea>
                    <input type="hidden" value="${post.seq }" name="parent">
                    <div class="reply">
                        <input type="submit" id="btn" value="[댓글등록]">
                    </div>
                </div>
                </form>
                <!-- <form action="modify.comm" method="post">--> <!-- modify.comm -->
                 <c:forEach var = "item" items="${comment}">
                  <form action="modify.comm" method="post">
	                 <input type="hidden" value="${item.seq}" name="seq">  <!-- 댓글 번호 -->
                	작성자 : ${item.writer}
                	작성일 : ${item.writeDate}
                	<br>
                	내  용 : ${item.comments }
                	<input type="hidden" value="${item.comments }" name="comments2">
                	<c:if test="${login.id == item.writer }">
                	 	<button type="button" id="delete" value="${item.seq}">[삭제]</button>
                	 	<button id="modify">[수정]</button> <!--  수정을 누르면 댓글 번호 전송-->
                	 </c:if>
                	 <hr>
                	 </form>
                </c:forEach>
            </div>
    </div>
</body>
</html>