<%@page import="com.model.ContactModel"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - View Contact Messages</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background: #f4f7f6;
        margin: 0;
        padding: 40px;
    }
    .container {
        max-width: 1000px;
        margin: auto;
        background: #fff;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0 5px 25px rgba(0,0,0,0.1);
    }
    h2 {
        color: #2c3e50;
        text-align: center;
        margin-bottom: 30px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th {
        background: #6a11cb;
        background: -webkit-linear-gradient(to right, #2575fc, #6a11cb);
        background: linear-gradient(to right, #2575fc, #6a11cb);
        color: white;
        padding: 15px;
        text-align: left;
    }
    td {
        padding: 12px 15px;
        border-bottom: 1px solid #eee;
        color: #555;
    }
    tr:hover {
        background-color: #f9f9f9;
    }
    .no-data {
        text-align: center;
        padding: 40px;
        color: #999;
    }
    .btn-back {
        display: inline-block;
        margin-bottom: 20px;
        text-decoration: none;
        color: #6a11cb;
        font-weight: 500;
        transition: 0.3s;
    }
    .btn-back:hover {
        color: #2575fc;
    }
</style>
</head>
<body>

<div class="container">
    <a href="index.jsp" class="btn-back">← Back to Site</a>
    <h2>Contact Inquiries List</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Feedback</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<ContactModel> list = Dao.viewcontact();
                if(list != null && !list.isEmpty()) {
                    for(ContactModel m : list) {
            %>
            <tr>
                <td><%= m.getId() %></td>
                <td><%= m.getFullname() %></td>
                <td><%= m.getEmail() %></td>
                <td><%= m.getPhone() %></td>
                <td><%= m.getFeedback() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" class="no-data">No messages found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
