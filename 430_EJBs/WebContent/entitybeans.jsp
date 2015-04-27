<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="col-md-offset-2 col-sm-8">
	<h1>Entity Bean example:</h1>
	<hr>
	<table class="table table-striped">
		<thead>
			<tr>
				<th></th>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
			</tr>
		<thead>
		<tbody>
		<c:forEach var="person" items="${personBean.people}">
			<tr>
				<td>
				<form action="editUser.jsp" method="post">
					<input type="hidden"  name="username" value="${person.uname}"></input>
					<input type="submit" value="Edit"></input>
				</form>
				</td>
				<td><c:out value="${person.uname}"></c:out></td>
				<td><c:out value="${person.email}"></c:out></td>
				<td><c:out value="${person.passwd}"></c:out></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="bs-callout bs-callout-success">
		<form action="PersonServlet" method="post">
			<input type="hidden" name="FUNCTION" value="ADDUSER"></input>
			<div class="form-group">
				<label for="usernameInput">Username: </label>
				<input type="text" name="username" id="usernameInput" placeholder="Enter username here"></input>
			</div>
			<div class="form-group">
				<label for="emailInput">Email: </label>
				<input type="text" name="email" placeholder="Enter email here"></input>
			</div>
			<button type="submit" class="btn btn-success">Add Person</button>
		</form>		
	</div>
	<br><br>
	<pre>
 @Entity
 public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private long id;
    
    @Column(name = "email", unique = true, length = 128, nullable = false)
    private String email = "";

    @Column(name = "uname", unique = true, length = 8, nullable = false)
    private String uname = null;

    @Column(name = "passwd", length = 80, nullable = false)
    private String passwd = "";
 }
	</pre>
	<p><c:out value="${RETURN_MESSAGE}"></c:out></p>	
	<br><br>
	<a href="index.html">Back to index</a>
</body>
</html>