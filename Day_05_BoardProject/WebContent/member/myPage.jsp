<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<body>
	<table border="1" align="center">
		<tr>
			<td>ID</td>
			<td>${list.id }</td>
		</tr>
		<tr>
			<td>Name</td>
			<td>${list.name }</td>
		</tr>
		<tr>
			<td>Phone</td>
			<td>${list.phone }</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${list.email }</td>
		</tr>
		<tr>
			<td>zipcode</td>
			<td>${list.zipcode }</td>
		</tr>
		<tr>
			<td>Address1</td>
			<td>${list.address1 }</td>
		</tr>
		<tr>
			<td>Address2</td>
			<td>${list.address2 }</td>
		</tr>
		<tr>
			<td>reg_Date</td>
			<td>${list.reg_date }</td>
		</tr>
		<tr>
			<td colspan="2"><a href="Index.jsp"><button class="btn btn-outline-secondary">Back</button></a></td>
		</tr>
	</table>
</body>
</html>