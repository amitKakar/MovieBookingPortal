<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>Admin login</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/navigationbar.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font.css" rel="stylesheet">


<style>
body {
	padding-top : 0px;
	background-image: url("${contextPath}/resources/images/img25.jpeg");
	
	background-repeat: no-repeat;

    background-size:cover;
}

label {
    font-weight: normal;
}
.container {
	padding-top : 100px;
}
</style> 

 
</head>

	<script>  
function validateform()
{  

	var adminName= $("#adminName").val();
	var password=$("#password").val();
	var validUser = false;
	var jsonData = {
			"adminName" : adminName,
			"password" : password
	} 
	
	$.ajax({
		
        type: "POST",
        contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonData),
		dataType : 'json',
        url: "/admin/login",
        async : false,
		success : function(response)
		{
			//alert("login successful....!!!!");
			validUser = true;
		},
        error: function (response) {
            alert("Invalid username password...!!!!"); 
            validUser = false;
        }
    });		
	
	if(validUser == true) {
		return true;
	}
	else {
		return false;
	} 
	
} 
</script>



<body>
	<div class="topnav">
		<a href="/welcome">Home</a> 
		<a class="active" href="${path}/adminLogin">Admin</a> 
		<a href="${path}operatorLogin">Operator</a>
		<a href="${path}/bookingPage">Browsing</a>
		<a href="${path}register">User Signup</a>
		<a href="${path}/userLogin">User Login</a>
		
	</div>

<div class="container">

	<h2 style="text-align: center; font-size: 36px; color:white;" >Admin Login</h2>
	
	<form:form name="adminform" method="post" action="/registerOperator" modelAttribute="adminform" class="form-signin"
	onsubmit="return validateform()">

		<div class="form-group}">
			<label><font size="5" color="white">Admin Username:</font></label>
			<form:input path="adminName" placeholder="Enter your user name" class="form-control" autofocus="true" />
		</div>
		<br>
		<div class="form-group}">
			<label><font size="5" color="white">Password:</font></label>
			<form:input type="password" path="password" placeholder="Enter your password" class="form-control" />
		</div>
		<br>
		<div class="form-group}">
			<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
		</div>
		
	</form:form>
	
</div>
<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>