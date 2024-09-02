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
import java.util.List;

@WebServlet("/listar-hechos")
public class HechoServlet extends HttpServlet {

    @Inject
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hecho> hechos = hechoService.listarHechos();
        request.setAttribute("hechos", hechos);
        request.getRequestDispatcher("/listar-hechos.jsp").forward(request, response);
    }
}
