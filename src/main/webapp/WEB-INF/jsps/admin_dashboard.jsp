<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>

	<%@ include file="logout.jsp"%>

	<h2>Welcome to ADMIN Page</h2>

	<br />
	<br />
	<h2>Registered as an Admin</h2>

	<br />
	<br />
	<br />

	<table border="4">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
		</tr>

		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>

		<c:forEach items="${usersList}" var="userfromList">
			<tr>
				<td>${userfromList.id}</td>
				<td>${userfromList.firstName}</td>
				<td>${userfromList.lastName}</td>
				<td>${userfromList.email}</td>
			</tr>
		</c:forEach>

	</table>

	<%@ include file="username.jsp"%>
</body>
</html>