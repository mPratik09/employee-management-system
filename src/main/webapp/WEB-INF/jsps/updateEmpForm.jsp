<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee</title>
</head>
<body>

	<h2>Update Employee</h2>

	${updateEmployee}

	<form action="saveUpdatedEmp" method="post">
		<table border="2">
		<tr>
				<td>First Name:</td>
				<td><input type = "text" name="firstName" value="${updateEmployee.firstName}"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type = "text" name="lastName" value="${updateEmployee.lastName}"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type = "text" name="email" value="${updateEmployee.email}"></td>
			</tr>
			<tr>
				<td>Contact Num:</td>
				<td><input type = "text" name="contactNum" value="${updateEmployee.contactNum}"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="id" value="${updateEmployee.id}">
					<input type="Submit" value="Update" style="float:right">
				</td>
			</tr>
		</table>
	</form>
	<br/><br/>
	${updateEmployee.id}
</body>
</html>