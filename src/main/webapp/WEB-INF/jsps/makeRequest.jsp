<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<form action="${pageContext.request.contextPath}/makeRequest" method="post">
		<table border="2">
			<c:forEach var="dept" items="${departmentsList}">
				<tr>
					<td><input type="radio" name="departId" value="${dept.id}"><label>
							${dept.department} </label></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Send Request"
					style="float: right"></td>
			</tr>
		</table>
	</form>
</body>
</html>