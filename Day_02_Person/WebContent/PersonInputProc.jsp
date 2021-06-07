<%@page import="DAO.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		
		PersonDAO dao = new PersonDAO();
		int result = dao.insert(name, contact);
	%>
	<script>
		if(<%=result%> > 0){
			alert("Success");
		}else{
			alert("Failed");
		}
		location.href="PersonIndex.html";
	</script>

</body>
</html>