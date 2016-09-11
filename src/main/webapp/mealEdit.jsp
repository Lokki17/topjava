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
            <td width="20">id</td>
            <td width="150">dateTime</td>
            <td width="150">description</td>
            <td width="70">calories</td>
        </tr>
        <tr>
            <td><input name="id" value="${meal.id}" title="id" readonly/></td>
            <td><input name="dateTime" type="datetime-local" value="${meal.dateTime}" title="dateTime"></td>
            <td><input name="description" type="text" value="${meal.description}" title="description"></td>
            <td><input name="calories" type="text" value="${meal.calories}" title="calories"/></td>
        </tr>
    </table>
    <input type="submit" value="Edit">
</form>

</body>
</html>
