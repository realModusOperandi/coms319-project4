<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="col-md-offset-2 col-md-8">
	<h1>Stateless vs Stateful EJB example:</h1>
	<hr>
	<div class="col-sm-6">
		<form name="input" action="SessionBeanServlet" method="post">
			<input type="hidden" name="beantype" value="stateless" />
			<input type="submit" value="Use Stateless Bean" class="btn btn-success btn-lg"/>
		</form>
		<h3>Use count: &nbsp;<c:out value="${RETURN_STATELESS}"></c:out></h3>
		<br>
		<pre>
 @Stateless
 public class MyStatefulBean {
    private int useCount = 0;

    public void useBean() {
        useCount++;
    }

    public int getUseCount() {
        return useCount;
    }
 }
		</pre>
	</div>
	<div class="cold-sm-6">
		<form name="input" action="SessionBeanServlet" method="post">
			<input type="hidden" name="beantype" value="stateful" />
			<input type="submit" value="Use Stateful Bean" class="btn btn-success btn-lg"/>
		</form>
		<h3>Use Count: &nbsp;<c:out value="${RETURN_STATEFUL}"></c:out></h3>
		<br>
		<pre>
 @Stateful
 public class MyStatefulBean {
    private int useCount = 0;

    public void useBean() {
        useCount++;
    }

    public int getUseCount() {
        return useCount;
    }
 }
		</pre>
	</div>
	<br><br>
	<div>
		<form name="input" action="SessionBeanServlet" method="post">
			<input type="submit" value="Restart Session" class="btn btn-danger btn-lg"/>
		</form>
	</div>
	<br><br>
	<c:out value="${BEAN_ID}"></c:out>
	<br><br>
	<a href="index.html">Back to index</a>
</body>
</html>