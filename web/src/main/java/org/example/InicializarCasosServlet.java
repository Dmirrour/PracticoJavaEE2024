package org.example;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;

import java.io.IOException;

@WebServlet(name = "InicializarCasosServlet", value = "/inicializarCasos")
public class InicializarCasosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        hechoService.insertarCasosDePrueba();
        response.sendRedirect("index.jsp");
    }
}

