<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
   <style>
        *{box-sizing: border-box;}
        div{border: 1px solid black;}
        .container{width: 550px;height: 500px; margin: auto;}
        .name{width: 100%; text-align: center; height: 50px; line-height: 50px;}
        .con{width: 100% ;height:400px}
        textarea{widgh:100%;heigh:100%}
        .title1{width: 100%;height: 50px;}
        .title{width: 100%;height: 100%;text-align: center;line-height: 40px;}
   </style>
</head>
<body>
   <form action="write.board" method="post" enctype=multipart/form-data>
    <div class="container">
        <div class="name">자유게시판 글 쓰기</div>
        <div class="title1">
            <input type="text" class="title" name="title" placeholder="제목을 입력하세요">
        </div>
        <div class="con">
            <textarea id="summernote" name="contents"></textarea>
        </div>
        <div class="upload">
			<input type=file name="file" required><br>
        </div>
        <div class="btn">
            <input type="submit" value="등록">
            <input type="reset"  value="초기화">
            <a href="${pageContext.request.contextPath}/boardList.board?cpage=1"><button type="button">목록</button></a>
        </div>
        <!--<script>
            $('#summernote').summernote({
              tabsize: 2,
              height: 100
            });
          </script> -->
    </div>
    </form> 
</body>
</html>