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


<title>Booking</title>


<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<!-- <script type="text/javascript" src="js/modernizr.custom.79639.js"></script> -->
<!-- <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700'
	rel='stylesheet' type='text/css' /> -->

<link href="${contextPath}/resources/css/dropdown.css" rel="stylesheet">
<link href="${contextPath}/resources/css/navigationbar.css" rel="stylesheet">
<link href="${contextPath}/resources/css/checkbox.css" rel="stylesheet"> 
<script src="${contextPath}/resources/js/dropdown.js"></script>
<%-- <script src="${contextPath}/resources/js/checkbox.js"></script> --%>


</head>

<script type="text/javascript">

	function book()
	{
		debugger;
		var booked = false;
		
		
        var date = $("#date-dropdown li a.selectedLi").text();
		
		var city = $("#city-dropdown li a.selectedLi").val();
		
		var theatreId = $("#theatre-dropdown li a.selectedLi").parent().attr('id');
		
		var movieId = $("#movie-dropdown li a.selectedLi").parent().attr('id');
		
		var showId = $("#show-dropdown li a.selectedLi").parent().attr('id');
	
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
		
		return booked;
		 
	}

</script>



<script type="text/javascript">
   
    function loadTheatres(city)
    {
        var date = $("#date-dropdown li a.selectedLi").text();
        //var city = $("#city-dropdown li a.selectedLi").text();
		//alert(date);
		
		//alert("inside loadtheatres... "+ city);
		//debugger;
        $.ajax({

				type : "GET",
				url : "/theatres/"+city+"/"+date,
				success : function(data){
					
					//debugger;
            		var theatre=$('#theatre-dropdown');
            		var li="";
            		
            		theatre.empty();
            		/* $('#movie-dropdown').empty();
            		$('#show-dropdown').empty();
            		$('#seat-list').empty(); */

            		for(var i=0; i<data.length; i++)
            		{
                		li = li + '<li id="'+data[i].theatreId+'" ><a>'+data[i].name +"-"+ data[i].address+'</a></li>'; 
            		
            		}
            		theatre.append(li);
        		},
        		error:function()
        		{
            		alert("theatre error");
        		}
        
        });

    }
    
    function loadMovies(theatreId)
    {
		
    	var date = $("#date-dropdown li a.selectedLi").text();
    	//var theatreId = $("#theatre-dropdown li a.selectedLi").parent().attr('id');
		alert(date);
		
		//alert("theatreId:" +theareId);
        $.ajax({

				type : "GET",
				url : "/movies/"+theatreId+"/"+date,
				success : function(data){
					
            		var movie=$('#movie-dropdown');
            		var li="";
            		//debugger;
            		movie.empty();

            		for(var i=0; i<data.length; i++)
            		{
                		li = li + '<li id="'+data[i].movieId+'" ><a>'+data[i].name+'</a></li>'; 
            		
            		}
            		movie.append(li);
            		
        		},
        		error:function()
        		{
            		alert("movie error");
        		}
        
        });

    }
    
    function loadShows(movieId)
    {
    	//var movieId = $("#movie-dropdown li a.selectedLi").parent().attr('id');
        var theatreId = $("#theatre-dropdown li a.selectedLi").parent().attr('id');
        var date = $("#date-dropdown li a.selectedLi").text();
        
        //alert("inside loadshows : theatreid="+theatreId+", date="+date);
       
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
			success : function(data){

        		var show=$('#show-dropdown');
        		var li="";
        		//debugger;
        		show.empty();

        		for(var i=0; i<data.length; i++)
        		{
					
							li = li + '<li id="'+data[i].showId+'" ><a>'
							+ "Start Time: " + data[i].startTime
							+ " ||| End Time: " + data[i].endTime + " ||| Price:"
							+ data[i].price + " ||| Discount: "
							+ data[i].discount + '</a></li>';

				}
				show.append(li);
			},
			error : function() {
				alert("show error");
			}

		});

	}
    
    function loadSeats(showId)
    {
    	//var showId = $("#show-dropdown li a.selectedLi").parent().attr('id');
    	
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

        		for(var i=0; i<myData.length; i++)
        		{
							option = option + "<option value='"+myData[i].seatId + "'>"
							+ "Seat Number: " + myData[i].seatNumber
							+ " ||| Seat Class : " + myData[i].seatClass
							+ "</option>";
				}
				seat.append(option);

			},
			error : function() {
				alert("seat error");
			}

		});

	} 
    
