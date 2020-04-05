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

.container {
	padding-top : 100px;
}
</style>

<style type="text/css">
    table {
    width:85%;
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

<script>
function cancelTicket(bookingId)
{
	//alert(bookingId);
	var cancelled = false;
	 $.ajax({

			type : "GET",
			url : "/cancelTicket/"+bookingId,
			async:false,
			success : function(data){
				
				alert("Ticket cancelled successfully");
				cancelled =true;
				
 			},
 			error:function()
 			{
     			alert("Unable to cancel ticket");
 			}
	});	
	 
	if(cancelled == true){
		location.reload();
	}
}
</script>

<script type="text/javascript">

$(document).ready(function(){

	
	var flag= false;
	var guest=false;
	
	 $.ajax({
         
		 
         type: 'GET',
         url: "/userType",
        
         //data: {userName:userName},
         success: function(response)
         {
        	 
			//alert("not a guest");
			$("#guest").hide();
			$("#topnav-guest").hide();
			guest=false;

          },
         error: function (response) {
        	 //debugger
         	//alert("guest");
         	guest=true;
         	
         	$("#topnav").hide();
         	
         	
         }
         });
	
	 	window.guestOrNot = function() {
		 
			return guest;
		}
});
</script> 

<body>	

		<div class="topnav" id="topnav">
		<a href="${path}/bookingPage">${username}</a> 
		<a href="${path}/profile">Profile</a> 
		<a class="active" href="${path}/history">History</a> 
		<a href="${path}/logout">Log Out</a>
		</div>
		
			
	<div class="topnav" id="topnav-guest">
		 
		<a href="${path}/welcome">Home</a> 
		<a href="/bookingPage">Guest Browsing</a>
		<a class="active" href="${path}/generateTicket/guest">Generate Ticket</a>
		<a href="${path}register">User Signup</a>
		<a href="${path}/userLogin">User Login</a>
	</div>
	
	<center>
		
	<br>
	<br>
	<div>
		<h1 style="text-align: center;color: red;">History</h1>
	</div>
	<br>
	<br>

		<table>
		<thead>
			<tr>
				<th>Booking Id</th>
				<th>Movie Name</th>
				<th>Theatre Name</th>
				<th>Screen Name</th>
				<th>Date</th>
				<th>Time</th>
				<th>Price</th>
				<th>Discount</th>
				<th>Final Price</th>
				<th>Seats</th>
				
			</tr>
			</thead>
			
			<c:forEach items = "${historyList}" var="history">
			
			<tbody>
				<tr>
					<td>${history.bookingId}</td>
					<td>${history.movieName}</td>
					<td>${history.theatreName}-${history.theatreAddress}</td>
					<td>${history.screenName}</td>
					<td>${history.date}</td>
					<td>${history.time}</td>
					<td>${history.price}</td>
					<td>${history.discount}</td>
					<td>${history.finalPrice}</td>
					<td >${history.seatNumberListString}</td>
					
					<td><a href="${path}/generateTicket/${history.bookingId}"><button class="btn">Generate Ticket</button></a></td>
					<td><button value="${history.bookingId}" class="btn" onclick="cancelTicket(this.value)">Cancel Ticket</button></td>
					</tr>
				
			</c:forEach>
		</tbody>
		</table>
	
		</center>
</body>
</html>