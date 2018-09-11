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
<title>Find Doctors</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="finddoctor" method="post">
	    <div class="jumbotron">
		<h1>Search for Doctors by State</h1>
		<p>
		</div>
		<p>
			<h2><label for="state">State</label></h2>
			<input id="state" name="state" value="${fn:escapeXml(param.state)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<h3><div id="doctorcreate"><a href="doctorcreate">Create a Doctor</a></div></h3>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Doctors</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>DoctorId</th>
                <th>LastName</th>
                <th>FirstName</th>
                <th>Credentials</th>
                <th>Street1</th>
                <th>City</th>
                <th>Zip</th>
                <th>State</th>
                <th>Speciality</th>
                <th>Delete Users</th>
           </tr></thead>
            <c:forEach items="${doctors}" var="doctor" >
                <tbody><tr>
                    <td><c:out value="${doctor.getDoctorId()}" /></td>
                    <td><c:out value="${doctor.getLastName()}" /></td>
                     <td><c:out value="${doctor.getFirstName()}" /></td>
                    <td><c:out value="${doctor.getCredentials()}" /></td>
                     <td><c:out value="${doctor.getStreet1()}" /></td>
                     <td><c:out value="${doctor.getCity()}" /></td>
                     <td><c:out value="${doctor.getState()}" /></td>
                     <td><c:out value="${doctor.getZip()}" /></td>
                     <td><c:out value="${doctor.getSpeciality()}" /></td>
                    <td><a href="doctordelete?doctorid=<c:out value="${doctor.getDoctorId()}"/>">Delete</a></td>
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
