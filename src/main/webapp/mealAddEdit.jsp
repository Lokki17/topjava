<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <tr>
            <c:if test="${!empty meal}">
                <td><input name="id" value="${meal.id}" width="20" title="id" readonly/></td>

            </c:if>
            <td><input name="dateTime" type="datetime-local" value="${meal.dateTime}" title="please, enter Date and Time"></td>
            <td><input name="description" type="text" value="${!empty meal ? meal.description : "sample"}"
                       title="please, enter description"></td>
            <td><input name="calories" type="text" value="${!empty meal ? meal.calories : "0"}" title="please, enter calories"/></td>
        </tr>
        <c:if test="${empty meal}">
            <tr>
                <td><input name="create" type="text" value="create" hidden></td>
            </tr>
        </c:if>
    </table>
    <input type="submit" value="Edit/Add" title="Press the button to Add/Create meal">
</form>

</body>
</html>
