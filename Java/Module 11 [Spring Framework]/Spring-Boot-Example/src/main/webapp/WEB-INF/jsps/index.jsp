<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
	background:#f4f6f9;
}

.main-box{
	margin-top:50px;
}

.card{
	border:none;
	border-radius:15px;
	box-shadow:0px 0px 15px rgba(0,0,0,0.1);
}

.card-header{
	border-radius:15px 15px 0px 0px !important;
}

.table th{
	background:black;
	color:white;
}

</style>

</head>

<body>

<div class="container main-box">

	<div class="row">

		<!-- FORM SECTION -->
		<div class="col-md-4">

			<div class="card">

				<div class="card-header bg-primary text-white">
					<h3 class="text-center">WELCOME</h3>
				</div>

				<div class="card-body">

					<span class="text-success">${msg}</span>

					<form:form action="reg" modelAttribute="user" method="post">

					<form:hidden path="id"/>

					<div class="mb-3">
						<form:label path="uname" class="form-label">Username</form:label>
						<form:input path="uname" class="form-control"/>
					</div>

					<div class="mb-3">
						<form:label path="email" class="form-label">Email</form:label>
						<form:input path="email" class="form-control"/>
					</div>

					<div class="mb-3">
						<form:label path="password" class="form-label">Password</form:label>
						<form:input path="password" class="form-control"/>
					</div>

					<input type="submit" class="btn btn-primary w-100">

					</form:form>

				</div>

			</div>

		</div>

		<!-- TABLE SECTION -->
		<div class="col-md-8">

			<div class="card">

				<div class="card-header bg-dark text-white">
					<h3 class="text-center">User Details</h3>
				</div>

				<div class="card-body">

					<table class="table table-bordered table-hover">

					<tr>
						<th>Id</th>
						<th>Username</th>
						<th>Email</th>
						<th>Password</th>
						<th colspan="2">Action</th>
					</tr>

					<c:forEach var="dt" items="${users}">

					<tr>

					<td>${dt.getId()}</td>
					<td>${dt.getUname()}</td>
					<td>${dt.getEmail()}</td>
					<td>${dt.getPassword()}</td>

					<td>
						<a href="delete?did=${dt.getId()}"
						   class="btn btn-danger btn-sm">
						   Delete
						</a>
					</td>

					<td>
						<a href="edit?eid=${dt.getId()}"
						   class="btn btn-warning btn-sm">
						   Edit
						</a>
					</td>

					</tr>

					</c:forEach>

					</table>

				</div>

			</div>

		</div>

	</div>

</div>

</body>
</html>