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
if(!(session_id()) header("Location: /operatorLogin");
</script>

<title>Welcome</title>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
<link href="${contextPath}/resources/css/navigationbar.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font.css" rel="stylesheet">
<link href="${contextPath}/resources/css/historyDropdown.css" rel="stylesheet">

<style>
body {
	padding-top : 0px;
	background-image: url("${contextPath}/resources/images/img6.jpg");
	
	background-repeat: no-repeat;

    background-size:cover;
}

.container {
	padding-top : 100px;
}

.topnav {
	overflow: visible;
	background-color: #333;
}

.dropdwon {

}
</style>

</head>

<body>
	<div class="topnav">
		
		<a  class="active" href="/operatorHome" >${operatorname }</a>  
		<a  href="/registerMovie">Add Movie</a> 
		<a  href="/theatre">Add Theatre</a> 
		<a  href="/registerShow">Add Show</a> 
		

		<div class="dropdown">
			<button class="dropbtn">History  </button>
			<div class="dropdown-content">
				<a href="/registered/movies">Movies</a> 
				<a href="/registered/theatres">Theatres</a> 
				<a href="/registered/shows">Shows</a>
			</div>
		</div> 

		
		<a  href="/operatorLogout">Log Out</a> 
	</div>

<div>
		
		<h2 style="text-align: center; font-size: 36px; color:white;" >Operator Home Page</h2>
	</div>
</body>
</html>