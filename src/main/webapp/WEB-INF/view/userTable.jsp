<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>operator table</title>

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
	padding-top : 100px;
}
</style>

<style type="text/css">
    table {
    width:60%;
    padding: 0;
    margin: 0;
    border: 1;
    border-collapse: collapse;
    }
	tr
	{
	font-size: 130%
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
		<a  href="${path}/adminHome">${adminname}</a> 
		<a  href="${path}OperatorList">List All Operators</a>
		<a class="active" href="${path}/userList">List All Users</a>
		<a href="${path}/adminLogout">Log Out</a>
		</div>
	
	<center>
		
	<br>
	<br>
	<div>
		<h1 style="text-align: center;color: red;">List Of Users</h1>
	</div>
	<br>
	<br>

		<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>email</th>
				<th>gender</th>
				<th>age</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					
					<td>${user.userId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${user.email}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${user.gender}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>${user.age}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	
		</center>
</body>
</html>