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


<title>Create your account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/availabilityCheck.css" rel="stylesheet">
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

#required label:after {
    color: #e32;
    content: ' *';
    display:inline;
    font-size : 18px;

}

#availability-div label:after {
    color: #e32;
    content: ' *';
    display:inline;
    font-size : 18px;

}

.container {
	padding-top : -5px;
}
</style>

</head>



<script type="text/javascript">

$(document).ready(function(){

$('#registrationButton').click(function(){
	
		var validation = validateform();
		
		if(validation == true) {

		var name = $("#name").val();

		var username = $("#username").val();

		var password = $("#password").val();

		var email = $("#email").val();
		
		var mobilenumber = $("#mobileNumber").val();

		var age = $("#age").val();

		var gender = $("#gender").val();

		var jsonObject = {
				
				"name" : name,
    			"userName": username,
    			"password" : password,
    			"email" : email,
    			"mobileNumber" : mobilenumber,
    			"gender" : gender,
    			"age": age
   		}
		
		var register = false;
		
		
	    $.ajax({
	            
	    		type : "POST",
				url: "/user",
				contentType:"application/json; charset=utf-8",
				data: JSON.stringify(jsonObject),
				dataType: 'json',
	           	async : false, 
	            success: function(response)
	            {
					register = true;
	            },
	            
	            error: function (response) {
	            	alert("Something went wrong...please try again later...!!!");
	            	register = false;

                }
	    });
		
	    if(register == true)
	    {
	    	window.location.replace("/userLogin");
	    }
	    
		}
	    
});

});
</script> 


<script type="text/javascript">

$(document).ready(function(){
		
		var userN= '';
		var availabel = true;
		
		function getUserName()
		{
			userN = $("#username").val().trim();
			return userN;
		}

	   $("#username").blur(function(){
		   
		  var userName = getUserName().toUpperCase();

	      if(userName.length > 3)
	      {
	         $("#p").html("checking...");

	         $.ajax({
	            
	            type: "GET",
	            url: "/user/"+userName,
	           
	            //data: {userName:userName},
	            success: function(response)
	            {
					//alert("success")
					
					$("#p").html('<font color="red">'+'Username Already in use'+'</font>');
					
					availabel= false;

	             },
	            error: function (response) {
	            	$("#p").html('<font color="green">'+'Available'+'</font>');
	            	
	            	availabel = true;
                }
	         });
	      }

	    });
	   
	   
	    window.checkAvailability = function()
		{
			return availabel;
		}

});
</script> 
 
<script type="text/javascript">

$(document).ready(function(){

		var validNumber = true;
		
	    $("#mobileNumber").blur(function(){
		   
		  var mobileNumber = $("#mobileNumber").val();

	      if(mobileNumber.length == 0 || mobileNumber.length == 10)
	      {
	    	  validNumber = true;

	      }
	      else
	      {
	    	  validNumber = false;
	      }
	  
	    });
	   
	    window.phoneNumberValidation = function()
		{
			return validNumber;
		}

});
</script> 

<script>
	function validateform() {
		
		//alert("inside validate");
		
		var name = $("#name").val();
		var password = $("#password").val();
		var username = $("#username").val();
		var email = $("#email").val();
		var age = $("#age").val();
		var gender = $("#gender").val();
		
		var regularExpression = /\S+@\S+\.\S+/;
		
		var availabel= checkAvailability();
		var validNumber = phoneNumberValidation();
		//alert(availabel);
		debugger;
		 if(availabel == false){
			alert("Username already in use");
			return false;
		} 
		
		if (name == null || name == "") {
			alert("Name can't be blank");
			return false;
		} else if (username == null || username == "" ||username.length<4) {
			alert("Username invalid");
			return false;
		} else if (password.length<6 || password.length>12) {
			alert("Password must be between 6 and 12 characters");
			return false;
		} else if (regularExpression.test(email) == false) {
			alert("Invalid email address.");
			return false;
		} else if (validNumber == false) {
			alert("Invalid mobile number.");
			return false;
		} else if (age < 18) {
			alert("You must be 18 years old.");
			return false;
		} else if (gender == -1) {
			alert("you must specify the gender");
			return false;
		} else if( availabel == false){
			return false;
		} 
		
		return true;
	}
</script>



<body>

	<div class="topnav">
		<a href="${path}/welcome">Home</a>
		<a href="${path}/adminLogin">Admin</a> 
		<a href="${path}operatorLogin">Operator</a>
		<a href="${path}/bookingPage">Browsing</a>
		<a class="active" href="${path}register">User Signup</a>
		<a href="${path}/userLogin">User Login</a>

	</div>

	<div class="container">
		<form:form name="registrationForm" method="post" class="form-signin"
			action="${path}/user" modelAttribute="userForm">
			
			<h2 style="text-align: center;  font-size: 36px; color:white;" >Create your account</h2>

			<br>

			<div class="form-group" id="required">
				<label><font size="5" color="white">Name:</font></label>
				<input type="text" placeholder="Enter your name" class="form-control" id="name" />
			</div>

			<div class="form-group" id="availability-div">

				<label><font size="5" color="white">User Name:(must be greater than 3 and can't be changed)</font></label>
					
				<input type="text" placeholder="Enter your user name" class="form-control" id="username"/>
					
				<div id="check">
					<p id="p"></p>
				</div>
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Password:(must be between 6 and 12)</font></label>
				<input type="password" placeholder="Enter your password" class="form-control" id="password" />
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Email Id:(must be valid)</font></label>
				<input type="text" placeholder="Enter your email id" class="form-control" id="email" />
			</div>
			
			<div class="form-group">
				<label><font size="5" color="white">Mobile No. :</font></label>
				<input type="text" placeholder="Enter your mobile number" class="form-control" id="mobileNumber" />
			</div>
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Age:(must be greater than 18)</font></label>
				<input placeholder="Enter your age" class="form-control" id="age" />
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Gender:</font></label>
				<select id="gender" class="form-control">
						<option value="-1">Select Gender</option>
						<option value="M">Male</option>
						<option value="F">Female</option>
				</select>
				
				
			</div>

			<p>
				<button class="btn btn-lg btn-primary btn-block" type="button"  id="registrationButton">SUBMIT</button>
			</p>

		</form:form>
		
	</div>
	<!-- /container -->

	
</body>
</html>