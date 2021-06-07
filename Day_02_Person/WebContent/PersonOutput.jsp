<%@page import="DTO.PersonDTO"%>
<%@page import="java.util.List"%>
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
	<table border=1 align=center>
	<tr>
		<th colspan=4>Person List
	</tr>
	<tr>
		<th>SEQ
		<th>WRITER
		<th>MESSAGE
		<th>WIRTE_DATE
	</tr>
	<%
		PersonDAO dao = new PersonDAO();	
		List<PersonDTO> list = dao.selectAll();
		
		for(PersonDTO dto : list){
			%>
				<tr>
					<td><%=dto.getId() %>
					<td><%=dto.getName() %>
					<td><%=dto.getContact() %>
					<td><%=dto.getReg_date() %>
				</tr>
			<%
		}
	%>
	<tr>
	<td align = center colspan=4>
	<a href="PersonIndex.html"><button>HOME</button></a>
	</tr>
	</table>
</body>
</html>