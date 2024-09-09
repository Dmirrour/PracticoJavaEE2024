package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarHechosServlet", value = "/listar-hechos")
public class ListarHechosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hecho> hechos = hechoService.listarHechos();
        request.setAttribute("hechos", hechos);
        request.getRequestDispatcher("ListarHechos.jsp").forward(request, response);
    }
}
