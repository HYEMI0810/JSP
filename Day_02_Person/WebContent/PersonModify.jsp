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
	<form action=PersonModifyProc.jsp method=get>
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
					<td><input type=radio name=modifyid value=<%=dto.getId() %>>
					<td><%=dto.getName() %>
					<td><%=dto.getContact() %>
					<td><%=dto.getReg_date() %>
				</tr>
			<%
		}
	%>
	<tr>
		<td align = center colspan=4>		
		<input type=text name=modifyname placeholder=NAME>
		<input type=text name=modicontact placeholder=CONTACT>
		<input type=submit value=Modify>
		<input type=reset value=Reset>
		</form>
	</tr>
	<tr>
	<td align = center colspan=4>
	<a href="PersonIndex.html"><button>HOME</button></a>
	</tr>
	</table>
</body>
</html>