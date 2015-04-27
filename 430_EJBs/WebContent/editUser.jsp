<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="col-md-offset-2 col-md-8">
	<h1>Entity Bean example:</h1>
	<hr>
	<c:set var="editPerson" value="${personBean.getPerson(param.username)}"/>
	<h2>Edit Person <c:out value="${editPerson.uname}"/></h2>
	<div class="bs-callout bs-callout-success">
		<form action="PersonServlet" method="post">
			<input type="hidden" name="FUNCTION" value="EDITUSER"></input>
			<input type="hidden" name="username" value="${editPerson.uname}" />
			<div class="form-group">
				<label for="emailInput">Email: </label>
				<input type="text" name="email" id="emailInput" placeholder="Enter email here"></input>
			</div>
			<div class="form-group">
				<label for="passwdInput">Password: </label>
				<input type="text" name="passwd" id="passwdInput" placeholder="Enter password here"></input>
			</div>
			<button type="submit" class="btn btn-success">Submit Update</button>
		</form>		
	</div>
	<p><c:out value="${RETURN_MESSAGE}"></c:out></p>	
</body>
</html>