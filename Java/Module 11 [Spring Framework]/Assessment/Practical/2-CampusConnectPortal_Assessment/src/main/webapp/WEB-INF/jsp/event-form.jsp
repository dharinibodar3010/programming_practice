<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Event</title>
</head>
<body>

	<h2>Create Event</h2>

	<form action="saveEvent" method="post">

		Event Title : <input type="text" name="title">
		<form:errors path="title" />
		<br>
		<br> Description : <input type="text" name="description">
		<form:errors path="description" />
		<br>
		<br> Event Date : <input type="date" name="date">
		<form:errors path="date" />
		<br>
		<br> <input type="submit" value="Save Event">

	</form>

	<br>

	<a href="dashboard">Back To Dashboard</a>

</body>
</html>