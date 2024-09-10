<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 3/9/2024
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buscar Hecho</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Buscar Hecho</h1>
<form action="buscar-hecho" method="get">
    <label for="query">ID del Hecho:</label>
    <input type="text" id="query" name="idHecho" required>
    <button type="submit">Buscar</button>
</form>
<div class="btn-container">
    <a href="index.jsp"><button>Volver al inicio</button> </a>
</div>
</body>
</html>

