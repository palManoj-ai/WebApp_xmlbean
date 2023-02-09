<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
  <style>
	body{
	background-image: url("https://images.unsplash.com/photo-1532019333101-b0f43c16a912?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
	background-repeat: no-repeat;
	background-attachment: fixed;
  	background-size: cover;
  	font-weight: 400;
	}
</style>
<title>SignupPage</title>
</head>
<body>


<div class="container" style=width:30%>
<form action="SignUp" method="post">
	<br>
	<h5 style="font-family:serif; ">Welcome to the Liberty Mutual Fund</h5>
	<br>
	<br>
	<label for="email"> Email: </label><input type="text" name="email" id="Email" class="form-control">
	<label for="password"> Password: </label><input type="password" name="password" id="password" class="form-control">
	<label for="phoneno">Phone No: </label><input type="text" name="phoneno" id="phoneno" class="form-control">
	<div style="width:30%">
	<label for="Submit"></label><input type="Submit" id="Submit" class="form-control">
	<label for="Reset"></label><input type="Reset" id="Reset" class="form-control">
	<span> ${error}</span>
	</div>
	<br>	
</form>
</div>

</body>
</html>