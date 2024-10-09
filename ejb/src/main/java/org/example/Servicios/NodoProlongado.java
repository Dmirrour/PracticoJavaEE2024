package org.example.Servicios;

import com.google.gson.Gson;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Dto.ServiciosExternos.NodoProlongado.HechoResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class NodoProlongado {
    private static final String API_URL = "http://localhost:8180/NodoProlongado-web/NodeAsync/hechos/";
    private static final String API_KEY = System.getenv("ApiKeyFactCheck");
    private static final int PAGESIZE = 5;
    private static final String IDIOMA = "es";

    public HechoResponse obtenerHecho(int id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL + id);
        System.out.println(API_KEY);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("X-API-Key", API_KEY)
                .get();
        System.out.println(response);

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error en la llamada a la API: " + response.getStatus());
        }


        return response.readEntity(HechoResponse.class);
    }

    public Response agregarHecho(String frase, String notasAdicionales, String estado, LocalDateTime fechaCreacion, String areaTematica, String urlFuente) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL+"agregar");
        System.out.println(frase+"  "+notasAdicionales+"  "+estado+"  "+fechaCreacion+"  "+areaTematica+"  "+urlFuente);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("frase", frase);
        requestBody.put("notasAdicionales", notasAdicionales);
        requestBody.put("estado", estado);
        requestBody.put("fechaCreacion", fechaCreacion.toString()); // Asegúrate de que el formato sea correcto
        requestBody.put("areaTematica", areaTematica);
        requestBody.put("urlFuente", urlFuente);

        String jsonRequestBody = new Gson().toJson(requestBody);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("X-API-Key", API_KEY)
                .post(Entity.entity(jsonRequestBody, MediaType.APPLICATION_JSON));

        System.out.println(response);

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error en la llamada a la API: " + response.getStatus());
        }

        return response;
    }
}
