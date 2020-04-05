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

<style>
body {
	padding-top : 0px;
	font-size : 14px !important;
}

.container {
	padding-top : 100px;
}

.topnav {
	overflow: visible;
	background-color: #333;
}

.dropdown button {
	background-color: #337ab7;
}
</style>


<style type="text/css">
    table {
    width:90%;
    padding: 0;
    margin: 0;
    border: 1;
    border-collapse: collapse;
    }
	tr
	{
	font-size: 100%
	}
    td {
    width:176px;
    padding: 0 10px 0 0;
    margin: 0;
    border: 0;
    /* font-size: 150% */
    }
    td.last {
    padding: 0;
    margin: 0;
    border: 0;
    }

</style>

</head>

<body>
	<div class="topnav">
		
		<a  href="/operatorHome" >${operatorname }</a>  
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

	<div align="center">
		
	<br>
	<br>
	<div>
		<h1 style="text-align: center;color: red;">List Of Registered Shows</h1>
	</div>
	<br>
	<br>

		<table>
		<thead>
			<tr>
					<th>Show Id</th>
					<th>Movie Name(Id)</th>
					<th>Theatre Name(Id)</th>
					<th>Screen Name(Id)</th>
					<th>Running Date</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Start Time</th>
					<th>End Time</th>
					<th>Price</th>
					<th>Discount</th>
				</tr>
			</thead>
			
			<c:forEach items = "${showDetailList}" var="showDetail">
			
			<tbody>
				<tr>
					<td>${showDetail.showId}</td>
					<td>${showDetail.movieName}(${showDetail.movieId})</td>
					<td>${showDetail.theatreName}-${showDetail.theatreAddress}(${showDetail.theatreId})</td>
					<td>${showDetail.screenName}(${showDetail.screenId})</td>
					<td>${showDetail.currDate}</td>
					<td>${showDetail.startDate}</td>
					<td>${showDetail.endDate}</td>
					<td>${showDetail.startTime}</td>
					<td>${showDetail.endTime}</td>
					<td>${showDetail.price}</td>
					<td>${showDetail.discount}</td>


				</tr>
				
			</c:forEach>
		</tbody>
		</table>
	
	</div>
</body>
</html>