package org.example;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.JMSProducer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HechoMDBServlet", urlPatterns = {"/HechoMDBServlet"})
public class HechoMDBServlet extends HttpServlet {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/queue/HechoMDB")
    private Queue queue;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String atributo1 = request.getParameter("descripcion");
        String atributo2 = request.getParameter("estado");
        String atributo3 = request.getParameter("fechaCreacion"); // Formato: yyyy-MM-dd HH:mm:ss
        String atributo4 = request.getParameter("fechaVerificacion"); // Formato: yyyy-MM-dd HH:mm:ss
        String atributo5 = request.getParameter("justificacion");
        String atributo6 = request.getParameter("calificacion"); // Suponiendo que es el nombre del enum
        String atributo7 = request.getParameter("publicado"); // true o false
        String atributo8 = request.getParameter("areaTematica"); // Suponiendo que es el nombre del enum

        String mensaje = atributo1 + "|" + atributo2 + "|" + atributo3 + "|" + atributo4 + "|" + atributo5 + "|"
                + atributo6 + "|" + atributo7 + "|" + atributo8; // Construir el mensaje

        JMSProducer producer = context.createProducer();
        producer.send(queue, mensaje); // Enviar el mensaje a la cola

        out.println("<h1>Mensaje enviado a la cola: " + mensaje + "</h1>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

