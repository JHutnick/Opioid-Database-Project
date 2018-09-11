<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find BeneficiaryInfo</title>
</head>
<body>
	<form action="findbeneficiaryinfo" method="post">
		<h1>Search for BeneficiaryInfo by DoctorId</h1>
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
	<div id="beneficiaryinfoCreate"><a href="beneficiaryinfocreate">Create BeneficiaryInfo</a></div>
	<br/>
	<h1>Matching BeneficiaryInfo</h1>
        <table border="1">
            <tr>
                <th>DoctorId</th>
                <th>BeneCount</th>
                <th>OpiBenCount</th>
                <th>ErOpiBenCount</th>
                <th>AntibioBenCount</th>
                <th>BenAgeL65</th>
                <th>BenAgeG66</th>
                <th>AvgAgeBen</th>
                <th>BenF</th>
                <th>BenM</th>
                <th>BenNDual</th>
                <th>BenDual</th>
                <th>BenAvgRisk</th>
                <th>Delete BeneficiaryInfo</th>
            </tr>
            <c:forEach items="${beneficiaryinfo}" var="beneficiaryinfo" >
                <tr>
                    <td><c:out value="${beneficiaryinfo.getDoctorId()}" /></td>
                    <td><c:out value="${beneficiaryinfo.getBeneCount()}" /></td>
                     <td><c:out value="${beneficiaryinfo,getOpiBenCount()}" /></td>
                    <td><c:out value="${beneficiaryinfo.getErOpiBenCount()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getAntibioBenCOunt()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenAgeL65()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenAgeG65()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getAvgAgeBen()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenF()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenM()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenNDual()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenDual()}" /></td>
                     <td><c:out value="${beneficiaryinfo.getBenAvgRisk()}" /></td>
                    <td><a href="beneficiaryinfodelete?doctorid=<c:out value="${beneficiaryinfo.getDoctorId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
