<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Locations</title>
</head>
<body>
<div>
    <h1>Locations</h1>
</div>
<div>
    <table>
        <tr>
            <td>LocationId</td>
            <td>Country</td>
            <td>City</td>
            <td>Street</td>
            <td>House</td>
            <td>Apartment</td>
        </tr>
        <c:forEach var="location" items="${locations}">
            <tr>
                <td>${location.id}</td>
                <td>${location.country}</td>
                <td>${location.city}</td>
                <td>${location.street}</td>
                <td>${location.house}</td>
                <td>${location.apartment}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
