<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a User</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="findusers" method="post">
	    <div class="jumbotron">
		<h1>Search for a User by FirstName</h1>
		<p>
		</div>
		<p>
			<h2><label for="username">FirstName</label></h2>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<h3><div id="usercreate"><a href="usercreate">Create a User</a></div></h3>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Users</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>UserName</th>
                <th>Password</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Delete Users</th>
                <th>Update Users</th>
            </tr></thead>
            <c:forEach items="${users}" var="user" >
                <tbody><tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getPassWord()}" /></td>
                     <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                     <td><c:out value="${user.getEmail()}" /></td>
                     <td><c:out value="${user.getPhone()}" /></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
       
    </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>
