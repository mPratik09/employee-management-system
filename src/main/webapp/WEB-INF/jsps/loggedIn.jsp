<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Logged In Successfully</h2>

	${msg}
	<h2>Make a request..</h2>

	${user.id} ${user.firstName} ${user.lastName} ${user.email}
	${user.contactNum}


	${user}

	<table>
		<tr>
			<td>
				<form action=assignRole method="post">
<!-- 					<input type="hidden" name="id"> -->
<%-- 					<input type="hidden" name="id"  value="${user.id}"> --%>
					<input type="hidden" name="user"  value="${user}">
					<button type="submit">As an Admin??</button>
				</form>
			</td>
			<td>
				<form action="assignRole" method="post">
					<input type="hidden" name="id" value="${user.id}">
					<button type="submit">As an Employee??</button>
				</form>
			</td>
	</table>
</body>
</html>