<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function(){
		$.ajax({
			url:"list.file",
			dataType:"json"
		}).done(function(resp){
			for(let i = 0; i < resp.length; i++){
				let line = $("<div>");
				
				let link = $("<a>");
				link.attr("href","files/" + resp[i].sysName);
				link.append(resp[i].oriName);
				
				line.append(link);
				$(".file-box").append(line);
			}
		})
	})
</script>
</head>
<body>
	<form action="upload.file" method=post enctype=multipart/form-data>
		<input type=text name="message"><br>
		<input type=file name="file" required><br>
		<input type=submit>
	</form>
	
	<fieldset class="file-box">
		<legend>File-List</legend>
	</fieldset>

</body>
</html>