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
	String[] delid = request.getParameterValues("delid");
	PersonDAO dao = new PersonDAO();
	for(int i = 0; i < delid.length; i++){
		String str = delid[i];
		dao.delete(str);
	}
%>
<script>
	location.href="PersonDelete.jsp";
</script>
</body>
</html>