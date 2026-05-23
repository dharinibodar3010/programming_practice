<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<h2>Student Login</h2>

	<form action="loginStudent" method="post">

		Email : <input type="email" name="email"> <br> <br>
		Password : <input type="password" name="password"> <br> <br>
		<input type="submit" value="Login">

	</form>

	<br>

	<a href="register">New Student Register</a>

</body>
</html>