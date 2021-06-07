<%@page import="DTO.PersonDTO"%>
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
		String modifyname = request.getParameter("modifyname");
		String modifycontact = request.getParameter("modicontact");
		
		PersonDAO dao = new PersonDAO();
		int result = dao.modify(new PersonDTO(Integer.parseInt(request.getParameter("modifyid")),modifyname,modifycontact,null));
		
		response.sendRedirect("PersonModify.jsp");
	%>
</body>
</html>