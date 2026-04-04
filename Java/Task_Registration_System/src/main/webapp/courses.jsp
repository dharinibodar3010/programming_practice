<%@page import="java.util.List"%>
<%@page import="com.model.CoursesModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course Name</th>
            <th>Description</th> <th>Price</th>
            <th>Action</th>
        </tr>
        <% 
            List<CoursesModel> list = (List<CoursesModel>)request.getAttribute("courses");
            if(list != null) {
                for(CoursesModel c : list) { 
        %>
        <tr>
            <td><%= c.getName() %></td>
            <td><%= c.getDescription() %></td> <td><%= c.getPrice() %></td>
            <td>
                <form action="EnrollServlet" method="post">
                    <input type="hidden" name="courseId" value="<%= c.getId() %>">
                    <input type="submit" value="Enroll Now">
                </form>
            </td>
        </tr>
        <% 
                } 
            } else {
        %>
        <tr>
            <td colspan="4">No courses available.</td>
        </tr>
        <% } %>
    </table>
</table>
</body>
</html>