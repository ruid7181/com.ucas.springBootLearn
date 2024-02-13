<%--
  Created by IntelliJ IDEA.
  User: empir
  Date: 2022/2/10
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Json Test</title>
    <script
            type="text/javascript"
            src="js/jquery-3.3.1.min.js"></script>
    <script
            type="text/javascript">
        $(function(){
                var user = {
                        "con_fields": "f_name",
                        "con_tLis":"20200101\\|20200202"
            };
            $.ajax({
                    url:"/customQuery",
                    data:JSON.stringify(user),
                    type:"POST",
                    contentType:"application/json;charset=UTF-8",
                    dataType:"JSON",
                    success:function(data){
                        alert(data.con_fields+"---"+data.con_tLis);
                    }
                }
            )
        });
    </script>
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