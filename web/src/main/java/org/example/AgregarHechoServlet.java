package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;
import org.example.entity.Hecho;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AgregarHechoServlet", value = "/agregar-hecho")
public class AgregarHechoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hecho nuevoHecho = new Hecho();
        nuevoHecho.setDescripcion(request.getParameter("descripcion"));
        nuevoHecho.setEstado(request.getParameter("estado"));
        nuevoHecho.setFechaCreacion(LocalDateTime.parse(request.getParameter("fechaCreacion")));
        nuevoHecho.setFechaVerificacion(LocalDateTime.parse(request.getParameter("fechaVerificacion")));
        nuevoHecho.setJustificacion(request.getParameter("justificacion"));
        nuevoHecho.setCalificacion(TipoCalificacion.valueOf(request.getParameter("calificacion")));
        nuevoHecho.setPublicado(request.getParameter("publicado") != null);
        nuevoHecho.setAreaTematica(TipoCategoria.valueOf(request.getParameter("areaTematica")));

        hechoService.agregarHecho(nuevoHecho);

        response.sendRedirect("listar-hechos");
    }
}
