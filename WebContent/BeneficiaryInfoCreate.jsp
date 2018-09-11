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
<title>Create BeneficiaryInfo</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
	<h1>Create BeneficiaryInfo</h1>
	</div>
	<form action="beneficiarycreate" method="post">
		<p>
			<h2><label for="doctorid">DoctorId</label></h2>
			<input id="doctorid" name="doctorid" value="">
		</p>
		<p>
			<h2><label for="benecount">beneCount</label></h2>
			<input id="benecount" name="benecount" value="">
		</p>
		<p>
			<h2><label for="opibencount">OpiBenCount</label></h2>
			<input id="opibencount" name="opibencount" value="">
		</p>
		<p>
			<h2><label for="eropibencount">ErOpiBenCount</label></h2>
			<input id="eropibencount" name="eropibencount" value="">
		</p>
		<p>
			<h2><label for="antibiobencount">AntiBioBenCount</label></h2>
			<input id="antibiocencount" name="antibiobencount" value="">
		</p>
		<p>
			<h2><label for="benagel65">BenAgeL65</label></h2>
			<input id="benagel65" name="benagel65" value="">
		</p>
		<p>
			<h2><label for="benageg65">benageg65</label></h2>
			<input id="benageg65" name="benageg65" value="">
		</p>
		<p>
			<h2><label for="avgageben"> AvgAgeBen</label></h2>
			<input id="avgageben" name="avgageben" value="">
		</p>
		<p>
			<h2><label for="benf">BenF</label></h2>
			<input id="benf" name="benf" value="">
		</p>
		<p>
			<h2><label for="benm">BenM</label></h2>
			<input id="benm" name="benm" value="">
		</p>
		<p>
			<h2><label for="benndual">BenNDual</label></h2>
			<input id="benndual" name="benndual" value="">
		</p>
		<p>
			<h2><label for="bendual">BenDual</label></h2>
			<input id="bendual" name="bendual" value="">
		</p>
		<p>
			<h2><label for="benavgrisk">BenAvgRisk</label></h2>
			<input id="benavgrisk" name="benvgrisk" value="">
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