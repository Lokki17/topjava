<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Add meal</h2>
<form method="post" action="mealEdit">
    <table border="3px">
        <tr>
            <%--<td width="20">id</td>--%>
            <td width="150">dateTime</td>
            <td width="150">description</td>
            <td width="70">calories</td>
        </tr>
        <tr>
            <td><input name="dateTime" type="datetime-local" title="dateTime"></td>
            <td><input name="description" type="text" title="description"></td>
            <td><input name="calories" type="text" title="calories"/></td>
            <td><input name="create" type="text" title="create" value="create" hidden></td>
        </tr>
    </table>
    <input type="submit" value="Add meal">
</form>
</body>
</html>
