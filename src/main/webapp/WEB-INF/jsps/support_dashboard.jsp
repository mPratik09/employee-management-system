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

	<br />
	<br /> ${user.status}
	<br />
	<br /> ${user.id} ${user.firstName} ${user.lastName} ${user.email}
	${user.contactNum}
	<br />
	<br />${user}
	<br />
	<br />
	<br />


	<table border="4">
		<tr>
			<th>Id</th>
			<th>Emp Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Depart Id</th>
			<th>Requested For</th>
			<th>Status</th>
			<th>Approve</th>
			<th>Reject</th>
			<th>Update</th>
		</tr>

		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>

		<c:forEach items="${usersList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.empId}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td>${user.departId}</td>
				<td>${user.department}</td>
				<td>${user.status}</td>
				<td>
					<form action="approveReq" method="post">
						<input type="hidden" name="empId" value="${user.empId}">
						<input type="hidden" name="departId" value="${user.departId}">
						<button type="submit" name="role">APPROVE</button>
					</form>
				</td>
				<td>
					<form action="roleAssign" method="post">
						<input type="hidden" name="id" value="${user.id}">
						<button type="submit" name="role">REJECT</button>
					</form>
				</td>
				<td>
					<form action="updateEmp" method="post">
						<input type="hidden" name="empId" value="${user.empId}">
						<button type="submit">UPDATE</button>
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>