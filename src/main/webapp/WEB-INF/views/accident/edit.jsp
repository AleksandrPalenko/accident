<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident edit</title>
</head>
<body>

<form action="<c:url value='/update'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <input type="hidden" name="id" value="${accident.id}">
            <td><input type='text' name='name' value="${accident.name}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='text' value="${accident.text}"></td>
        </tr>
        <td>Адрес:</td>
        <td><input type='text' name='name' value="${accident.address}"></td>
        <tr>
            <td colspan='2'><input class="btn btn-primary btn-sm" name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>