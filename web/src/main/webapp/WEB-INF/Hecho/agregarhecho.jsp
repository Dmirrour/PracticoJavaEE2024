<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 2/9/2024
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Agregar Hecho</title>
</head>
<body>
<h1>Agregar Nuevo Hecho</h1>

<form action="agregar-hecho" method="post">
    <label for="descripcion">Descripción:</label><br>
    <input type="text" id="descripcion" name="descripcion" required><br><br>

    <label for="estado">Estado:</label><br>
    <input type="text" id="estado" name="estado" required><br><br>

    <input type="submit" value="Agregar Hecho">
</form>

<a href="listar-hechos">Volver a la Lista</a>
</body>
</html>

