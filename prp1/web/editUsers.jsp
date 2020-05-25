<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: 7
  Date: 23.05.2020
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<div align="center">
    <h2>Users Management - Edit user</h2>
</div>
<%  String name = "";
    int age = 0;
    long id = 0;
    if (request.getAttribute("userForEdit") != null) {
        User user = (User) request.getAttribute("userForEdit");
        name = user.getName();
        age = user.getAge();
        id = user.getId();
    }

%>
<form action="/edit" method="post">
    <label>Name:
        <input type="text" name="name" value="<%=name%>"><br/>
    </label>

    <label>Age:
        <input type="text" name="age" value="<%=age%>"><br/>
    </label>
    <input type="text" name="id" value="<%=id%>" hidden="true">
    <button type="submit">   Save   </button>
</form>
<div>
    <button onclick="location.href='/'">See all users</button>
</div>
<br>
<br>
</body>
</html>