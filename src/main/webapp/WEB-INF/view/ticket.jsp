<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REGISTER SUCCESS</title>
</head>

<body>
	
	<center>
	
	<div>
	<h2 style="text-align: center;color: red;"> ${username} </h2>
	</div>
	<br>
	<br>
	<div>
	<h2 style="text-align: center;color: red;">Ticket</h2>
	</div>
	
	<div>
		<h2>Details: </h2>
	</div>
	
	<div>
		<label>Username : ${booked.userName}</label>
		<br>
		<label>Email : ${booked.email}</label>
		<br>
		<label>Theatre name: ${theatreName }</label>
		<br>
		<label>Movie name: ${movieName }</label>
		<br>
		<label>Screen name: ${screenName }</label>
		<br>
		<label>Date: ${booked.date }</label>
		<br>
		<label>Time: ${booked.time }</label>
		<br>
		<label>Price: ${booked.price }</label>
		<br>
		<label>Discount: ${booked.discount }</label>
		<br>
		<label>FinalPrice: ${booked.finalPrice }</label>
		<br>
		<label>Seat List: ${booked.seatNumberListString }</label>
		<br>

		<br>
	</div>
	
	<p>
		<a href="${path}/bookingPage"><button class="btn"><font size="3">User Home</font></button></a>
	</p>
	
	</center>

</body>
</html>