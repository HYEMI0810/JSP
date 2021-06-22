<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Translator</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
	fieldset{width:500px;height:200px};
	fieldset textarea{width:100%;height:100%}
</style>
</head>
<body>
	<fieldset>
		<legend>번역할 내용</legend>
		<textarea id="from" placeholder="번역할 한글을 입력하세요"></textarea>
	</fieldset>
	
	<hr>
	<button id="btn">번역</button><br>
	<script>
		$("#btn").on("click",function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/translator.tr",
				type:"get",
				data:{"text":$("#from").val()},
				dataType:"json"
			}).done(function(resp){
				$("#to").text(resp.message.result.translatedText);
			})
		})
	</script>
	
	<fieldset>
		<legend>번역된 내용</legend>
		<div id="to"></div>
	</fieldset>

</body>
</html>