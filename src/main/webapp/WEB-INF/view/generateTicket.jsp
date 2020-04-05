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

.heading {
	color : #002e4d;
	font-size : 22px;
	/* margin-left: 44%; */
}

.detail {
	font-size : 18px;
}

#history {
    position: absolute;
    left: 50%;
    margin-left: -100px;
}

/******Print******/
@media print {
   body * {
    visibility: hidden;
  } 
  #print-div {
    visibility: visible;
  }

}

</style>
    
</head>


<script type="text/javascript">

$(document).ready(function(){

	
	var flag= false;
	
	 $.ajax({
         
         type: "GET",
         url: "/userType",
        
         //data: {userName:userName},
         success: function(response)
         {
			//alert("success");
			$("#guest").hide();
			$("#topnav-guest").hide();
			

          },
         error: function (response) {
        	
         	//alert("error");
         	 $("#topnav").hide();

         }
         });
	
});
</script> 


<body>
	
	<div class="topnav" id="topnav">
		<a href="/bookingPage">${username}</a> 
		<a href="${path}/profile">Profile</a> 
		<a class="active" href="${path}/history">History</a> 
		<a href="${path}/logout">Log Out</a>
	</div>
	
	<div class="topnav" id="topnav-guest">
		 
		<a href="${path}/welcome">Home</a> 
		<a href="/bookingPage">Guest Browsing</a>
		<a href="${path}/register">User Signup</a> 
		<a href="${path}/userLogin">User Login</a>
	</div>
	
	<div id="print-div">
	
	<div>
	<h1 style="text-align: center;color: red;">Ticket </h1>
	</div>
	
	<div id="main" align="center">
	<div>
		<h2>Details: </h2>
	</div>
	
	<br><br>
	
	<div id = "history">
	<div style="text-align:left">
		<label class="heading">Booking Id : </label><label class="detail">&nbsp;&nbsp;${booked.bookingId}</label>
		<br>
		<label class="heading">Theatre name:  </label><label class="detail">&nbsp;&nbsp;${theatreName }-${theatreAddress}</label>
		<br>
		<label class="heading">Movie name:  </label><label class="detail">&nbsp;&nbsp;${movieName }</label>
		<br>
		<label class="heading">Screen name:  </label><label class="detail">&nbsp;&nbsp;${screenName }</label>
		<br>
		<label class="heading">Date:  </label><label class="detail">&nbsp;&nbsp;${booked.date }</label>
		<br>
		<label class="heading">Time:  </label><label class="detail">&nbsp;&nbsp;${booked.time }</label>
		<br>
		<label class="heading">Number of tickets:  </label><label class="detail">&nbsp;&nbsp;${booked.numberOfTickets }</label>
		<br>
		<label class="heading">Seat Number:  </label><label class="detail">&nbsp;&nbsp;${booked.seatNumberListString }</label>
		<br>
		<label class="heading">Price:  </label><label class="detail">&nbsp;&nbsp;${booked.price }</label>
		<br>
		<label class="heading">Total Price:  </label><label class="detail">&nbsp;&nbsp;${booked.totalPrice }</label>
		<br>
		<label class="heading">Discount:  </label><label class="detail">&nbsp;&nbsp;${booked.discount }</label>
		<br>
		<label class="heading">FinalPrice:  </label><label class="detail">&nbsp;&nbsp;${booked.finalPrice }</label>
		<br>
		
		<br>
		<br>
	</div>
	
	</div>
	
	</div>
	
	</div>
	<div>
	<!-- <p>
		<a href=""><button class="btn" onClick="window.print()"><font size="3">Print</font></button></a>
	</p>  -->
	</div>
	

</body>
</html>