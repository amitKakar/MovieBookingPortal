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



<title>Booking Page</title>


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
	background-image: url("${contextPath}/resources/images/img22.jpeg");
	
	background-repeat: no-repeat;

    background-size:cover;
}

label {
    font-weight: normal;
}

.container {
	padding-top : 10px;
}

.form-group select {
	width : 300px;
	height : 30px;
}

.form-group option {
	width : 300px;
	height : 30px;
	font-size: 18px;
}

</style>

</head>



<script type="text/javascript">

$(document).ready(function(){
	
	$('#booking-button').click(function(){
	
		//alert("inside click function");

		var date = $("#date-list").val();
		
		var city = $("#city-list").val();
		
		var theatreId = $("#theatre-list").val();
		
		var movieId = $("#movie-list").val();
		
		var showId = $("#show-list").val();
	
		var seatIdList = $("#seat-list").val();
		
		var username = $("#username").val();
	
		var email = $("#email").val();
		
		var jsonObject = {
				
				"userName" : username,
				"email" : email,
				"date" : date,
				"city" : city,
				"theatreId" : theatreId,
				"movieId" : movieId,
				"showId" : showId,
				"seatIdList" : seatIdList
		}
		
		var validation = validateForm(date, city, theatreId, movieId, showId, seatIdList, username, email);
		//debugger
		//alert("validation retrun value="+validation);
		
		if(validation == true) {
			//alert("insidebooking");
			//alert("validation retrun value="+validation);
			
			var booked = false;
			
			$.ajax({
	     		type : "POST",
	     		url: "/makePayment",
	     		async : false,
	     		contentType:"application/json; charset=utf-8",
	     		data: JSON.stringify(jsonObject),
	     		dataType: 'json',

	     		success: function(data) {

	         		alert('You ticket booked successfully...!!');
	         		booked = true;
	        
	     		},
	     		error:function()
	     		{
	         		alert("Unable to book ticket due to some problem. Kindly try again...!!");
	         		
	         		booked = false;
	     		}
	     
	     	}); 
			
			if(booked == true)
		    {
		    	window.location.replace("/generateTicket");
		    }
			else 
			{
				
				location.reload();
			}
		}
		/* else {
			
			//alert("invalid details");
		} */

	});
});
		
</script>

<script type="text/javascript">
	
	function validateForm(date, city, theatreId, movieId, showId, seatIdList, username, email)
	{
		//alert("inside validate");
		
		if(date == -1){
			alert("Please select the date..!!")
			return false;
		}
		else if(city == -1){
			alert("Please select the city..!!")
			return false;
		}
		else if(theatreId == -1){
			alert("Please select the theatre..!!")
			return false;
		}
		else if(movieId == -1){
			alert("Please select the movie..!!")
			return false;
		}
		else if(showId == -1){
			alert("Please select the show..!!")
			return false;
		}
		else if(seatIdList == null || seatIdList == -1){
			alert("Please select the seats..!!")
			return false;
		}
		//debugger
		var regularExpression = /\S+@\S+\.\S+/;
		var guest = guestOrNot();
		
		if(guest == true) {
			
			//alert("guest inside validate");
			
			var available= checkAvailability();
			//alert("username available ="+ available);
			
			if (username == null || username == "" || username.length<4) {
				alert("Invalid Username");
				return false;
			} else if( available == false){
				alert("This username already exist..!!");
				return false;
			}else if (email == null || email == "") {
				alert("Email address can't be blank..!!");
				return false;
			}else if (regularExpression.test(email) == false) {
				alert("Invalid email address.");
				return false;
			}  
		}
		
		return true;
		
	}

</script>

