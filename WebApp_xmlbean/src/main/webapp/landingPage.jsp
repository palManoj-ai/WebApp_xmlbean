<%@page import="java.util.List"%>
<%@page import="mavensrc.userData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
	body{
	background-image: url("https://images.unsplash.com/photo-1532019333101-b0f43c16a912?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
	background-repeat: no-repeat;
	background-attachment: fixed;
  	background-size: cover;
  	font-weight: 400;
	}
</style>
<title>Landing Page</title>
</head>
<body>

	<br>
	<h5 style="font-family:serif; ">${message2}</h5>
	<br>
	<br>	

<div class="container" style="width: 50%">
<table class="table">
	<thead>
	<tr>
		<th>Email</th>
		<th>Password</th>
		<th>Phone</th>
		
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>${user.getEmail()}</td>
		<td>${user.getPassword()}</td>
		<td>${user.getPhone()}</td>
	</tr>
	</tbody>
</table>

<a href="allData?key=${user.getEmail()}"><button type="button" class="btn-btn-primary" class="form-control">View All Users</button></a>

<!-- Now the code for fetching arraylist  req.setAttribute("listofuser", listOfUser); always use ctr+space -->
<table class="table">
	<thead>
	<tr>
		<th>Email</th>
		<th>Password</th>
		<th>Phone</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
<%
List<userData> listOfUsers=(List<userData>)request.getAttribute("listofuser");
		if(listOfUsers!=null){
			for(userData entry:listOfUsers){
				%>
		<tr>
			<td><%=entry.getEmail() %></td>
			<td><%=entry.getPassword()%></td>
			<td><%=entry.getPhone() %></td>
			<!--//if we want to pass key from prev file, use param.  -->
			<td><a href="deleteRow?keydel=<%=entry.getEmail()%>&key=${param.key}"><button type="button" class="btn btn-secondary">Delete Row</button></a></td>
		</tr>
<%
			}
%>
</tbody>
<%
		}
		%>
</table>
</div>
</body>
</html>