<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title></title>
</head>
<body>
<div align="center">
  <h2>Users Management</h2>
</div>
<%
  List<User> users = (List<User>) request.getAttribute("users");
%>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of Users</h2></caption>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Surname</th>
      <th>Age</th>
      <th>Actions</th>
    </tr>
    <%
      if (users != null && !users.isEmpty()) {
        for (User user : users) {
          out.println("<tr>");
          out.print("<td>" + user.getId() + "</td>");
          out.print("<td>" + user.getName() + "</td>");
          out.print("<td>" + user.getAge() + "</td>");
          out.print("<td><a href='/edit?id="+ user.getId()+"'>Edit</a>");
          out.print("  or  <a href='/delete?id="+ user.getId()+"'>Delete</a></td>");
          out.println("</tr>");
        }
      } else {
        out.println("<p>There are no users yet!</p>");
      }
    %>
  </table>
</div>
<br>
<div>       <!-- content -->
  <div>    <!-- buttons holder -->
    <button onclick="location.href='/add'">Add new user</button>
  </div>
</div>
<br>
<br>
Current Time: <%
  SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy. HH:mm:ss");
  out.print(sdf.format(new Date())); %>
</body>
</html>

