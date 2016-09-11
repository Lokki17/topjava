<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fmt" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table border="3px">
    <tr>
        <td width="20">id</td>
        <td width="150">dateTime</td>
        <td width="150">description</td>
        <td width="70">calories</td>
        <td width="50">edit</td>
        <td width="50">delete</td>
    </tr>
    <c:if test="${!empty meals}">
        <c:forEach items="${meals}" var="meal">
            <tr bgcolor="${meal.exceed ? 'red' : 'green'}">
                <td><c:out value="${meal.id}"/></td>
                <td><tags:localDate date="${meal.dateTime}"/></td>
                <td><c:out value="${meal.description}"/></td>
                <td><c:out value="${meal.calories}"/></td>
                <td>
                    <a href="mealEdit?action=edit&meal=${meal.id}">Edit</a>
                </td>
                <td>
                    <a href="meals?action=delete&meal=${meal.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<a href="mealEdit?action=add">Add meal</a>

</body>
</html>
