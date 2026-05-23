<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>

<body>

	<h2>Student Event Management System</h2>

	<form action="loginStudent" method="post">

		<table border="1" cellpadding="15">

			<tr>
				<td>Email</td>
				<td><input type="email" name="email"></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>

			<tr>

				<td align="center"><input type="submit" value="Login">
				</td>

				<td align="center"><a href="register">Register</a></td>

			</tr>

		</table>

	</form>

</body>
</html>