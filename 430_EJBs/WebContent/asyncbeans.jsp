<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="col-md-offset-2 col-md-8">
	<h1>Asynchronous bean example:</h1>
	<hr>
	<div class="col-sm-6">
		<form name="input" action="AsyncBeanServlet" method="post">
			<input type="hidden" name="operation" value="putDataAsync" />
			<input type="submit" value="Insert data to DB async" class="btn btn-success btn-lg"/>
		</form>
		<h3>Time taken: <c:out value="${putDataAsync}"></c:out></h3>
	<pre>
 @Asynchronous
 public Future&lt;String> putDataAsync() {
     lookBusy(5000);
     return new AsyncResult&lt;String>("DB operation completed.");
 }
  </pre>
	</div>
	<div class="col-sm-6">
		<form name="input" action="AsyncBeanServlet" method="post">
			<input type="hidden" name="operation" value="putDataBlocking" />
			<input type="submit" value="Insert data to DB blocking" class="btn btn-danger btn-lg"/>
		</form>
		<h3>Time taken: <c:out value="${putDataBlocking}"></c:out></h3>
	 <pre>
	 
 public Future&lt;String> putDataBlocking() {
     lookBusy(5000);
     return new AsyncResult&lt;String>("Blocking operation completed");
 }
	</pre>
	</div>
	<br><br>
	Using bean: <c:out value="${BEAN_ID}"></c:out>
	<br><br>
	<a href="index.html">Back to index</a>
</body>
</html>