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
	background-image: url("${contextPath}/resources/images/img10.jpg");
	
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
    /* font-size : 22px; */

}

#availability-div label:after {
    color: #e32;
    content: ' *';
    display:inline;
    /* font-size : 22px; */

}

#screeAndSeat label:after {
    color: #e32;
    content: ' *';
    display:inline;
    /* font-size : 22px; */

}

#addButton {
	font-size : 17px;
	width : 147px;
	background-color : #337ab7;
	color : white;

}

#removeButton {
	font-size : 17px;
	width : 147px;
	background-color : #337ab7;
	color : white;

}

#myBtn {
	font-size : 17px;
	width : 300px;
	background-color : #337ab7;
	color : white;

}

.container {
	padding-top : 0px;
}

.topnav {
	overflow: visible;
	background-color: #333;
}

.dropdwon {

}
</style>
<!-- 
<style type="text/css">
  table {
    width:50%;
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

</style> -->

</head>



<script type="text/javascript">

$(document).ready(function(){
		
	
	var availabel = true;

   $("#address").blur(function(){
	   
	  var theatreName = $("#theatrename").val().toUpperCase();
	  var address = $("#address").val().toUpperCase();
	  
	  var jsonData = {
		
  			"name" : theatreName,
  			"address" : address
  		} 
	  
      if(address.length > 3)
      {
         $("#p").html("checking...");

         $.ajax({
            
        	type: "POST",
 	        contentType : "application/json; charset=utf-8",
 			data : JSON.stringify(jsonData),
 			dataType : 'json',
 	        url: "/getTheatre",
 	        async : false,
           
            //data: {userName:userName},
            success: function(response)
            {
				
				$("#p").html('<font color="red">'+'This theatre is already registered'+'</font>');
				
				availabel= false;

             },
            error: function (response) {
            	$("#p").html('<font color="green">'+'Available'+'</font>');
            	
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


<SCRIPT>

	function validateform() {

		//alert("I am an alert box!");
		var name = document.registrationForm.name.value;
		var address = document.registrationForm.address.value;
		var city = document.registrationForm.city.value;
		
		if (name == null || name == "") {
			alert("Theatre name can't be blank");
			return false;
		} else if (address == null || address == "") {
			alert("Address name can't be blank");
			return false;
		} else if (city == null || city == "") {
			alert("City name can't be blank");
			return false;
		}
		
		var available = checkAvailability();
		
		if(available == false) {
			alert("This theatre is already registered...!!")
			
			return false;
		}
		
		
		var screenData = getTextBoxValues();
		//alert("inside validater----" + screenData);
		document.getElementById('jsonData').value = screenData;
		
		var numOfScreens = getNumberOfScreens();
		document.getElementById('numberOfScreens').value=numOfScreens;
		
		return true;

	}
	
</SCRIPT>

<script type="text/javascript">

$(document).ready(function(){

    var counter = 2;
		
    $("#addButton").click(function () {

		
	var newTextBoxDiv = $(document.createElement('div'))
	     .attr("id", 'screenDiv' + counter);
                
	newTextBoxDiv.after().html('<br><label><font size="5" color="white">Screen '+ counter+':</font></label><br/>' + 
			
			'<label><font size="5" color="white">Screen Name: </font></label>' +
	      	'<input type="text" name="screenbox' + counter + '" id="screenbox' + counter + '" placeholder="screen name" class="form-control" ><br/>'+
	      	
	      	'<label><font size="5" color="white">Number of Seats: </font></label>'+'<input type="text" name="seatbox' + counter + 
	      	'" id="seatbox' + counter + '" placeholder="number of seats" class="form-control" >');
            
	newTextBoxDiv.appendTo("#screeAndSeat");

				
	counter++;
     });

     $("#removeButton").click(function () {
	if(counter==1){
          alert("No more textbox to remove");
          return false;
       }   
        
	counter--;
			
        $("#screenDiv" + counter).remove();
			
     });
      
      window.getTextBoxValues = function()
      {
    	  var textValues='';
    	  for(i=1; i<counter; i++)
    	  {
    		  if(i == counter-1)
    		  {
    			  textValues +=$('#screenbox' + i).val()+":"+$('#seatbox' + i).val();
    		  }
    		  else
    		  {
    			  textValues +=$('#screenbox' + i).val()+":"+$('#seatbox' + i).val()+",";
    		  }
    	   	  
    	   	  
    	   	  //alert("!!!!!!!!!!!!!!!!!!!!!")
    		}
    	  //alert("***"+textValues);
    	  
    	  return textValues;
      }
      
      window.getNumberOfScreens = function()
      {
    	  //alert("counter="+counter);
    	  return counter-1;
      }

      
  });
</script>

<script type="text/javascript">

$(document).ready(function(){

	$("#modal-div").hide();
	   $("#address").blur(function(){
		   
		   $("#modal-div").show();
	   });
		   
		 

});
</script> 

<script type="text/javascript">

function createTable(){

    	 $.ajax({

    			type : "GET",
    			url: "/registered/theatres/operator",
    			
    			success: function(theatreList) {
    				//alert("List")
    				var table="";
    				 
    				$('#table').empty();
    				
    				for(var i=0; i<theatreList.length; i++)
            		{
    					
                		table = table + '<tr>'+
						'<td>'+theatreList[i].theatreId+'</td>'+
						'<td>'+theatreList[i].name+'</td>'+
						'<td>'+theatreList[i].address+'</td>'+
						'<td>'+theatreList[i].city+'</td>'+
						'<td>'+theatreList[i].numberOfScreens+'</td>'+
						'</tr>'
            		}
    				$('#table').append(table); 
     			},
    			
    			error:function()
    			{
    				alert("No theatre is already registered..!!!")
    			}
    
    	}); 
}

</script> 

<BODY>
	<div class="topnav">
		<a  href="/operatorHome" >${operatorname }</a> 
		<a  href="/registerMovie">Add Movie</a> 
		<a  class="active" href="/theatre">Add Theatre</a> 
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
		
		<h2 style="text-align: center; font-size: 36px; color:white;" >Register your theatre</h2>
		
		<form:form name="registrationForm" method="post" class="form-signin"
			action="${path}/theatreDisplay" modelAttribute="theatreForm"
			onsubmit="return validateform()">
			
			<!-- <div id="modal-div">
			Trigger/Open The Modal
			<button id="myBtn" type="button" class="btn">List of Registered Theatres</button>
			</div>
			<br> -->
			
			<!-- The Modal -->
			<!-- <div id="myModal" class="modal">

				Modal content
				<div class="modal-content">
					<span class="close">&times;</span>
					<table>
						<thead>
							<tr>
								<th>Theatre Id</th>
								<th>Name</th>
								<th>Address</th>
								<th>City</th>
								<th>Number Of Screens</th>
							</tr>
						</thead>



						<tbody id="table">

						</tbody>
					</table>
				</div>

			</div> -->
			
			<br>
			<div class="form-group" id="required">
				<label><font size="5" color="white">Theatre Name:</font></label>
				<form:input path="name" id="theatrename" placeholder="Enter Theatre name"
					class="form-control" autofocus="true" />
			</div>
			<div class="form-group" id="availability-div">
				<label><font size="5" color="white">Address:</font></label>
				<form:input path="address" id="address" placeholder="Enter address"
					class="form-control" />
					
				<div id="check">
					<p id="p"></p>
				</div>
				
			</div>
			
		    <div class="form-group" id="required">
				<label><font size="5" color="white">City:</font></label>
				<form:input path="city" placeholder="Enter city"
					class="form-control" />
			</div>

			<div id='screeAndSeat' class="form-group" >
				<div id="screenDiv1">
					<label><font size="5" color="white">Screen 1: </font></label>
					<br> 
					<label><font size="5" color="white">Screen Name :</font></label>
					<input type='text' id='screenbox1' placeholder="screen name" class="form-control"> 
					<br>
					<label><font size="5" color="white">Number of Seats :</font></label>
					<input type='text' id='seatbox1' placeholder="number of seats" class="form-control">

				</div>
			</div>
			<input type='button' value='Add Screen' id='addButton' class="btn">
			<input type='button' value='Remove Screen' id='removeButton' class="btn">

			<form:hidden path="numberOfScreens"/>
			<form:hidden path="jsonData" />

			<br><br>
			<div class="form-group">
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">Submit</form:button>
			</div>
			
			
			

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
	
	
</BODY>
</HTML>
