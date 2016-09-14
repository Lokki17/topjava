<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fmt" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .green{
        border-color: black;
        color: green;
    }
    .red{
        border-color: black;
        color: red;
    }
</style>

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
            <tr class="${meal.exceed ? 'red' : 'green'}">
                <td title="id of meal"><c:out value="${meal.id}"/></td>
                <td title="Date and Time of meal"><c:out value="${fn:replace(meal.dateTime, 'T', ' ')}"/></td>
                <td title="Description of meal"><c:out value="${meal.description}"/></td>
                <td title="Calories of meal"><c:out value="${meal.calories}"/></td>
                <td title="Edit meal">
                    <c:url var="onEdit" value="meals">
                        <c:param name="action" value="edit"/>
                        <c:param name="meal" value="${meal.id}"/>
                    </c:url>
                    <a href="${onEdit}">Edit</a>
                </td>
                <td title="Delete meal">
                    <c:url var="onDelete" value="meals">
                        <c:param name="action" value="delete"/>
                        <c:param name="meal" value="${meal.id}"/>
                    </c:url>
                    <a href="${onDelete}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<a href="mealEdit?action=add">Add meal</a>

</body>
</html>
