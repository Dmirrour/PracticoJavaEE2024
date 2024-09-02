package org.example.Servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.IOException;

@WebServlet("/agregar-hecho")
public class AgregarHechoServlet extends HttpServlet {

    @Inject
    private IHechoServiceLocal hechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");

        Hecho nuevoHecho = new Hecho();
        nuevoHecho.setDescripcion(descripcion);
        nuevoHecho.setEstado(estado);

        hechoService.agregarHecho(nuevoHecho);

        response.sendRedirect("listar-hechos");
    }
}