<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find ClaimInfo</title>
</head>
<body>
	<form action="findclaiminfo" method="post">
		<h1>Search for ClaimInfo by DoctorId</h1>
		<p>
			<label for="DoctorId">DoctorId</label>
			<input id="DoctorId" name="DoctorId" value="${fn:escapeXml(param.doctorid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="claiminfoCreate"><a href="claiminfocreate">Create ClaimInfo</a></div>
	<br/>
	<h1>Matching ClaimInfo</h1>
        <table border="1">
            <tr>
                <th>DoctorId</th>
                <th>TotalClaimCount</th>
                <th>30DayFill</th>
                <th>BrandClaimCount</th>
                <th>GenericClaim</th>
                <th>OpioidClaim</th>
                <th>ErOpioidClaim</th>
                <th>AntiClaim</th>
                <th>Delete ClaimInfo</th>
            </tr>
            <c:forEach items="${claiminfo}" var="claiminfo" >
                <tr>
                    <td><c:out value="${claiminfo.getDoctorId()}" /></td>
                    <td><c:out value="${claiminfo.getTotalClaimCount()}" /></td>
                     <td><c:out value="${claiminfo.getDayFill30()}" /></td>
                    <td><c:out value="${claiminfo.getBrandClaimCount()}" /></td>
                     <td><c:out value="${claiminfo.getGenericClaim()}" /></td>
                     <td><c:out value="${claiminfo.getOpioidClaim()}" /></td>
                     <td><c:out value="${claiminfo.getErOpioidClaim()}" /></td>
                     <td><c:out value="${claiminfo.getAntiClaim()}" /></td>
                    <td><a href="claiminfodelete?doctorid=<c:out value="${claiminfo.getDoctorId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
