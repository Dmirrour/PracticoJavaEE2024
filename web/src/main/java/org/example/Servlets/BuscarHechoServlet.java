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

@WebServlet("/buscar-hecho")
public class BuscarHechoServlet extends HttpServlet {

    @Inject
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Hecho hecho = null;
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            hecho = hechoService.obtenerHechoPorId(id);
        }
        request.setAttribute("hecho", hecho);
        request.getRequestDispatcher("/buscar-hecho.jsp").forward(request, response);
    }
}