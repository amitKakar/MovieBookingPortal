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

<script>
if(!(session_id()) header("Location: /operatorLogin");
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
<link href="${contextPath}/resources/css/navigationbar.css" rel="stylesheet">
<link href="${contextPath}/resources/css/font.css" rel="stylesheet">
<link href="${contextPath}/resources/css/historyDropdown.css" rel="stylesheet">


<style>
body {
	padding-top : 0px;
	background-image: url("${contextPath}/resources/images/img15.jpeg");
	
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

#availability-div label:after {
    color: #e32;
    content: ' *';
    display:inline;
    /* font-size : 22px; */

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
</style>
    
</head>

<script type="text/javascript">

$(document).ready(function(){
		
	
	var availabel = true;

   $("#moviename").blur(function(){
	   
	  var movieName = $("#moviename").val().toUpperCase();
	  
      if(movieName.length > 3)
      {
         $("#p").html("checking...");

         $.ajax({
            
            type: "GET",
            url: "/movie/"+movieName,
           
            //data: {userName:userName},
            success: function(response)
            {
				
				$("#p").html('<font color="red" size="20">'+'This movie is already registered'+'</font>');
				
				availabel= false;

             },
            error: function (response) {
            	$("#p").html('<font color="green" size="20">'+'Available'+'</font>');
            	
            	availabel = true;
            }
         });
      }

    });
   
   
    window.checkAvailability = function()
	{
		return availabel;
	}

});
</script>


<script>
	function validateform() {
		var name = document.movieRegistrationForm.name.value;
		var genre = document.movieRegistrationForm.genre.value;
		var duration = document.movieRegistrationForm.duration.value;
		var language = document.movieRegistrationForm.language.value;
		
		if (name == null || name == "") {
			alert("Name can't be blank");
			return false;
		} else if (genre == null || genre == "") {
			alert("genre can't be blank");
			return false;
		} else if (duration<0) {
			alert("Duration can't be 0");
			return false;
		} else if (language == null || language == "") {
			alert("you must specify the language");
			return false;
		}
		
		var available = checkAvailability();
		
		if(available == false) {
			alert("This movie is already registered...!!")
			
			return false;
		}
		
		return true;
	}
</script>



<body>


	<div class="topnav">
		<a  href="/operatorHome" >${operatorname }</a> 
		<a  class="active" href="/registerMovie">Add Movie</a> 
		<a  href="/theatre">Add Theatre</a> 
		<a  href="/registerShow">Add Show</a> 
		
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
		<form:form name="movieRegistrationForm" method="post" class="form-signin"
			action="${path}/movie" modelAttribute="movieForm"
			onsubmit="return validateform()">
			
			
			<h2 style="text-align: center; font-size: 36px; color:white;" >Register Your Movie</h2>
			
			<br>
			<div class="form-group" id="availability-div">

				<label><font size="5" color="white">Movie Name:</font></label>
					<form:input path="name" placeholder="Enter movie name"
					class="form-control" id="moviename"/>
					
					<div id="check">
						<p id="p"></p>
					</div>
			</div>
			
			<div class="form-group" id="required">
				<label><font size="5" color="white">Genre:</font></label>
				<form:input path="genre" placeholder="Enter Genre"
					class="form-control" />
			</div>
			<div class="form-group" id="required">
				<label><font size="5" color="white">Duration(minutes):</font></label>
				<form:input path="duration" placeholder="Enter Duration"
					class="form-control" />
			</div>
			<div class="form-group" id="required">
				<label><font size="5" color="white">Language:</font></label>
				<form:input path="language" placeholder="Enter language"
					class="form-control" />
			</div>
			<br>
			<p>
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
			</p>

		</form:form>
		
		<%-- <div style="text-align: center;">

			<a href="${path}welcome"><button class="btn">HOME</button></a>
		</div> --%>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script> 
	
</body>
</html>