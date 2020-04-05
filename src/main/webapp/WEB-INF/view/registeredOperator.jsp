<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>

<script>
if(${adminname} == "" || ${adminname} == null) {
	window.location.replace("/adminLogin");
}
</script>
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
    width:10%;
    padding: 0;
    margin: 0;
    border: 1;
    border-collapse: collapse;
    }
	tr
	{
	font-size: 150%
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
		<a class="active" href="${path}OperatorList">List All Operators</a>
		<a href="${path}/userList">List All Users</a>
		<a href="${path}/adminLogout">Log Out</a>
	</div>
	
	<center>
	

	<br>
	<br>
	<div>
	<h1 style="text-align: center;color: red;">List Of Operators</h1>
	</div>
	<br><br>
	
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
			</tr>
			</thead>
			
			<c:forEach items="${operators}" var="operator">
			
			<tbody>
				<tr>
					<td>${operator.operatorId}</td>
					<td>${operator.operatorName}</td>
				</tr>	
			</c:forEach>
		</tbody>
		</table>

	
	</center>

</body>
</html>