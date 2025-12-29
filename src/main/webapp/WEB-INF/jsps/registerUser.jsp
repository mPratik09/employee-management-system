<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Form</title>
</head>
<body>
	<form action="saveUser" method="post">
		<table border="5">
			<tr>
				<td><label>First Name:</label></td>
				<td><input type="text" name="firstName"
					placeholder="Please Enter your user name here.."
					style="width: 80%;"></td>
			</tr>
			<tr>
				<td><label>Last Name:</label></td>
				<td><input type="text" name="lastName"
					placeholder="Enter your last name here.."></td>
				</td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input type="text" name="email"
					placeholder="Please Enter your password here.."></td>
				</td>
			</tr>
			<tr>
				<td><label>Paswword:</label></td>
				<td><input type="password" name="password"
					placeholder="Please Enter your password.."></td>
				</td>
			</tr>
			<tr>
				<td><label>Re-type Password:</label></td>
				<td><input type="password" name="reEnterPassword"
					placeholder="Re-Enter your password.."></td>
				</td>
			</tr>
			<tr> 
				<td><label>Contact Num:</label></td>
				<td><input type="text" name="contactNum"
					placeholder="Enter your contact number.."></td>
				</td>
			</tr>
			<tr> 
				<td><input type="radio"name="admin">ADMIN</td>
				<td><input type="radio"name="employee">EMPLOYEE</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="Submit"
					value="Proceed" />
			</tr>
		</table>
	</form>

	<label>Don't have an account??</label>
	<br />
	<a href="/register">Click here to Register..</a>
	${error}
	
</body>
</html>