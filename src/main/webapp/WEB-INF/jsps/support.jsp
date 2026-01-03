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

	<table border="4">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Approve</th>
			<th>Reject</th>
		</tr>

		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>

		<c:forEach items="${pendingUser}" var="pendingUsers">
			<tr>
				<td>${pendingUsers.id}</td>
				<td>${pendingUsers.firstName}</td>
				<td>${pendingUsers.lastName}</td>
				<td>${pendingUsers.email}</td>
	<!-- 			
				<td>
					<form action="roleAssign" method="post">
						<button type="submit">ADMIN</button>
					</form>
				</td>
				<td>
					<form action="roleAssign" method="post">
						<button type="submit">EMPLOYEE</button>
					</form>
				</td>
	 -->			
				<td><a href="roleAssign?id=${pendingUsers.id}">ADMIN</a></td>
				<td><a href="roleAssign?id=${pendingUsers.id}">EMPLOYEE</a></td>
				
			</tr>
		</c:forEach>

	</table>
</body>
</html>