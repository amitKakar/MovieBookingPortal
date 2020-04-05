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

<script>
if(!(session_id()) header("Location: /adminLogin");
</script>

<title>Welcome</title>


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
	
	background-image: url("${contextPath}/resources/images/img8.jpg");
	
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
    font-size : 22px;

}

.container {
	padding-top : 100px;
}
</style>

</head>

<script>  
function validateform()
{  

	var password=$("#password").val();  
	var operatorName=$("#operatorName").val();  

	if(operatorName == null || operatorName == "")
	{
		alert("Username can't be blank");  
  		return false; 
  	}  
} 
</script>


<body>

	<div class="topnav">
		<a class="active" href="${path}/adminHome">${adminname}</a> 
		<a href="${path}OperatorList">List All Operators</a>
		<a href="${path}/userList">List All Users</a>
		<a href="${path}/adminLogout">Log Out</a>
	</div>
	
<div class="container">

	<h2 style="text-align: center; font-size: 36px; color:white;" >Register Operator</h2>
	
	<form:form name="operatorForm" method="post"
		action="${path}/addOperator" modelAttribute="operatorForm"
		onsubmit="return validateform()" class="form-signin" >

		<div class="form-group" id="required">
			<label><font size="5" color="white">Operator Name:</font></label>
			<form:input id="operatorName" path="operatorName" placeholder="Enter operator's name" class="form-control" autofocus="true"/>
		</div>
		<div class="form-group" id="required">
			<label><font size="5" color="white">Password:</font></label>
			<form:input id="password"  type="password" path="password" placeholder="Enter operator's password" class="form-control" />
		</div>

		<div class="form-group">
			<form:button class="btn btn-lg btn-primary btn-block" type="submit" >SUBMIT</form:button>
		</div>

	</form:form>
	</div>
	
	

	
	
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>