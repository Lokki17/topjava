<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Edit Meal</title>
</head>
<body>

<h2><a href="index.html">Home</a></h2>
<h2>Edit meal</h2>
<form method="post" action="mealEdit">
    <table border="3px">
        <tr>
            <c:if test="${!empty meal}">
                <td width="20">id</td>
            </c:if>
            <td width="150">dateTime</td>
            <td width="150">description</td>
            <td width="70">calories</td>
        </tr>
        <c:if test="${!empty meal}">
            <tr>
                <td><input name="id" value="${meal.id}" width="20" title="id" readonly/></td>
                <td><input name="dateTime" type="datetime-local" value="${meal.dateTime}" title="dateTime"></td>
                <td><input name="description" type="text" value="${meal.description}" title="description"></td>
                <td><input name="calories" type="text" value="${meal.calories}" title="calories"/></td>
            </tr>
        </c:if>
        <c:if test="${empty meal}">
            <tr>
                <td><input name="dateTime" type="datetime-local" title="dateTime"></td>
                <td><input name="description" type="text" value="exemple" title="description"></td>
                <td><input name="calories" type="text" value="0" title="calories"/></td>
                <td><input name="create" type="text" title="create" value="create" hidden></td>
            </tr>
        </c:if>
    </table>
    <input type="submit" value="Edit/Add">
</form>

</body>
</html>
