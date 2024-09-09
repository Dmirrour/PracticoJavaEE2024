package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.IOException;

@WebServlet(name = "CargarModificarHechoServlet", value = "/cargar-modificar-hecho")
public class CargarModificarHechoServlet extends HttpServlet {
    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
       String a= request.getParameter("idHecho");
        System.out.println(a);
        int idHecho = Integer.parseInt(request.getParameter("idHecho"));

        // Buscar el hecho por ID
        Hecho hecho = hechoService.obtenerHechoPorId(idHecho);
        if (hecho != null) {
            // Enviar el hecho a la vista de modificación
            request.setAttribute("hecho", hecho);
            RequestDispatcher dispatcher = request.getRequestDispatcher("modificar-hecho.jsp");
            dispatcher.forward(request, response);
        } else {
            // Manejar error si no se encuentra el hecho
            response.sendRedirect("error.jsp");
        }
    }
}
