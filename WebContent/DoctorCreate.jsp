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
<title>Create a Doctor</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
	<h1>Create Doctor</h1>
	</div>
	<form action="doctorcreate" method="post">
		<p>
			<h2><label for="octorid">DoctorId</label></h2>
			<input id="doctorid" name="doctorid" value="">
		</p>
		<p>
			<h2><label for="lastname">LastName</label></h2>
			<input id="lastname" name="lastname" value="">
		</p>
		
		<p>
			<h2><label for="firstname">FirstName</label></h2>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<h2><label for="redentials">Credentials)</label></h2>
			<input id="credentials" name="credentials" value="">
		</p>
		<p>
			<h2><label for="street1">Street1</label></h2>
			<input id="street1" name="street1" value="">
		</p>
		<p>
			<h2><label for="city">City</label></h2>
			<input id="city" name="city" value="">
		</p>
		<p>
			<h2><label for="zip">Zip</label></h2>
			<input id="zip" name="zip" value="">
		</p>
		<p>
			<h2><label for="state">State</label></h2>
			<input id="state" name="state" value="">
		</p>
		<p>
			<h2><label for="speciality">Speciality</label></h2>
			<input id="speciality" name="speciality" value="">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
		</p>
	</form>
	<br/><br/>
	<p>
		<div class="alert alert-success" role="alert">
		<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	
	</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>