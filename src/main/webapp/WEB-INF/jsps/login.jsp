<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Here</title>
</head>
<body>
	<form action="/employee-management-system/login" method="post">
		<table border="2">
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password">
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>

	${error}

	<br/><br/><br/>
	<label>Don't have an account??</label>
	<br />
	<a href="registerUser">Click here to Register..</a>


</body>
</html>