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
	background-image: url("${contextPath}/resources/images/img22.jpeg");
	
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

function validateGuest()
{
		//alert("asdadadd");
		var guest= false;
		var userName= $("#username").val();
    	//var email=$("#email").val();
    	
    	if(userName == null || userName == "")
    	{
    		alert("Username can't be blank");  
      		return false; 
      	}  
    	
    	
    	 jQuery.ajax({
	        
	        type: "GET",
	        url: "/authenticateGuest/"+userName.toUpperCase(),
	        async:false,
			success: function(response)
			{
				
				//alert("success");
				guest = true;
				
			},
	         error: function (response) {
	        	
	        	
	            alert("Invalid username...!!!!"); 
	            guest = false;
	        } 
	    });		
    	 
  		return guest;
    
}

</script>



<body>
	
	<div class="topnav" id="topnav-guest">
		 
		<a href="${path}/welcome">Home</a> 
		<a href="/bookingPage">Guest Browsing</a>
		<a class="active" href="${path}/generateTicket/guest">Generate Ticket</a>
		<a href="${path}register">User Signup</a>
		<a href="${path}/userLogin">User Login</a>
	</div>
	
	<div class="container">

	<form:form name="guestTicketForm" method="post" action="/history/guest" 
				modelAttribute="guestTicketForm" class="form-signin" onsubmit="return validateGuest()">	
				
		<h2 style="text-align: center; font-size: 36px; color:white;" >Guest Ticket</h2>
		
		<br>
		<div class="form-group}">
			<label><font size="5" color="white">Guest username:</font></label>
			<form:input placeholder="Enter your user name" id="username" path="userName"
					class="form-control" />
		</div>
		
		<br>
		
		<%-- <div class="form-group}">
			<label><font size="5" color="white">Email:</font></label>
			<form:input placeholder="Enter your email address" id="email" path="email" class="form-control"/>
		</div> --%>
		
		
		<!-- <div class="form-group}">
			<label><font size="5" color="white">Enter the OTP:</font></label>
			<input placeholder="Enter the OTP" id="OTP" class="form-control"/>
		</div> -->
		
		<div class="form-group">
			<form:button class="btn btn-lg btn-primary btn-block" type="submit">GET TICKET</form:button>
		</div>
		
	</form:form>
	 
	
	
	</div>


	<!-- /container -->
	
</body>
</html>