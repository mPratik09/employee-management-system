<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Support Page</title>
</head>
<body>

	<h2>Welcome to SUPPORT Page..</h2>
	<%@ include file="logout.jsp"%>

	${pendingUser}
	<table border="4">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Approve</th>
			<th>Reject</th>
		</tr>

		<c:forEach items="${pendingUser}" var="pendingUsers">
			<tr>
				<td>${pendingUsers.id}</td>
				<td>${pendingUsers.first_name}</td>
				<td>${pendingUsers.last_name}</td>
				<td>${pendingUsers.email}</td>
				<td><a href="">Accept</a></td>
				<td><a href="">Reject</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>