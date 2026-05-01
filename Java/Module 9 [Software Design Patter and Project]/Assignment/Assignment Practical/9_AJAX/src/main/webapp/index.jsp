<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Username Validation with AJAX</title>
<script>
function checkUsername() {
    var username = document.getElementById("username").value;
    
    // Create XMLHttpRequest object
    var xhr = new XMLHttpRequest();
    
    // Define the function that handles the response
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("message").innerHTML = this.responseText;
        }
    };
    
    // Open and send the asynchronous request to CheckUsernameServlet
    xhr.open("GET", "CheckUsernameServlet?username=" + username, true);
    xhr.send();
}
</script>
</head>
<body>
    <h2>AJAX Live Username Validation</h2>
    <form>
        <label for="username">Enter Username:</label>
        <input type="text" id="username" name="username" onkeyup="checkUsername()">
        <span id="message"></span><br><br>
        
        <label for="password">Enter Password:</label>
        <input type="password" id="password" name="password"><br><br>
        
        <input type="button" value="Submit">
    </form>
</body>
</html>
