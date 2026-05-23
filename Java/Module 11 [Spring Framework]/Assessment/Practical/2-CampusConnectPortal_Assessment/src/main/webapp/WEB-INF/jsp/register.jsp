<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>

	<h2>Student Registration</h2>

	<form action="saveStudent" method="post">

		Name : <input type="text" name="name"> <br>
		<br> Email : <input type="email" name="email"> <br>
		<br> Password : <input type="password" name="password"> <br>
		<br> <input type="submit" value="Register">

	</form>

	<br>

	<a href="login">Back To Login</a>

</body>
</html>