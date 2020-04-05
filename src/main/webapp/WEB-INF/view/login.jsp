<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Log In</title>

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


<script type="text/javascript">

function validateform()
{
		var validUser = false;
		//alert("ASdasdadADADASD");
		var userName= $("#username").val();
    	var password=$("#password").val();
    	
    	if(userName == null || userName == "")
    	{
    		alert("Username can't be blank");  
      		return false; 
      	}  
    	
    	var jsonData = {
    			"userName" : userName,
    			"password" : password
    	} 
    	
    	
		$.ajax({
	        
	        type: "POST",
	        contentType : "application/json; charset=utf-8",
			data : JSON.stringify(jsonData),
			dataType : 'json',
	        url: "/authetication",
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
		<a href="${path}/welcome">Home</a>
		<a href="${path}/adminLogin">Admin</a> 
		<a href="${path}operatorLogin">Operator</a>
		<a href="${path}/bookingPage">Browsing</a>
		<a href="${path}register">User Signup</a>
		<a class="active" href="${path}/userLogin">User Login</a>
		
	</div>
	
	<div class="container">

	<form:form name="loginform" method="get" action="${path}/bookingPage" 
				modelAttribute="loginForm" class="form-signin" onsubmit="return validateform()">
				
				
		<h2 style="text-align: center; font-size: 36px; color:white;" >User Login</h2>
		
		<br>
		<div class="form-group}">
			<label><font size="5" color="white">User Name:</font></label>
			<input placeholder="Enter your user name" id="username"
					class="form-control" />
		</div>
		
		<br>
		
		<div class="form-group}">
			<label><font size="5" color="white">Password:</font></label>
			<input placeholder="Enter your password" id="password" type="password"
					class="form-control"/>
		</div>
		
		<br>
		
		<div class="form-group">
			<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
		</div>
		
	</form:form>
	 
	
	
</div>


	<!-- /container -->
	
</body>
</html>