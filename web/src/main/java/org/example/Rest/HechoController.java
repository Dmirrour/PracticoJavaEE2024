package org.example.Rest;

import jakarta.ejb.EJB;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.example.Dto.CrearHechoDto;
import org.example.Dto.HechoDto;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.entity.Hecho;

import java.time.LocalDateTime;
import java.util.List;

@Path("/hechos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HechoController {
    @EJB
    private IHechoServiceRemote hechoServiceRemote;



    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarHecho(CrearHechoDto newHecho) {

        Hecho hecho = new Hecho();
        hecho.setAreaTematica(newHecho.getAreaTematica());
        hecho.setEstado(newHecho.getEstado());
        hecho.setFechaCreacion(LocalDateTime.now());
        hecho.setFechaVerificacion(newHecho.getFechaVerificacion());
        hecho.setCalificacion(newHecho.getCalificacion());
        hecho.setPublicado(newHecho.isPublicado());
        hecho.setDescripcion(newHecho.getDescripcion());
        hecho.setJustificacion(newHecho.getJustificacion());

        hechoServiceRemote.agregarHecho(hecho);
        return Response.status(Response.Status.CREATED).entity(newHecho).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerHechoPorId(@PathParam("id") int id) {
        HechoDto hecho = hechoServiceRemote.obtenerHechoPorIdDto(id);
        if (hecho != null) {
            return Response.ok(hecho).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarHechos() {
        List<HechoDto> hechos = hechoServiceRemote.listarHechosDto();

        return Response.ok(hechos).build();

    }

    @GET
    @Path("/buscar/{query}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarHechos(@PathParam("query") String query) {
        List<HechoDto> hechos = hechoServiceRemote.buscarHechosDto(query);
        return Response.ok(hechos).build();
    }
}
