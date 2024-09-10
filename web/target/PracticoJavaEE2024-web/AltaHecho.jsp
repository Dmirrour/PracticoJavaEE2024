<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 3/9/2024
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Hecho</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Agregar Hecho</h1>
<form action="agregar-hecho" method="post">
    <label for="descripcion">Descripción:</label>
    <input type="text" id="descripcion" name="descripcion" required><br><br>

    <label for="estado">Estado:</label>
    <input type="text" id="estado" name="estado" required><br><br>

    <label for="fechaCreacion">Fecha de Creación:</label>
    <input type="datetime-local" id="fechaCreacion" name="fechaCreacion" required><br><br>

    <label for="fechaVerificacion">Fecha de Verificación:</label>
    <input type="datetime-local" id="fechaVerificacion" name="fechaVerificacion"><br><br>

    <label for="justificacion">Justificación:</label>
    <input type="text" id="justificacion" name="justificacion"><br><br>

    <label for="calificacion">Calificación:</label>
    <select id="calificacion" name="calificacion">
        <option value="FALSA">Falsa</option>
        <option value="VERDADERA">Verdadera</option>
        <option value="VERDAD_A_MEDIAS">Verdad a Media</option>
        <option value="INFLADO">Inflado</option>
        <option value="ENGANOSO">Engañoso</option>
        <option value="RIDICULO">Ridiculo</option>
    </select><br><br>

    <label for="publicado">Publicado:</label>
    <input type="checkbox" id="publicado" name="publicado"><br><br>

    <label for="areaTematica">Área Temática:</label>
    <select id="areaTematica" name="areaTematica">
        <option value="ECONOMIA">Economía</option>
        <option value="POLITICA">Política</option>
        <option value="SOCIEDAD">Sociedad</option>
        <option value="MEDICINA">Medicina</option>
        <option value="CELEBRIDADES">Celebridades</option>
        <option value="CIENCIA">Ciencia</option>
    </select><br><br>

    <input type="submit" value="Agregar">
</form>
<div class="btn-container">
    <button onclick="location.href='index.jsp'">Menú Principal</button>
</div>
</body>
</html>

