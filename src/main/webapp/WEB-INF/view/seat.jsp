<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Create an account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</head>

<script>
$(document).ready(function(){

    var counter = 2;
		
    $("#addButton").click(function () {

		
	var newTextBoxDiv = $(document.createElement('div'))
	     .attr("id", 'seatDiv' + counter);
                
	newTextBoxDiv.after().html('<br>Seat '+ counter+':<br/>' + '<label>Seat Number &nbsp &nbsp &nbsp &nbsp </label>' +
	      '<input type="text" name="seatbox' + counter + 
	      '" id="seatbox' + counter + '" value="" >
            
	newTextBoxDiv.appendTo("#Seat");

				
	counter++;
     });

     $("#removeButton").click(function () {
	if(counter==1){
          alert("No more textbox to remove");
          return false;
       }   
        
	counter--;
			
        $("#seatDiv" + counter).remove();
			
     });
      
      window.getTextBoxValues = function()
      {
    	  var textValues='';
    	  for(i=1; i<counter; i++)
    	  {
    		  if(i == counter-1)
    		  {
    			  textValues +=$('#seatbox' + i).val();
    		  }
    		  else
    		  {
    			  textValues +=$('#seatbox' + i).val()+",";
    		  }
    	   	  
    	   	  
    	   	  //alert("!!!!!!!!!!!!!!!!!!!!!")
    		}
    	  //alert("***"+textValues);
    	  
    	  return textValues;
      }


      
  });  
    </script>

<body>

	<div class="container">
		<form:form name="seatForm" method="post" class="form-signin"
			action="${path}/welcome" modelAttribute="seatForm" onsubmit="return validateform()">
			
			
			
			<table>
		<thead>
			<tr>
				<th>seat</th>
				<th>class</th>
				<th>status</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${seats}" var="seat">
				<tr>
					<td>${seat.seatNumber}</td>
					<td>${seat.seatClass}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
			
			<div id='Seat'>
				<div id="seatDiv1">
					Seat : <br /> <label>Seat &nbsp; &nbsp; &nbsp; &nbsp; </label><input type='text'
						id='seatbox1'> 				</div>
			</div>
			<input type='button' value='Add Seat' id='addButton'>
			<input type='button' value='Remove Seat' id='removeButton'>

			
			<p>
				<form:button class="btn btn-lg btn-primary btn-block" type="submit">SUBMIT</form:button>
			</p>
				
		
		</form:form>
		
		<div style="text-align: center;">

			<a href="${path}welcome"><button class="btn">HOME</button></a>
		</div>
		
	</div>
			
</body>

</html> --%>