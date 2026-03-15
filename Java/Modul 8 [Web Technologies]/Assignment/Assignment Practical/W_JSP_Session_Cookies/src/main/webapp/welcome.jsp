<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

<html>
<head>
<title>Welcome</title>
</head>

<body>

	<%
	
	if(session==null || session.getAttribute("username")==null)
	{
	response.sendRedirect("index.jsp");
	return;
	}
	
	String user=(String)session.getAttribute("username");
	
	%>
	
	<h2>Welcome <%=user%></h2>
	
	<%
	
	Cookie cookies[]=request.getCookies();
	
	if(cookies!=null){
	for(Cookie c:cookies){
	if(c.getName().equals("username")){
	%>
	
	<p>Cookie User: <%=c.getValue()%></p>
	
	<%
	}
	}
	}
	%>
	
	<br>
	<a href="LogoutServlet">Logout</a>

</body>
</html>