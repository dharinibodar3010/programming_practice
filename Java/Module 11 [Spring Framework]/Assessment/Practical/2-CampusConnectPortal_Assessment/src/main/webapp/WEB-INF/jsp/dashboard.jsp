<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>

	<h2>Upcoming Events</h2>

	<a href="eventForm">Create Event</a> &nbsp;&nbsp;&nbsp;

	<a href="logout">Logout</a>

	<hr>

	<table border="1" cellpadding="10">

		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Description</th>
			<th>Date</th>
		</tr>

		<c:forEach var="e" items="${events}">

			<tr>
				<td>${e.id}</td>
				<td>${e.title}</td>
				<td>${e.description}</td>
				<td>${e.date}</td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>