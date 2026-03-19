<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>It Console</title>
</head>
<body>

	<%@ include file="logout.jsp"%>

	<h2>Welcome to the Information Technology Emp. List</h2>
	<br>
	<br>
	<table border="4">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Status</th>

		</tr>

		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>

		<c:forEach items="${employeesList}" var="userfromList">
			<tr>
				<td>${userfromList.id}</td>
				<td>${userfromList.firstName}</td>
				<td>${userfromList.lastName}</td>
				<td>${userfromList.email}</td>
				<td>${userfromList.status}</td>
			</tr>
		</c:forEach>

	</table>

	<%@ include file="username.jsp"%>
</body>
</html>