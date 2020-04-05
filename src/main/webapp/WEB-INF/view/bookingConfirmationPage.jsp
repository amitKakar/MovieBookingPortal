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
	<h2 style="text-align: center;color: red;">Booking Summary</h2>
	</div>
	
	<div>
		<h2>Details: </h2>
	</div>
	
	<div>
		<label>Show ID: ${booking.showId }</label>
		<br>
		<label>Theatre ID: ${booking.theatreId }</label>
		<br>
		<label>Movie ID: ${booking.movieId }</label>
		<br>
		<label>Screen ID: ${booking.screenId }</label>
		<br>
		<label>Date: ${booking.date }</label>
		<br>
		<label>Time: ${booking.time }</label>
		<br>
		<label>Price: ${booking.price }</label>
		<br>
		<label>Discount: ${booking.discount }</label>
		<br>
		<label>FinalPrice: ${booking.finalPrice }</label>
		<br>
		<label>Seat List: ${booking.seatIdListString }</label>
		<br>

		<br>
	</div>
	
	<p>
		<a href="${path}/makePayment"><button class="btn"><font size="3">Book</font></button></a>
	</p>
	
	</center>

</body>
</html>