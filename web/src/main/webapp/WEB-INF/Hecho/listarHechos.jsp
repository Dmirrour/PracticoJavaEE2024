<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 2/9/2024
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Listar Hechos</title>
</head>
<body>
<h1>Listar Hechos</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Descripción</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="hecho" items="${hechos}">
        <tr>
            <td>${hecho.IDHecho}</td>
            <td>${hecho.Descripción}</td>
            <td>${hecho.Estado}</td>
            <td>
                <a href="modificar-hecho?id=${hecho.IDHecho}">Modificar</a> |
                <a href="eliminar-hecho?id=${hecho.IDHecho}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="agregar-hecho.jsp">Agregar Nuevo Hecho</a>
</body>
</html>
