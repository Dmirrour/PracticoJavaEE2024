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
import java.util.List;
@WebServlet(name = "BuscarHechosServlet", value = "/buscarHechos")
public class BuscarHechosServlet extends HttpServlet {

    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");
        List<Hecho> resultados = hechoService.buscarHechos(query);
        request.setAttribute("hechos", resultados);
        request.getRequestDispatcher("ListarHechos.jsp").forward(request, response);
    }
}
