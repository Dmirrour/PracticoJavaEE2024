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
    <title>Buscar Hechos</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Buscar Hecho</h1>
<form action="buscarHechos" method="get">
    <label for="query">Buscar:</label>
    <input type="text" id="query" name="query" required>
    <button type="submit">Buscar</button>
</form>
<div class="btn-container">
    <a href="index.jsp"><button>Volver al inicio</button> </a>
</div>
</body>
</html>

