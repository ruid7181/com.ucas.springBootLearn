<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>数据列表</h1>
    <table>
        <tr>
            <th>id</th>
            <th>卫星</th>
            <th>时间</th>
        </tr>
        <c:forEach items="${list}" var="tmdata">
            <tr>
                <td>${tmdata.f_name}</td>
                <td>${tmdata.sat}</td>
                <td>${tmdata.time}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
