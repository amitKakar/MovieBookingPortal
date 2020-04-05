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
<link href="${contextPath}/resources/css/historyDropdown.css" rel="stylesheet">
<link href="${contextPath}/resources/css/availabilityCheck.css" rel="stylesheet">

<style>
body {
	padding-top : 0px;
}

.container {
	padding-top : 30px;
}

.topnav {
	overflow: visible;
	background-color: #333;
}

.heading {
	color : #002e4d;
	font-size : 22px;
	margin-left: 45%;
}

.detail {
	font-size : 18px;
}

</style>

<body>
	
	<div class="topnav">
		<a  href="/operatorHome" >${operatorname }</a> 
		<a  href="/registerMovie">Add Movie</a> 
		<a  class="active" href="/theatre">Add Theatre</a> 
		<a  href="/registerShow">Add Show</a> 
		
		<a  href="/operatorLogout">Log Out</a>

		<div class="dropdown">
			<button class="dropbtn">History  </button>
			<div class="dropdown-content">
				<a href="/registered/movies">Movies</a> 
				<a href="/registered/theatres">Theatres</a> 
				<a href="/registered/shows">Shows</a>
			</div>
		</div>
	</div>
	
	
	
	<div>
	<h1 style="text-align: center;color: red;">Theatre is registred Successfully </h1>
	</div>
	
	<div id="main" align="center">
	<div>
		<h2>Details: </h2>
	</div>
	<br><br>
	<div align="left">
		<label class="heading">Theatre Id: </label><label class="detail">&nbsp;&nbsp;${theatreId }</label>
		<br>
		<label class="heading">Name: </label><label class="detail">&nbsp;&nbsp;${theatre.name }</label>
		<br>
		<label class="heading">Address: </label><label class="detail">&nbsp;&nbsp;${theatre.address }</label>
		<br>
		<label class="heading">City: </label><label class="detail">&nbsp;&nbsp;${theatre.city }</label>
		<br>
		<label class="heading">Number of screens: </label><label class="detail">&nbsp;&nbsp; ${theatre.numberOfScreens }</label>
		<br>
		<br>
		<br>
	</div>
	
	<p>
		<a href="${path}/theatre"><button class="btn"><font size="3">Add more</font></button></a>
	</p>
	
	</div>
	
	
</body>
</html>