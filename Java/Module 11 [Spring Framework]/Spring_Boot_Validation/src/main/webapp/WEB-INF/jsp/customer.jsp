<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>

<style>

.error{
    color:red;
}

.errorBlock{
    color:#000;
    background-color:#ffeeee;
    border:3px solid #ff0000;
    padding:8px;
    margin:16px;
}

</style>

</head>

<body>

<form:form method="POST"
           action="addCustomer"
           modelAttribute="customer">

    <form:errors path="*" cssClass="errorBlock" element="div"/>

    <table>

        <tr>
            <td>
                <form:label path="name">Name:</form:label>
            </td>

            <td>
                <form:input path="name"/>
            </td>

            <td>
                <form:errors path="name" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="password">Password:</form:label>
            </td>

            <td>
                <form:password path="password"/>
            </td>

            <td>
                <form:errors path="password" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="address">Address:</form:label>
            </td>

            <td>
                <form:textarea path="address" cols="30" rows="5"/>
            </td>

            <td>
                <form:errors path="address" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="language">Technologies:</form:label>
            </td>

            <td>
                <form:checkboxes
                        items="${customerDynamic.languages}"
                        path="language"/>
            </td>

            <td>
                <form:errors path="language" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="gender">Gender:</form:label>
            </td>

            <td>
                <form:radiobutton path="gender" value="male"/>Male

                <form:radiobutton path="gender" value="female"/>Female
            </td>

            <td>
                <form:errors path="gender" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="degree">Degree:</form:label>
            </td>

            <td>
                <form:select path="degree"
                             items="${customerDynamic.degrees}"/>
            </td>

            <td>
                <form:errors path="degree" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit"
                       value="Add Customer"/>
            </td>
        </tr>

    </table>

</form:form>

</body>
</html>