<script type="text/javascript">
   
   
    function loadCities(date)
    {
    	$.ajax({

			type : "GET",
			url : "/cities/"+date,
			success : function(data){

        		var city=$('#city-list');
        		var option="";
        		
        		option = option + "<option value=-1>"
				+"Select City"
				+ "</option>";
				city.append(option);
        		
				city.empty();
        

        		for(var i=0; i<data.length; i++)
        		{
            	option = option + "<option value='"+data[i] + "'>"+data[i] +"</option>";
        		}
        		city.append(option);
    		},
    		error:function()
    		{
    			alert("error:unable to get any record");
    		}
    
    });

    }
   
    function loadTheatres(city)
    {
        var date = $("#date-list").val();

        $.ajax({

				type : "GET",
				url : "/theatres/"+city+"/"+date,
				success : function(data){
            		//alert('inside function');
            		var theatre=$('#theatre-list');
            		var option="";
            		
            		option = option + "<option value=-1>"
    				+"Select Theatre"
    				+ "</option>";
    				theatre.append(option);
            		
            		theatre.empty();
            

            		for(var i=0; i<data.length; i++)
            		{
                	option = option + "<option value='"+data[i].theatreId + "'>"+data[i].name +"-"+ data[i].address+"</option>";
            		}
            		theatre.append(option);
        		},
        		error:function()
        		{
        			alert("error:unable to get any record");
        		}
        
        });

    }
    
    function loadMovies(theatreId)
    {

    	var date = $("#date-list").val();

        $.ajax({

				type : "GET",
				url : "/movies/"+theatreId+"/"+date,
				success : function(data){
					
            		var movie=$('#movie-list');
            		var option="";
            		
            		option = option + "<option value=-1>"
    				+"Select Movie"
    				+ "</option>";
    				movie.append(option);
            		
            		movie.empty();

            		for(var i=0; i<data.length; i++)
            		{
                		option = option + "<option value='"+data[i].movieId + "'>"+data[i].name + "</option>";
            		}
            		movie.append(option);
            		
        		},
        		error:function()
        		{
        			alert("error:unable to get any record");
        		}
        
        });

    }
    
    function loadShows(movieId)
    {

        var theatreId = $('#theatre-list').val();
        var date = $('#date-list').val();
       
        var jsonData = {
        		currDate : date,
        		theatreId : theatreId,
        		movieId : movieId
        } 
        
        $.ajax({

    		type : "POST",
			contentType : "application/json",
			url :"/getShows",
			data : JSON.stringify(jsonData),
			dataType : 'json', 
			success : function(myData){

        		var show=$('#show-list');
        		var option="";
        		
        		option = option + "<option value=-1>"
				+"select show"
				+ "</option>";
				show.append(option);
				
        		show.empty();
        		
				
				
        		for(var i=0; i<myData.length; i++)
        		{

            		
							option = option + "<option value='"+myData[i].showId + "'>"
							+ "Start time: "+myData[i].startTime + "||| End time: " + myData[i].endTime
							+ "||| Price: " + myData[i].price + "||| Discount: " + myData[i].discount
							+ "</option>";
				}
				show.append(option);
			},
			error : function() {
				alert("error:unable to get any record");
			}

		});

	}
     
    
    function loadSeats(showId)
    {
        
        $.ajax({

    		type : "GET",
			url :"/seatResult/"+showId,
			success : function(myData){
        		//alert('inside function');
        		var seat=$('#seat-list');
        		var option="";
        		
        		option = option + "<option value=-1>"
				+"select seat"
				+ "</option>";
				seat.append(option);
        		
        		seat.empty();
        		//debugger;
        		if(myData.length == 0){
        			
        			alert("No seats are available..!!");
        		}
        		else
        		{
        			for(var i=0; i<myData.length; i++)
            		{
    							option = option + "<option value='"+myData[i].seatId + "'>"
    							+ "Seat Number: " + myData[i].seatNumber
    							+ " ||| Seat Class : " + myData[i].seatClass
    							+ "</option>";
    				}
    				seat.append(option);
        		}

        		

			},
			error : function() {
				alert("No seats are available..!!");
			}

		});

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

<script>

/* $(document).ready(function() {

$('#date-list').fancySelect();

}); */

</script>

<script type="text/javascript">

$(document).ready(function(){
		
		var available= false;
		
	   $("#username").blur(function(){
		   
		  var userName = $("#username").val().toUpperCase();

	      if(userName.length > 3)
	      {
	         $("#p").html("checking...");

	         $.ajax({
	            
	            type: "GET",
	            url: "/user/"+userName,
	           
	            //data: {userName:userName},
	            success: function(response)
	            {
					//alert("success")
					
					$("#p").html('<font color="red">'+'Username Already in use'+'</font>');
					
					available= false;

	             },
	            error: function (response) {
	            	$("#p").html('<font color="green">'+'Available'+'</font>');
	            	
	            	available = true;
                }
	         });
	      }

	    });
	   
	   
	    window.checkAvailability = function()
		{
			return available;
		}

});
</script> 


<body>

	<div class="topnav" id="topnav">
		<a class="active">${username}</a> 
		<a href="${path}/profile">Profile</a> 
		<a href="${path}/history">History</a> 
		<a href="${path}/logout">Log Out</a>
	</div>
	
	<div class="topnav" id="topnav-guest">
		 
		<a href="${path}/welcome">Home</a> 
		<a class="active">Guest Browsing</a>
		<a href="${path}/generateTicket/guest">Generate Ticket</a>
		<a href="${path}register">User Signup</a>
		<a href="${path}/userLogin">User Login</a>
	</div>
	

	<div class="container">
	
	<h2 style="text-align: center;  font-size: 36px; color:white;" >Book Your Ticket</h2>
	
		<form:form name="bookingForm" class="form-signin" modelAttribute="bookingForm">
			
			 <br>
			 <br>
			
			<div class="form-group" >
				<label><font size="5" color="white">Date:</font></label>
				 <form:select id="date-list" path="date" onchange="loadCities(this.value)">
					<option value="-1">Select date</option>
					<c:forEach items="${dateList}" var="date">
						<option value="${date}">${date}</option>
					</c:forEach>
				</form:select>
			</div>
			
			<div class="form-group">
				<label><font size="5" color="white">Cities:</font></label>
				 <form:select id="city-list" onchange="loadTheatres(this.value)" path="city">
					<option value="-1">Select City</option>
					
				</form:select>
			</div>
			
			<div class="form-group">
				<label><font size="5" color="white">Theatre:</font></label>
				 <form:select id="theatre-list" onchange="loadMovies(this.value)" path="theatreId">
					<option value="-1">Select theatre</option>
					
				</form:select>
			</div>
			
			<div class="form-group">
				<label><font size="5" color="white">Movies:</font></label>
				 <form:select id="movie-list" onchange="loadShows(this.value)" path="movieId">
					<option value="-1">Select movie</option>
					
				</form:select>
			</div>
			
			<div class="form-group">
				<label><font size="5" color="white">Shows:</font></label>
				 <form:select id="show-list" onchange="loadSeats(this.value)" path="showId" >
					<option value="-1">Select show</option>
					
				</form:select>
			</div>

			<div class=""  >
				<label><font size="5" color="white">Seats:</font></label>
				 <form:select size="8" id="seat-list" path="seatIdList" multiple="multiple" style="width: 300px;">
					<option value="-1">Select seat</option>
				</form:select>
			</div>
			
<!-- 			<div class="form-group">
				<label>Seats:</label>
				 <ul id="seat-list">
				 
				 </ul>
			</div> 
 -->
			
			<div id="guest">
				<p><font size="5" color="white">To continue as a guest, provide the following details</font></p>
				<div class="form-group">
			 		<form:input id="username" path="userName" placeholder="Enter your username" class="form-control" /> 
			 		<div id="check">
					<p id="p"></p>
				</div>
				</div>
			
				<div class="form-group">
			 		<form:input id="email" path="email" placeholder="Enter your email address" class="form-control" /> 
				</div>
			</div>
			
			<p>
				<button class="btn btn-lg btn-primary btn-block" type="button" id="booking-button">SUBMIT</button>
			</p>
				
			<%-- <p>
				<form:hidden id="bookingId" path="bookingId" value="${bookingId}"/>
			</p> --%>
			
			<%-- <form:checkbox path="seatIdList" value="-1"/>abcd --%>
			
			
		
		</form:form>
		
		<%-- <div style="text-align: center;">

			<a href="${path}welcome"><button class="btn">HOME</button></a>
		</div> --%>
		
	</div>
			
</body>

</html>