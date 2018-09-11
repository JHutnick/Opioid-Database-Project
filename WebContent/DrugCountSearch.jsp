<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DrugCostSearch</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>DoctorId</th>
                <th>TotalDrugCost</th>
                <th>BrandDrugCost</th>
                <th>GenericDrugCost</th>
                <th>OpioidDrugCost</th>
                <th>ErOpioidDrugCost</th>
                <th>AntiDrugCost</th>
            </tr>
            <c:forEach items="${drugcost}" var="drugcost" >
                <tr>
                    <td><c:out value="${drugcost.getDoctorId()}" /></td>
                    <td><c:out value="${drugcost.getTotalDrugCost()}" /></td>
                    <td><c:out value="${drugcost.getBrandDrugCost()}" /></td>
                    <td><c:out value="${drugcost.getGenericDrugCost()}" /></td>
                    <td><c:out value="${drugcost.getOpioidDrugCost()}" /></td>
                    <td><c:out value="${drugcost.getErOpioidDrugCost()}" /></td>
                    <td><c:out value="${drugcost.getAntiDrugCost()}" /></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>