</script>

<script>
/* $(document).ready(function(){
$('#theatre-dropdown').delegate("li a","click", function(){
	
	alert("ASasasasa");

	$("#theatre-dropdown li a").removeClass("selectedLi");
	
	$(this).addClass('selectedLi');
	
	var text = $(this).parent().text();
	var theatreId = $(this).parent().attr('id');
	alert("theatre name:" + text);
	alert("theatreId:"+theatreId); 
	
	loadMovies(theatreId);
}); */


</script> 

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
		<a class="active">${username}</a> 
		<a href="${path}/profile">Profile</a> 
		<a href="${path}/history">History</a> 
		<a href="${path}/logout">Log Out</a>
	</div>
	 
	
	<%-- <div class="topnav" id="topnav-guest">
		<a class="active">Guest</a> 
		<a href="${path}/welcome">Home</a> 
		<a  href="${path}/userLogin">Login</a>
	</div> --%>

	<div class="container">
		<form:form name="bookingForm" method="get"
			action="${path}/generateTicket" modelAttribute="bookingForm"
			onsubmit="return book()">

			<br><br>
			<div id="date-dropdown-div" class="wrapper-dropdown-3" >
				<span>Date</span>
				<ul class="dropdown" id="date-dropdown">
					<c:forEach items="${dateList}" var="date">
						<li id="{date}"><a>${date}</a></li>
					</c:forEach>
				</ul>
				
				<%-- <form:hidden id="selected-date" path="date"/> --%>
			</div>
			
			<br><br>
			
			<div id="city-dropdown-div" class="wrapper-dropdown-3" >
				<span>City</span>
				<ul class="dropdown" id="city-dropdown">
					<c:forEach items="${cityList}" var="city">
						<li id="${city}"><a>${city}</a></li>
					</c:forEach>
				</ul>
				
				<%-- <form:hidden id="selected-city" path="city"/> --%>
			</div>

			<br><br>
			
			<div id="theatre-dropdown-div" class="wrapper-dropdown-3" >
				<span>Theatre</span>
				<ul class="dropdown" id="theatre-dropdown"></ul>
				
				<%-- <form:hidden id="selected-theatre" path="theatreId"/> --%>
			</div>

			<br><br>
			<div id="movie-dropdown-div" class="wrapper-dropdown-3" >
				<span>Movie</span>
				<ul class="dropdown" id="movie-dropdown">
					
				</ul>
				
				<%-- <form:hidden id="selected-movie" path="movieId"/> --%>
			</div>
			
			<br><br>
			<div id="show-dropdown-div" class="wrapper-dropdown-3" >
				<span>Show</span>
				<ul class="dropdown" id="show-dropdown">
					
				</ul>
				
				<%-- <form:hidden id="selected-show" path="showId"/> --%>
			</div>
			
			<%-- <div id="seat-dropdown-div" class="wrapper-dropdown-2" >
				<span>Seat</span>
				<ul class="dropdown" id="seat-dropdown">
					<li><form:checkbox path="seatIdList" value="Java"/>dadsad</li>
					<li><form:checkbox path="seatIdList" value="Java"/>dadsad</li>
					<li><form:checkbox path="seatIdList" value="Java"/>dadsad</li>
				
				</ul>
				
				<form:hidden id="selected-show" path="seatIdList"/>
			</div> --%>

			<br><br>
			<div >
				<label>Seats:</label>
				 <form:select size="10" id="seat-list" path="seatIdList" multiple="multiple">
					<option >Select seat</option>
					
				</form:select>
			</div>
		
			<br><br>
			<div id="guest">
				<p>To continue as a guest, provide the following details</p>
				<div class="form-group">
					<form:input id="username" path="userName"
						placeholder="Enter your username" class="form-control" />
				</div>

				<div class="form-group">
					<form:input id="email" path="email"
						placeholder="Enter your email address" class="form-control" />
				</div>
			</div>

			<p>
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
			</p>

			<%-- <p>
				<form:hidden id="bookingId" path="bookingId" value="${bookingId}"/>
			</p> --%>

			<%-- <form:checkbox path="seatIdList" value="-1"/>abcd --%>



		</form:form>

		<div style="text-align: center;">

			<a href="${path}welcome"><button class="btn">HOME</button></a>
		</div>

	</div>




</body>

</html>