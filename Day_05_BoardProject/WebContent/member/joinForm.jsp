<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	window.onload = function(){
		document.getElementById("search").onclick = function() {
            new daum.Postcode({
                oncomplete: function(data) {
                    let roadAddr = data.roadAddress;
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("address1").value = roadAddr;               
                }
            }).open();
        }
		
		document.getElementById("ch").oninput = function(){
            let pw = document.getElementById("pw").value;
            let ch = document.getElementById("ch").value;
            if(pw != ch){
                val.innerHTML = "패스워드가 일치하지 않습니다.."
            }else{
             	val.innerHTML = "패스워드가 일치합니다." 
            }
        }		
    }
		$(function(){
			$("#check").on("click",function(){
				let id = $("#id").val();
				let idRegex = /^[A-Z][a-zA-Z0-9]{5, }$/;
				if(id == ""){
					alert("아이디를 입력하세요")
					return;
				}else{
					let resultid = idRegex.test(id);
					if(resultid){
						window.open("${pageContext.request.contextPath}/idCheck.mem?id=" + $("#id").val() ,"", "width=400,height=400" );
					}else{
						alert("아이디 형식을 확인하세요")
					}
				}
				
		}) 
	})
</script>

</head>
<body>
	<div class="container">
    	<form action="joinMember.mem" method="post">
        	<div class="form-row">
          		<div class="form-group col-md-6">
           		 <label for="inputEmail4">ID *첫글자는 대문자이고 나머지 글자는 영문자, 숫자로 총 5글자 이상만 가능*</label>
            	 <input type="text" class="form-control" id="id" name="id">
          		</div>
          		<div >
      				<button type="button" class="btn btn-outline-secondary" id="check">ID Check</button>
    			</div>
       	  	</div>
        	<div class="form-row">
          		<div class="form-group col-md-6">
            		<label for="pw">Password</label>
            		<input type="password" class="form-control" id="pw" name="pw">
          		</div>
        	</div>
        	<div class="form-row">
          		<div class="form-group col-md-6">
            		<label for="pw">Check Password</label>
            		<input type="password" class="form-control" id="ch">
            		<div id="val"></div>
          		</div>
        	</div>
        	<div class="form-row">
          		<div class="form-group col-md-6">
            		<label for="inputEmail4">Name</label>
            		<input type="text" class="form-control" id="name" name="name">
          		</div>
        	</div>
       		 <div class="form-row">
          		<div class="form-group col-md-6">
            		<label for="inputEmail4">Phone</label>
            		<input type="text" class="form-control" id="phone" name="phone">
          		</div>
        	</div>
        	<div class="form-row">
          		<div class="form-group col-md-6">
            		<label for="inputEmail4">Email</label>
            		<input type="text" class="form-control" id="email" name="email">
         		 </div>
        	</div>
        	<div class="form-row">
    			<div class="form-group col-md-6">
      				<label for="postcode">PostCode</label>
      				<input type="text" class="form-control" id="postcode" name="zipcode">
    			</div>
    			<div >
      				<button type="button" class="btn btn-outline-secondary" id="search">Find</button>
    			</div>
  			</div>
        	<div class="form-group">
          		<label for="address1">Address</label>
          		<input type="text" class="form-control" id="address1" name="address1">
        	</div>
        	<div class="form-group">
          		<label for="address2">Address 2</label>
          		<input type="text" class="form-control" id="address2" name="address2">
        	</div>
        	<input type="submit" class="btn btn-outline-secondary" value="Join">
        	<input type="reset" class="btn btn-outline-secondary" value="Reset">
      	</form>
      </div>	
</body>
</html>