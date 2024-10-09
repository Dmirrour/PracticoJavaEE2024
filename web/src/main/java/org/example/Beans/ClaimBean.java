package org.example.Beans;

import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;
import lombok.Data;
import org.example.Dto.ServiciosExternos.ClaimsResponse;
import org.example.Dto.ServiciosExternos.NodoProlongado.HechoResponse;
import org.example.Servicios.FactCheckService;
import org.example.Servicios.NodoProlongado;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Named
@ManagedBean
@ViewScoped
public class ClaimBean implements Serializable {

    @EJB
    private FactCheckService factCheckService;
    @EJB
    private NodoProlongado nodoProlongado;

    private ClaimsResponse claimsResponse;
    private String query;
    private String nextPageToken;
    private HechoResponse hechoResponse = new HechoResponse();
    private int id;




    public void buscarHechos() {
        claimsResponse = factCheckService.obtenerHechos(query, null);
        nextPageToken = claimsResponse.getNextPageToken();
    }

    public void siguientePagina() {
        if (nextPageToken != null) {
            claimsResponse = factCheckService.obtenerHechos(query, nextPageToken);
            nextPageToken = claimsResponse.getNextPageToken();
        }
    }

    public void obtenerHecho() {
        hechoResponse = nodoProlongado.obtenerHecho(id);
        if (hechoResponse == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El hecho no se encontró"));
        }
    }

    public String formatearFecha(LocalDateTime fecha) {
        if (fecha != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return fecha.format(formatter);
        }
        return "";
    }
    public void agregarHecho() {
        System.out.println(hechoResponse);
        Response response = nodoProlongado.agregarHecho(
                hechoResponse.getFrase(),
                hechoResponse.getNotasAdicionales(),
                hechoResponse.getEstado().toString(),
                hechoResponse.getFechaCreacion(),
                hechoResponse.getAreaTematica().toString(),
                hechoResponse.getUrlFuente()
        );

        // Manejo de la respuesta
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Hecho agregado exitosamente");
        } else {
            System.err.println("Error al agregar hecho: " + response.getStatus());
        }

        hechoResponse = new HechoResponse(); // Resetea el objeto para permitir agregar otro hecho
    }

}
