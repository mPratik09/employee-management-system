<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee</title>
</head>
<body>
	<%@ include file="logout.jsp" %>
	<h2>Welcome to EMPLOYEE Page</h2>
	
	${user.role}
	<br />
	<br />
	<br />
	<br />
	${user.id} ${user.firstName} ${user.lastName} ${user.email}
	${user.contactNum}

	
</body>
</html>