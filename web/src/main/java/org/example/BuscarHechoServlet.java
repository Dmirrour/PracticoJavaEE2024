package org.example;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BuscarHechoServlet", value = "/buscar-hecho")
public class BuscarHechoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idHechoStr = request.getParameter("idHecho");
        int idHecho = Integer.parseInt(idHechoStr);

        Hecho hecho = hechoService.obtenerHechoPorId(idHecho);
        if (hecho != null) {
            request.setAttribute("hecho", hecho);
            request.getRequestDispatcher("detalleHecho.jsp").forward(request, response);
        } else {
            response.setContentType("text/html");
            response.setHeader("Refresh", "5;url=buscar-hecho.jsp");

            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Mensaje de fallo</title></head><body>");
            out.println("<h1>Hecho no encontrado</h1>");
            out.println("<p>Redirigiendo a la página en 5 segundos...</p>");
            out.println("</body></html>");
            out.close();

        }
    }
}