<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Modificar Hecho</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
  <h1>Modificar Hecho</h1>
  <form action="modificar-hecho" method="post">
    <!-- Mostrar el ID del hecho como campo oculto -->
    <input type="hidden" name="idHecho" value="${hecho.idHecho}">


    <label for="estado">Estado:</label>
    <input type="text" id="estado" name="estado" value="${hecho.estado}" required><br><br>

    <label for="descripcion">Descripción:</label>
    <input type="text" id="descripcion" name="descripcion" value="${hecho.descripcion}"><br><br>

    <label for="calificacion">Calificación:</label>
    <select id="calificacion" name="calificacion">
      <option value="FALSA" ${hecho.calificacion == 'FALSA' ? 'selected' : ''}>Falsa</option>
      <option value="VERDADERA" ${hecho.calificacion == 'VERDADERA' ? 'selected' : ''}>Verdadera</option>
      <option value="VERDAD_A_MEDIAS" ${hecho.calificacion == 'VERDAD_A_MEDIAS' ? 'selected' : ''}>Verdad a Medias</option>
      <option value="INFLADO" ${hecho.calificacion == 'INFLADO' ? 'selected' : ''}>Inflado</option>
      <option value="ENGANOSO" ${hecho.calificacion == 'ENGANOSO' ? 'selected' : ''}>Engañoso</option>
      <option value="RIDICULO" ${hecho.calificacion == 'RIDICULO' ? 'selected' : ''}>Ridículo</option>
    </select><br><br>

    <label for="areaTematica">Área Temática:</label>
    <select id="areaTematica" name="areaTematica">
      <option value="POLITICA" ${hecho.areaTematica == 'POLITICA' ? 'selected' : ''}>Política</option>
      <option value="MEDICINA" ${hecho.areaTematica == 'MEDICINA' ? 'selected' : ''}>Medicina</option>
      <option value="CIENCIA" ${hecho.areaTematica == 'CIENCIA' ? 'selected' : ''}>Ciencia</option>
      <option value="CELEBRIDADES" ${hecho.areaTematica == 'CELEBRIDADES' ? 'selected' : ''}>Celebridades</option>
      <option value="ECONOMIA" ${hecho.areaTematica == 'ECONOMIA' ? 'selected' : ''}>Economía</option>
      <option value="SOCIEDAD" ${hecho.areaTematica == 'SOCIEDAD' ? 'selected' : ''}>Sociedad</option>
    </select><br><br>

    <label for="justificacion">Justificación:</label>
    <textarea id="justificacion" name="justificacion">${hecho.justificacion}</textarea><br><br>

    <label for="publicado">Publicado:</label>
    <input type="checkbox" id="publicado" name="publicado" ${hecho.publicado ? 'checked' : ''}><br><br>

    <label for="fechaCreacion">Fecha de Creación:</label>
    <input type="text" id="fechaCreacion" name="fechaCreacion" value="${hecho.fechaCreacion}" readonly><br><br>


    <button type="submit">Guardar Cambios</button>
  </form>

  <div class="btn-container">
    <a href="index.jsp"><button>Volver al inicio</button> </a>
  </div>
</body>
</html>
