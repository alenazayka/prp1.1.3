<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form method="post">
    <label>Name:
        <input type="text" name="name"><br/>
    </label>

    <label>Age:
        <input type="text" name="age"><br/>
    </label>

    <button type="submit">   Add   </button>
</form>
<div>
    <button onclick="location.href='/'">See all users</button>
</div>
<br>
<br>
<%
    if (request.getAttribute("userAdded") != null) {
        out.println("<p>User '" + request.getAttribute("userAdded") + "' was added successfully!</p>");
    }
%>
</body>
</html>