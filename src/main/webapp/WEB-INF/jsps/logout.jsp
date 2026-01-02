<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>
/* .logout-btn {
	padding: 8px 16px;
	background-color: red;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	border: none;
	cursor: pointer;
} */
* {
	margin: 1px;
	padding: 1px;
}

.logout-btn {
	float: right;
}
</style>


</head>
<body>

	<form action="logout" method="post" style="display: inline;">
		<button type="submit" class="logout-btn">Logout</button>
	</form>

</body>
</html>