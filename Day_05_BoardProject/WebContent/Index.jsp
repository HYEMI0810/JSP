<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script>
$(function(){
	$("#join").on("click",function(){
		location.href="joinForm.mem";
	})
	$("#logout").on("click",function(){
		alert("로그아웃 되었습니다!")
		location.href="logout.mem";
	})
	$("#mypage").on("click",function(){
		location.href="myPage.mem"
	})
	$("#quit").on("click",function(){
		if(confirm("정말탈퇴?")){
			location.href="quit.mem"
		}else{
			return;
		}
	})
	$("#update").on("click",function(){
		location.href="update.mem"
	})
	$("#board").on("click",function(){
		location.href="boardList.board?cpage=1"
	})
})


</script>
</head>
<body>
	<c:choose>
		<c:when test="${login != null }">
			<br>
			<b>Welcome ${login.name}!</b><br><br>
			<button type="button" class="btn btn-outline-secondary btn-lg" id="mypage">MyPage</button>
			<button type="button" class="btn btn-outline-secondary btn-lg" id="board">Board</button>
			<button type="button" class="btn btn-outline-secondary btn-lg" id="update">Update</button>
			<button type="button" class="btn btn-outline-secondary btn-lg" id="logout">Logout</button>
			<button type="button" class="btn btn-outline-secondary btn-lg" id="quit">MemberOut</button><br>
		</c:when>
		<c:otherwise>
		<form action="login.mem" method=post class="needs-validation" novalidate>
			<div class="container">   
  				<div class="form-row">
    				<div class="col-md-3 mb-3">
      					<label for="validationCustom01">ID</label>
      					<input type="text" class="form-control" id="validationCustom01" name =id>
      				<div class="valid-feedback"></div>      			
    				</div>
    				<div class="col-md-3 mb-3">
      					<label for="validationCustom02">PassWord</label>
      					<input type="password" class="form-control" id="validationCustom02" name=pw>
     				 	<div class="valid-feedback"></div>
    				</div>
  				</div>
  				<input class="btn btn-outline-secondary" type="submit" value="Login">
  				<button type="button" class="btn btn-outline-secondary" id="join">Join</button>
			</div> 
		</form>
		</c:otherwise>
	</c:choose>
</body>
</html>