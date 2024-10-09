package org.example.Servicios;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Dto.ServiciosExternos.ClaimsResponse;


@Stateless
public class FactCheckService {

    private static final String API_URL = "https://factchecktools.googleapis.com/v1alpha1/claims:search";
    private static final String API_KEY = System.getenv("ApiKeyFactCheck");
    private static final int PAGESIZE = 5;
    private static final String IDIOMA = "es";

    public ClaimsResponse obtenerHechos(String query, String nextPageToken) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL)
                .queryParam("query", query)
                .queryParam("pageSize", PAGESIZE)
                .queryParam("languageCode", IDIOMA)
                .queryParam("key", API_KEY);


        if (nextPageToken != null && !nextPageToken.isEmpty()) {
            target = target.queryParam("pageToken", nextPageToken);
        }

        Response response = target.request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(ClaimsResponse.class);
    }
}
