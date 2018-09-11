<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DDSearch</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>Year</th>
                <th>Population</th>
                <th>DeathRateRangeLow</th>
                <th>County</th>
                <th>State</th>
            </tr>
            <c:forEach items="${deathdata}" var="deathdata" >
                <tr>
                    <td><c:out value="${deathdata.getZip()}" /></td>
                    <td><c:out value="${deathdata.getCounty()}" /></td>
                    <td><c:out value="${deathdata.getState()}" /></td>
                    <td><c:out value="${deathdata.getDeathRateRangeLow()}" /></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>
