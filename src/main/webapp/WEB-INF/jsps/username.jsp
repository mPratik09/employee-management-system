<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Name</title>


<style>
body {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

footer {
	margin-top: auto;
	background: #333;
	color: white;
	text-align: center;
	padding: 10px;
}
</style>


</head>
<body>
	<footer> 
		${pageContext.request.userPrincipal.name}
	</footer>
</body>
</html>