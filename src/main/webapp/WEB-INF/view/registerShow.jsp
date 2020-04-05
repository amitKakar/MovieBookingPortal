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
<link href="${contextPath}/resources/css/modal.css" rel="stylesheet">

<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<style>
body {
	padding-top : 0px;
	background-image: url("${contextPath}/resources/images/img3.jpg");
	
	background-repeat: no-repeat;

    background-size:cover;
}

label {
    font-weight: normal;
}

#required label:after {
    color: #e32;
    content: ' *';
    display:inline;
    font-size : 22px;

}

.container {
	padding-top : 30px;
}

.topnav {
	overflow: visible;
	background-color: #333;
}

.dropdwon {

}

.form-group select {
	width : 300px;
	height : 30px;
}

.form-group option {

	font-size: 18px;
}
</style>





<style type="text/css">
  table {
    width:100%;
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

<style>
 .ui-datepicker 
         {
            background: #999;
          }

</style>

</head>


<script type="text/javascript">

$(document).ready(function(){

	$("#modal-div").hide();
	   $("#currDate").blur(function(){
		   
		   $("#modal-div").show();
	   });
		   
		 

});
</script> 


<script>
         $(function() {
            $( "#currDate" ).datepicker({ dateFormat: 'yy-mm-dd', changeMonth: true,
                changeYear: true });
            
            $( "#startDate" ).datepicker({ dateFormat: 'yy-mm-dd', changeMonth: true,
                changeYear: true });
            
            $( "#endDate" ).datepicker({ dateFormat: 'yy-mm-dd', changeMonth: true,
                changeYear: true });
            
            $('#startTime').timepicker({ timeFormat: 'HH:mm:ss', interval: 5, scrollbar: true });
            
            $('#endTime').timepicker({ timeFormat: 'HH:mm:ss', interval: 5, scrollbar: true });


         });
</script>

<script type="text/javascript">

function validateForm()
{
	
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var city = $("#city").val();
	var theatreId = $("#theatreId").val();
	var movieId = $("#movieId").val();
	var screenId = $("#screenId").val();
	var currentDate=$("#currDate").val();	
		
	
	if(city == -1){
		alert("Either there is no registered city or you have made an illegal selection...!!!")
		return false;
	}
	else if(theatreId == -1){
		alert("Either there is no registered theatre or you have made an illegal selection...!!!")
		return false;
	}
	else if(movieId == -1){
		alert("Either there is no registered movie or you have made an illegal selection...!!!")
		return false;
	}
	else if(screenId == -1){
		alert("Either there is no registered screen or you have made an illegal selection...!!!")
		return false;
	}
	else if(startTime == null || startTime==' '){
		alert("Invalid Time Format...!!!")
		return false;
	}
	
	else if(endTime == null || endTime==' '){
		alert("Invalid Date Format...!!!")
		return false;
	}

	else if(currentDate == null || currentDate==' '){
		alert("Invalid Date Format...!!!")
		return false;
	}

	else if(startDate == null || startDate==' '){
		alert("Invalid Date Format...!!!")
		return false;
	}

	else if(endDate == null || endDate==' '){
		alert("Invalid Date Format...!!!")
		return false;
	}
	else if(startTime >= endTime){
		alert("Invalid Time Format...!!!")
		return false;
	}
	
	else if(startDate >= endDate){
		alert("Invalid Date Format...!!!")
		return false;
	}
	var availabel= checkAvailability();
	if(availabel == false){
		alert("Show already exist...Please select some other start time...!!! ");
		return false;
	}
	
	
	
}
</script>

<script type="text/javascript">

$(document).ready(function()
{
	
	$("#endTime").blur(function(){
	
		var theatreId = $("#theatre-list").val();
		//alert(theatreId);
		var screenId = $("#screen-list").val();
		//alert(screenId);
		var startTime = $("#startTime").val();
		//alert(startTime);
		var endTime = $("#endTime").val();
		//alert(endTime);
		var currentDate = $("#currDate").val();
		//alert(currentDate );
	
		var jsonObject = {
			
    			"theatreId": theatreId,
    			"screenId" : screenId,
    			"startTimeString" : startTime,
    			"endTimeString" : endTime,
    			"currDateString" : currentDate
   		}
	
		var available = false;
    
    	$.ajax({

    			type : "POST",
    			url: "/showAvailability",
    			contentType:"application/json; charset=utf-8",
    			data: JSON.stringify(jsonObject),
    			dataType: 'json',
    			
    			success: function(data) {
 					
    				$("#p").html('<font color="red">'+'Show is already running on this screen...Please select some other time slot...!!!'+'</font>');
    				available = true;
    			},
    			
    			error:function()
    			{
    				
    				$("#p").html('<font color="green">'+'Available'+'</font>');
    				available = false;
    			}
    
    	});
	});
	
	window.checkAvailability = function()
	{
		return availabel;
	}
	
});
</script>



<script type="text/javascript">

function createTable(){
		var theatreId = $("#theatre-list").val();
		//alert("theatre:"+theatreId);
		var screenId = $("#screen-list").val();
		//alert("screen:"+screenId);
		var currentDate = $("#currDate").val();
		//alert("date:"+currentDate );
	
		var jsonObject = {
			
    			"theatreId": theatreId,
    			"screenId" : screenId,
    			"currDateString" : currentDate
   		}
	
		var available = false; 
    
    	 $.ajax({

    			type : "POST",
    			url: "/getCurrentlyRunningShow",
    			contentType:"application/json; charset=utf-8",
    			data: JSON.stringify(jsonObject),
    			dataType: 'json',
    			
    			success: function(show) {
    				var table="";
    				 
    				$('#table').empty();
    				
    				for(var i=0; i<show.length; i++)
            		{
    					
                		table = table + '<tr>'+
						'<td>'+show[i].showId+'</td>'+
						'<td>'+show[i].movieName+'('+show[i].movieId+')'+'</td>'+
						'<td>'+show[i].theatreName+'-'+show[i].theatreAddress+'('+show[i].theatreId+')'+'</td>'+
						'<td>'+show[i].screenName+'('+show[i].screenId+')'+'</td>'+
						'<td>'+show[i].currDate+'</td>'+
						'<td>'+show[i].startDate+'</td>'+
						'<td>'+show[i].endDate+'</td>'+
						'<td>'+show[i].startTime+'</td>'+
						'<td>'+show[i].endTime+'</td>'+
						'<td>'+show[i].price+'</td>'+
						'<td>'+show[i].discount+'</td>'+
						'</tr>'
            		}
    				$('#table').append(table); 
     			},
    			
    			error:function()
    			{
    				alert("No show is currently running on this screen...!!!")
    			}
    
    	}); 
}

</script> 

<script type="text/javascript">

    function loadTheatres(city)
    {
        $.ajax({
				type : "GET",
				url : "theatres/operator/"+city,
				success : function(data){
 
            		var theatre=$('#theatre-list');
            		var option="";
            		
            		option = option + "<option value=-1>"
    				+"Select Theatre"
    				+ "</option>";
    				theatre.append(option);
            		
            		theatre.empty();
            
            		
    				
            		for(var i=0; i<data.length; i++)
            		{
                		option = option + "<option value='"+data[i].theatreId + "'>"+data[i].name +" - " + data[i].address + "</option>";
            		}
            		theatre.append(option);
        		},
        		error:function()
        		{
            		alert("error");
        		}
        
        });

    }
    
    function loadScreens(theatreId)
    {
        
        $.ajax({
				type : "GET",
				url : "/screens/"+theatreId,
				success : function(data)
				{
					
            		var screen=$('#screen-list');
            		var option="";
            		
            		option = option + "<option value=-1>"
    				+"select screen"
    				+ "</option>";
    				screen.append(option);
            		
            		screen.empty();
            		
            		

            		for(var i=0; i<data.length; i++)
            		{
                		option = option + "<option value='"+data[i].screenId + "'>"+data[i].name + "</option>";
            		}
            		screen.append(option);
        		},
        		error:function()
        		{
            		alert("error");
        		}
        
        });
        
        

    }
   
</script>




<body>

	<div class="topnav">
		<a  href="/operatorHome" >${operatorname }</a> 
		<a  href="/registerMovie">Add Movie</a> 
		<a   href="/theatre">Add Theatre</a> 
		<a  class="active" href="/registerShow">Add Show</a> 
		
		<a  href="/operatorLogout">Log Out</a>

		<div class="dropdown">
			<button class="dropbtn">History  </button>
			<div class="dropdown-content">
				<a href="/registered/movies">Movies</a> 
				<a href="/registered/theatres">Theatres</a> 
				<a href="/registered/shows">Shows</a>
			</div>
		</div>


	</div>


	<div class="container">
	
	<h2 style="text-align: center; font-size: 36px; color:white;" >Register Your Show</h2>
	
		<form:form name="showForm" method="post" class="form-signin"
			action="${path}/addShow" modelAttribute="showForm" onsubmit="return validateForm()" >
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">City:</font></label>
				 <select id="city-list" onchange="loadTheatres(this.value)" >
					<option value = "-1">Select city</option>
					<c:forEach items="${cityList}" var="city">
						<option value="${city}">${city}</option>
					</c:forEach>
				</select>
			</div>
			
			
			<div class="form-group" id="required"> 
				<label><font size="5" color="white">Theatre:</font></label>
				 <form:select id="theatre-list" onchange="loadScreens(this.value)" path="theatreId">
					<option value="-1">Select theatre</option>
					
				</form:select>
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Movies:</font></label>
				 <form:select id="movie-list" path="movieId">
					<option value="-1">Select movie</option>
					<c:forEach items="${movieList}" var="movie">
						<option value="${movie.movieId}">${movie.name}</option>
					</c:forEach>
				</form:select>
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Screens:</font></label>
				 <form:select path="screenId" id="screen-list" >
					<option value="-1">Select screen</option>
					
				</form:select>
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Price:</font></label>
				<form:input path="price" placeholder="Enter the price"
					class="form-control" autofocus="true" />
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Discount(%):</font></label>
				<form:input path="discount" placeholder="Enter discount"
					class="form-control" autofocus="true" />
			</div>
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Current Date(yyyy-MM-dd):</font></label>
				<form:input path="currDateString" id="currDate" placeholder="Enter current date"
					class="form-control" autofocus="true" />
			</div>
			
			
			<div id="modal-div">
			<!-- Trigger/Open The Modal -->
			<button id="myBtn" type="button">Currently running shows on selected screen</button>
			</div>
			<br>
			
			<!-- The Modal -->
			<div id="myModal" class="modal">

				<!-- Modal content -->
				<div class="modal-content">
					<span class="close">&times;</span>
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



						<tbody id="table">

						</tbody>
					</table>
				</div>

			</div>
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Start Date(yyyy-MM-dd):</font></label>
				<form:input path="startDateString" id="startDate" placeholder="Enter start date"
					class="form-control" autofocus="true" />
			</div>
			
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">End Date(yyyy-MM-dd):</font></label>
				<form:input path="endDateString" id="endDate" placeholder="Enter end date"
					class="form-control" autofocus="true" />
			</div>

			

			<div class="form-group" id="required">
				<label><font size="5" color="white">Start time(HH:mm:ss):</font></label>
				<form:input path="startTimeString" id="startTime" placeholder="Enter start time"
					class="form-control" autofocus="true" />
					
				
			</div>
			
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">End time(HH:mm:ss):(end time must be greater than start time)</font></label>
				<form:input path="endTimeString" id="endTime" placeholder="Enter end time"
					class="form-control" autofocus="true" />
					
				<div id="check">
					<p id="p"></p>
				</div>
			</div>
			
				
			<p>
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
			</p>
				
		
		</form:form>
	</div>



<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
	
	createTable();
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</body>
</html>
