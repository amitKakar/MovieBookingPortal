<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Create an account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<script type="text/javascript">
$('form').each(function() { this.reset() });
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

	   $("#username").keyup(function(){
		   
	      
	      //alert(userName);
	      var userName = getUserName();
	      //console.log(userName);
	      
	      if(userName.length > 3)
	      {
	    	  //alert(userName);
				
	         //$("#username_response").show();
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
	            	/* console.log("SUCCESS : ", response);
	            	$("#userName_response").html("<span class='exists'>Available.</span>");
                    $("#p").hide(); */
	                /* if(response == 404)
	                {
	                	$("#userName_response").html("<span class='exists'>Available.</span>");
	                    $("#p").hide();
	                }
	                else
	                {
	                	$("#userName_response").html("<span class='not-exists'>* Username Already in use.</span>");
	          
	                	$("#p").hide();
	                } */

	             },
	            error: function (response) {
                    //alert(response.responseText + "e"); 
	            	$("#p").html('<font color="green">'+'Available'+'</font>');
	            	
	            	availabel = true;
                }
	             });
	      }

	    });
	   
	   
	    window.checkAvailability = function()
		{
		   //alert('inside availability');
			return availabel;
		}

});
</script> 


<script>
	function validateform() {
		var name = $("#name").val();
		var password = $("#password").val();
		var username = $("#username").val();
		var email = $("#email").val();
		var age = $("#age").val();
		var gender = $("#gender").val();
		
		var regularExpression = /\S+@\S+\.\S+/;
		
		var availabel= checkAvailability();
		//alert(availabel);
		
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
		} else if (age < 18) {
			alert("You must be 18 years old.");
			return false;
		} else if (gender == -1) {
			alert("you must specify the gender");
			return false;
		} else if( availabel == false){
			return false;
		} 
	alert("user registered successfully");
	}
</script>

<style>
	#usernameDiv{
		position:relative;
	}
	
	#check{
		position : absolute;
		left: 320px;
		top: 40px;
		
	}
	
	#p{
	font-weight: bold;
	font-size: 18px;
	}
</style>

<body>

	<div class="container">
		<form:form name="registrationForm" method="post" class="form-signin"
			action="${path}/user" modelAttribute="userForm"
			onsubmit="return validateform()">
			
			<h2 class="form-signin-heading">Create your account</h2>


			<div class="form-group">
				Name:
				<form:input path="name" placeholder="Enter your name"
					class="form-control" autofocus="true" />
			</div>

			<div class="form-group" id="usernameDiv">

				User Name:(must be greater than 3 and can't be changed)
				<%-- <form:input path="userName" placeholder="Enter your user name"
					class="form-control" id="userName" onBlur="checkAvailability()"/> --%>
					
				<form:input path="userName" placeholder="Enter your user name"
					class="form-control" id="username" />
					
				<div id="check">
					<p id="p"></p>
				</div>
			</div>
			
			<div class="form-group">
				Password:(must be between 6 and 12)
				<form:input path="password" placeholder="Enter your password"
					class="form-control" />
			</div>
			<div class="form-group">
				Email Id:(must be valid)
				<form:input path="email" placeholder="Enter your email id"
					class="form-control" />
			</div>
			<div class="form-group">
				Age:(must be greater than 18)
				<form:input path="age" placeholder="Enter your age"
					class="form-control" />
			</div>
			<div class="form-group">
				Gender:
				<form:select path="gender" id="gender" class="form-control">
						<option value="-1">Select Gender</option>
						<option value="M">Male</option>
						<option value="F">Female</option>
				</form:select>
				
				
			</div>

			<p>
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
			</p>

		</form:form>
		
		<div style="text-align: center;">

			<a href="${path}welcome"><button class="btn">HOME</button></a>
		</div>
	</div>
	<!-- /container -->

	
</body>
</html>