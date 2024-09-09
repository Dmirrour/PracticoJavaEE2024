package org.example;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Services.InterfaceService.IHechoServiceLocal;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BorrarHechoServlet", value = "/Borrar")
public class BorrarHechoServlet extends HttpServlet {

    @Inject
    private IHechoServiceLocal hechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idHecho = request.getParameter("idHecho");
        try {
            int id = Integer.parseInt(idHecho);
            hechoService.eliminarHecho(id);
            response.setContentType("text/html");
            response.setHeader("Refresh", "5;url=index.jsp");

            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Mensaje de éxito</title></head><body>");
            out.println("<h1>Operación realizada con éxito</h1>");
            out.println("<p>Redirigiendo a la página en 5 segundos...</p>");
            out.println("</body></html>");
            out.close();
        }catch (Exception e){
            response.setContentType("text/html");
            response.setHeader("Refresh", "5;url=index.jsp");

            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Mensaje de fallo</title></head><body>");
            out.println("<h1>La Operación no borrar el Hecho</h1>");
            out.println("<p>Redirigiendo a la página en 5 segundos...</p>");
            out.println("</body></html>");
            out.close();
            throw new RuntimeException(e);
        }


    }
}
