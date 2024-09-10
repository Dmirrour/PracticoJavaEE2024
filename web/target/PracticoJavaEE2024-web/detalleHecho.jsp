<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 9/9/2024
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Detalles del Hecho</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Detalles del Hecho</h1>
    <c:if test="${not empty hecho}">
        <p><strong>ID:</strong> ${hecho.idHecho}</p>
        <p><strong>Descripción:</strong> ${hecho.estado}</p>
        <p><strong>Descripción:</strong> ${hecho.descripcion}</p>
        <p><strong>Calificación:</strong> ${hecho.calificacion}</p>
        <p><strong>Área Temática:</strong> ${hecho.areaTematica}</p>
        <p><strong>Justificación:</strong> ${hecho.justificacion}</p>
        <p><strong>Publicado:</strong> ${hecho.publicado ? 'Sí' : 'No'}</p>
        <p><strong>Fecha de Verificación:</strong> ${hecho.fechaVerificacion}</p>
    </c:if>
    <c:if test="${empty hecho}">
        <p>Hecho no encontrado.</p>
    </c:if>
    <br>
    <div class="btn-container">
        <a href="index.jsp"><button>Volver al inicio</button> </a>
    </div>
</body>
</html>
