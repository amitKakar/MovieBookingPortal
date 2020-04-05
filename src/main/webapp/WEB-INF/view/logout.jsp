<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>
		${message}
		${username}
	</p>

	<p>
		<a href="${path}/logout"><button class="btn"><font size="5">logout</font></button></a>
	</p>
	
	<p>
		<a href="${path}/bookingPage"><button class="btn"><font size="5">Book Your Show</font></button></a>
	</p>
</body>
</html>