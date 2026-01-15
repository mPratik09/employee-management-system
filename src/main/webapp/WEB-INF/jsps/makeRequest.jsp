<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="logout.jsp"%>

	<h2>Logged In Successfully</h2>
	<br />
	<br />
	<h4>Welcome ${email}</h4>
	<br /> ${user.status}
	<br />
	<br />
	<br /> ${user.id} ${user.firstName} ${user.lastName} ${user.email}
	${user.contactNum} 
	<br />
	<br />${user}
	<br />

	<form action="makeRequest" method="post">
		<input type="hidden" name="user_id" value="${user.id}">

		<table border="2">
			<tr>
				<td><input type="checkbox" name="depart_code" value="ADM_01">
					<label>Admin</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="depart_code" value="SPRT_02">
					<label>Support</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="depart_code" value="HR_03">
					<label>Human Resource</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="depart_code" value="IT_04">
					<label>Information Technology</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="depart_code" value="FIN_05">
					<label>Finance</label></td>
			</tr>
			<tr>
				<td><input type="submit" value="Send Request"
					style="float: right"></td>
			</tr>
		</table>
	</form>
</body>
</html>