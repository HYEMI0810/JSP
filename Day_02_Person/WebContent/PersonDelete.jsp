<%@page import="DTO.PersonDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>.</title>
</head>
<body>
	<table border=1 align=center>
	<form action=PersonDeleteProc.jsp method=get>
	<tr>
		<th colspan=4>Person List
	</tr>
	<tr>
		<th>SEQ
		<th>NAME
		<th>CONTACT
		<th>REG_DATE
	</tr>
	<%
		PersonDAO dao = new PersonDAO();	
		List<PersonDTO> list = dao.selectAll();
		
		for(PersonDTO dto : list){
			%>
				<tr>
					<td><input type=checkbox name=delid value=<%=dto.getId() %>>
					<td><%=dto.getName() %>
					<td><%=dto.getContact() %>
					<td><%=dto.getReg_date() %>
				</tr>
			<%
		}
	%>
	<tr>
		<td align = center colspan=4>		
		<input type=submit value="Delete">
		<a href="PersonIndex.html"><button type="button">HOME</button></a>
		</form>
	</tr>

	</table>
</body>
</html>