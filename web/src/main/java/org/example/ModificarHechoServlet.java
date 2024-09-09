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
import java.util.Date;

@WebServlet(name = "ModificarHechoServlet", value = "/modificar-hecho")
public class ModificarHechoServlet extends HttpServlet {
    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Evitan problemas con tildes
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int idHecho = Integer.parseInt(request.getParameter("idHecho"));

        Hecho hechoModificado = hechoService.obtenerHechoPorId(idHecho);
        if (hechoModificado != null) {

            hechoModificado.setEstado(request.getParameter("estado"));
            hechoModificado.setDescripcion(request.getParameter("descripcion"));
            hechoModificado.setCalificacion(TipoCalificacion.valueOf(request.getParameter("calificacion")));
            hechoModificado.setAreaTematica(TipoCategoria.valueOf(request.getParameter("areaTematica")));
            hechoModificado.setJustificacion(request.getParameter("justificacion"));


            String publicado = request.getParameter("publicado");
            hechoModificado.setPublicado(publicado != null && publicado.equals("on"));


            Hecho hecho = hechoService.modificarHecho(hechoModificado);
            request.setAttribute("hecho", hecho);
        }


        request.getRequestDispatcher("detalleHecho.jsp").forward(request, response);
    }
}


