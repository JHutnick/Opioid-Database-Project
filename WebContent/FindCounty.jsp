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
<title>Find a County</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="findcounty" method="post">
	    <div class="jumbotron">
		<h1>Search for a County by CountyName</h1>
		<p>
		</div>
		<p>
			<h2><label for="countyname">CountyName</label></h2>
			<input id="countyname" name="countyname" value="${fn:escapeXml(param.countyname)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<h3><div id="countycreate"><a href="countycreate">Create a County</a></div></h3>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Counties</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>ZipCode</th>
                <th>City</th>
                <th>State</th>
                <th>CountyName</th>
                <th>Delete County</th>
           </tr></thead>
            <c:forEach items="${counties}" var="county" >
                <tbody><tr>
                    <td><c:out value="${county.getZip()}" /></td>
                    <td><c:out value="${county.getCity()}" /></td>
                     <td><c:out value="${county.getState()}" /></td>
                    <td><c:out value="${county.getCountyName()}" /></td>
                    <td><a href="countydelete?countyname=<c:out value="${county.getCountyName()}"/>">Delete</a></td>
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
