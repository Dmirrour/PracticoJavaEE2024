<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 3/9/2024
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Hechos</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Gestión de Hechos</h1>
<div class="btn-container">
    <a href="listar-hechos"><button>Listar Hechos</button></a>
    <a href="AltaHecho.jsp"><button>Agregar Hecho</button></a>
    <a href="buscar-hecho.jsp"><button>Buscar Hecho ID</button></a>
    <a href="listar-hechosM"><button>Modificar y Borrar</button></a>
    <a href="buscar-hechos.jsp"><button>Buscar Hechos</button></a>
    <br><br>
    <form action="inicializarCasos" method="post">
        <button type="submit">Inicializar Casos de Prueba</button>
    </form>

    <br><br>
    <a href="jsf/Hechos.xhtml"><button>Parte 4 JSF</button></a>
</div>
</body>
</html>

