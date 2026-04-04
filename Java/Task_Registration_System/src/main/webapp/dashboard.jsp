
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.UsersModel, com.model.CoursesModel, com.dao.Dao, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
        }
        /* Navigation Bar */
        .navbar {
            background-color: #2c3e50;
            color: white;
            padding: 15px 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            font-weight: 500;
        }
        .logout-btn {
            background-color: #e74c3c;
            padding: 8px 15px;
            border-radius: 5px;
            transition: 0.3s;
        }
        .logout-btn:hover {
            background-color: #c0392b;
        }

        /* Dashboard Container */
        .container {
            width: 90%;
            margin: 30px auto;
        }
        .welcome-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
            margin-bottom: 30px;
        }
        .welcome-card h2 {
            margin: 0;
            color: #2c3e50;
        }

        /* Course Table Styles */
        .course-section {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }
        .course-section h3 {
            border-left: 5px solid #F37254;
            padding-left: 15px;
            color: #2c3e50;
            margin-bottom: 20px;
        }
        .custom-table {
            width: 100%;
            border-collapse: collapse;
        }
        .custom-table th {
            background-color: #f8fafc;
            color: #64748b;
            text-align: left;
            padding: 15px;
            border-bottom: 2px solid #edf2f7;
        }
        .custom-table td {
            padding: 15px;
            border-bottom: 1px solid #edf2f7;
            vertical-align: top;
        }
        .course-name {
            font-weight: bold;
            color: #334155;
        }
        .course-price {
            color: #27ae60;
            font-weight: bold;
        }
        .btn-enroll {
            background-color: #F37254;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 600;
            transition: 0.3s;
            display: inline-block;
        }
        .btn-enroll:hover {
            background-color: #e66a4c;
            box-shadow: 0 4px 8px rgba(243, 114, 84, 0.3);
        }
    </style>
</head>
<body>

    <% 
        UsersModel u = (UsersModel)session.getAttribute("user"); 
        if(u == null) {
            response.sendRedirect("index.jsp"); 
            return;
        }
    %>

    <div class="navbar">
        <div class="logo">🎓 E-Learning Portal</div>
        <div class="nav-links">
            <span>Welcome, <b><%= u.getName() %></b></span>
            <a href="logout.jsp" class="logout-btn">Logout</a>
        </div>
    </div>

    <div class="container">
        
        <div class="welcome-card">
            <h2>Hello, <%= u.getName() %>!</h2>
            <p style="color: #64748b;">Register Here</p>
        </div>

        <div class="course-section">
            <h3>Available Courses</h3>
            <table class="custom-table">
                <thead>
                    <tr>
                        <th>Course Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<CoursesModel> list = Dao.getAllCourses();
                        if(list != null && !list.isEmpty()) {
                            for(CoursesModel c : list) {
                    %>
                    <tr>
                        <td class="course-name"><%= c.getName() %></td>
                        <td style="color: #64748b; font-size: 14px; max-width: 400px;"><%= c.getDescription() %></td>
                        <td class="course-price">₹<%= c.getPrice() %></td>
                        <td>
                        <a href="EnrollServlet?courseId=<%= c.getId() %>&amount=<%= c.getPrice() %>" class="btn-enroll">
        					Enroll Now
    					</a>
                            
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" style="text-align:center; padding:30px; color:#94a3b8;">
                            No courses available at this time.
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>