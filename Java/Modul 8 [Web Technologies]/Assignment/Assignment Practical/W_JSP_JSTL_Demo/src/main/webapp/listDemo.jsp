<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP List Demo</title>
</head>
<body>

<h2>List Demo with JSTL</h2>

<%
    // Scriplet: create a list of names
    java.util.List<String> names = new java.util.ArrayList<>();
    names.add("Alice");
    names.add("Bob");
    names.add("Charlie");

    // Set list in request scope to access via JSTL
    request.setAttribute("namesList", names);

    // Using implicit objects
    session.setAttribute("sessionMessage", "Hello from session!");
    application.setAttribute("appMessage", "Hello from application!");
%>

<!-- JSTL Iteration -->
<ul>
    <c:forEach var="name" items="${namesList}">
        <li>${name}</li>
    </c:forEach>
</ul>

<!-- Access implicit objects -->
<p>Request Scope Message: <%= "This is request scope message!" %></p>
<p>Session Scope Message: ${session.sessionMessage}</p>
<p>Application Scope Message: ${applicationScope.appMessage}</p>

</body>
</html>