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
}

.container {
	padding-top : 30px;
}

.heading {
	color : #002e4d;
	font-size : 22px;
	margin-left: 42%;
}

.detail {
	font-size : 18px;
}

</style>

<body>
	
	
	<div class="topnav">
		<a class="active" href="${path}/adminHome">${adminname}</a> 
		<a href="${path}OperatorList">List All Operators</a>
		<a href="${path}/userList">List All Users</a>
		<a href="${path}/adminLogout">Log Out</a>
	</div>


	
	<br><br>
	
	<div>
	<h2 style="text-align: center;color: red;">Operator is registred Successfully </h2>
	</div>
	
	<div id="main" align="center">
	<div>
		<h2>Details: </h2>
	</div>
	<br><br>
	<div align="left">
		<label class="heading">Operator Id: </label><label class="detail">&nbsp;&nbsp;${operatorId }</label>
		<br>
		<label class="heading">Operator Name: </label><label class="detail">&nbsp;&nbsp;${operatorName}</label>
		<br>
		<br>
		<br>
	</div>
	
	<%-- <p>
		<a href="${path}/registerMovie"><button class="btn"><font size="3">Add more</font></button></a>
	</p> --%>
	
	</div>
	
	
</body>
</html>