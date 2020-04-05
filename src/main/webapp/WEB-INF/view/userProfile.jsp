<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>operator table</title>

<script>
if(!(session_id()) header("Location: /userLogin");
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
<link href="${contextPath}/resources/css/navigationbar.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font.css" rel="stylesheet">

<style>
body {
	padding-top : 0px;
	
}


	.update input{
	margin-top: 10px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
	}
	
	.heading {
	color : #002e4d;
	font-size : 22px;
	margin-left: 43%;
}

	.edit-box {
	font-size : 22px;
	margin-left: 43%;
	align : center;
	width : 400px;
}

	.detail {
	font-size : 18px !important;
}
</style>

</head>

<script type="text/javascript">

$(document).ready(function(){

	$(".update").hide();
	$("#updateprofile").hide(); 
	
});
</script>

<script type="text/javascript">
	function editProfile()
	{
		$(".update").show();
		$("#edit").hide();
		$("#updateprofile").show();
	
	}
	
	
</script>

<script type="text/javascript">
	function updateProfile()
	{
		
 		var name = $("#name").val();
 		
		var password = $("#password").val();

		var email = $("#email").val();

		var age = $("#age").val();

		var gender = $("#gender").val();

		
		var jsonData = {
			
			"name" : name,
			"password" : password,
			"email" : email,
			"age" : age,
			"gender" : gender
		}
		
		$.ajax({
     		//alert('inside ajax'); 
     		type : "POST",
     		url: "/updateUser",
     		async : false,
     		contentType:"application/json; charset=utf-8",
     		data: JSON.stringify(jsonData),
     		dataType: 'json',
     		//complete:function(){alert('req complete');},
     		success: function(data) {
     			

         		//alert('user updated successfully...!!');
         		
         	
     		},
     		error:function()
     		{
         		alert("Unable to update");
     		}
     
     	}); 
		
		/* $("#name-label").text("name");
		$("#password-label").text("password");
		$("#email-label").text("email");
		$("#age-label").text("age");
		$("#gender-label").text("gender"); */
		
		
		
		$("#edit").hide();
		$("#updateprofile").hide();
		$(".update").hide();
		
		location.reload();
		
		
	}
</script>



<body>
	
	<div class="topnav" id="topnav">
		<a href="${path}/bookingPage">${username}</a> 
		<a class="active" href="${path}/profile">Profile</a> 
		<a href="${path}/history">History</a> 
		<a href="${path}/logout">Log Out</a>
	</div>
	
	<center>
	
	<br><br>
	<div>
	<h2 style="text-align: center;color: red;"> Profile </h2>
	</div>
	<br>
	<br>
	
	
	
	<div align="left">  
	
		<label class="heading" id="name-lable">Username : ${user.userName}</label>
		<br>
		<br>
		
		<label class="heading">Name: ${user.name}</label>
		<div class ="edit-box">
			<input type="text" class="update" id="name" value="${user.name}"/>
		</div>
		<br>
		<br>

		<label class="heading" id="password-lable">Password: ********</label>
		<div class ="edit-box">
			<input type="password" class="update" id="password" value="${user.password }"/>
		</div>
		<br>
		<br>
		
		<label class="heading" id="email-lable">Email : ${user.email}</label>
		<div class ="edit-box">
			<input type="text" class="update" id="email" value="${user.email}"/>
		</div >
		<br>
		<br>
		
		<label class="heading" id="age-lable">Age: ${user.age}</label>
		<div class ="edit-box">
			<input type="text" class="update" id="age" value="${user.age }"/>
		</div>
		<br>
		<br>
		
		<label class="heading" id="gender-lable">Gender: ${user.gender }</label>
		<div class ="edit-box">
			<input type="text" class="update" id="gender" value="${user.gender}"/>
		</div>
		<br>
		<div align="center">
			<button class="btn" id="edit" onclick="editProfile()">Edit profile</button>
		</div>
		
		
		<div align="center">
			<button class="btn" id="updateprofile" onclick="updateProfile()">Update</button>
		</div>
			
			
	</div>
	<br><br>
	<%-- <p>
		<a href="${path}/bookingPage"><button class="btn"><font size="3">User Home</font></button></a>
	</p> --%>
	
	</center>

</body>
</html>