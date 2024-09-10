<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 9/9/2024
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.entity.Hecho" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Seleccionar Hecho para Modificar</title>
</head>
<body>
<h1>Seleccionar Hecho para Modificar</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Descripción</th>
        <th>Estado</th>
        <th>Fecha de Creación</th>
        <th>Fecha de Verificación</th>
        <th>Justificación</th>
        <th>Calificación</th>
        <th>Publicado</th>
        <th>Área Temática</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Hecho> hechos = (List<Hecho>) request.getAttribute("hechos");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (hechos != null) {
            for (Hecho hecho : hechos) {
    %>
    <tr>
        <td><%= hecho.getIdHecho() %></td>
        <td><%= hecho.getDescripcion() %></td>
        <td><%= hecho.getEstado() %></td>
        <td><%= hecho.getFechaCreacion().format(formatter) %></td>
        <td><%= hecho.getFechaVerificacion() != null ? hecho.getFechaVerificacion().format(formatter) : "N/A" %></td>
        <td><%= hecho.getJustificacion() %></td>
        <td><%= hecho.getCalificacion() %></td>
        <td><%= hecho.isPublicado() ? "Sí" : "No" %></td>
        <td><%= hecho.getAreaTematica() %></td>
        <td>
            <form action="cargar-modificar-hecho" method="get">
                <input type="hidden" name="idHecho" value="<%= hecho.getIdHecho() %>">
                <button type="submit">Modificar</button>
            </form>
        </td>
        <td>
            <form action="Borrar" method="post">
                <input type="hidden" name="idHecho" value="<%= hecho.getIdHecho() %>">
                <button type="submit">Borrar</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="9">No hay hechos disponibles</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<div class="btn-container">
    <a href="index.jsp"><button>Volver al inicio</button> </a>
</div>

</body>
</html>
