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



<title>Operator table</title>


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
    width:30%;
    padding: 0;
    margin: 0;
    border: 1;
    border-collapse: collapse;
    }

    td {
    width:176px;
    padding: 0 10px 0 0;
    margin: 0;
    border: 0;
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
		<a class="active" href="${path}OperatorList">List All Operators</a>
		<a href="${path}/userList">List All Users</a>
		<a href="${path}/adminLogout">Log Out</a>
	</div>
	
	<h2 style="text-align: center; color: #005c99;" >Log In</h2>
	
	<div align="center">
		<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>password</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${operators}" var="operator">
				<tr>
					<td>${operator.operatorId}</td>
					<td>${operator.operatorName}</td>
					<td>${operator.password}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	
	
		
</body>
</html>