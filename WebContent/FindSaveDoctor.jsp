<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="savedoctor" method="post">
		<h1>Search for a SaveDoctor by UserName</h1>
		<p>
			<label for="UserName">UserName</label>
			<input id="UserName" name="UserName" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="savedoctorCreate"><a href="savedoctorcreate">Create SaveDoctor</a></div>
	<br/>
	<h1>Matching SaveDoctor</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>DoctorId</th>
                <th>Password</th>
                 <th>Delete SaveDoctor</th>
            </tr>
            <c:forEach items="${saveDoctor}" var="savedoctor" >
                <tr>
                    <td><c:out value="${saveDoctor.getUserName()}" /></td>
                     <td><c:out value="${saveDoctor.getDoctorId()}" /></td>
                    <td><c:out value="${saveDoctor.getPassWord()}" /></td>
                    <td><a href="savedoctordelete?username=<c:out value="${saveDoctor.getUserName()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
