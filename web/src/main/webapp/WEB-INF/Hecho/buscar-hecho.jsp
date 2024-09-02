<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 2/9/2024
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Buscar Hecho</title>
</head>
<body>
<h1>Buscar Hecho por ID</h1>

<form action="buscar-hecho" method="get">
    <label for="id">ID:</label><br>
    <input type="text" id="id" name="id" required><br><br>

    <input type="submit" value="Buscar Hecho">
</form>

<c:if test="${not empty hecho}">
    <h2>Resultado:</h2>
    <p>ID: ${hecho.IDHecho}</p>
    <p>Descripción: ${hecho.Descripción}</p>
    <p>Estado: ${hecho.Estado}</p>
</c:if>

<a href="listar-hechos">Volver a la Lista</a>
</body>
</html>

