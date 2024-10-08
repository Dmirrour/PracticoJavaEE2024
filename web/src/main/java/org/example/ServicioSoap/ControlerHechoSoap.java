package org.example.ServicioSoap;

import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.Dto.CrearHechoDto;
import org.example.Dto.HechoDto;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.entity.Hecho;

import java.time.LocalDateTime;
import java.util.List;

@WebService
public class ControlerHechoSoap {

    @EJB
    private IHechoServiceRemote iHechoServiceRemote;

    @WebMethod
    public void addHecho(@WebParam(name = "newHecho") CrearHechoDto newHecho) {
        Hecho hecho = new Hecho();
        hecho.setAreaTematica(newHecho.getAreaTematica());
        hecho.setEstado(newHecho.getEstado());
        hecho.setFechaCreacion(LocalDateTime.now());
        hecho.setFechaVerificacion(newHecho.getFechaVerificacion());
        hecho.setCalificacion(newHecho.getCalificacion());
        hecho.setPublicado(newHecho.isPublicado());
        hecho.setDescripcion(newHecho.getDescripcion());
        hecho.setJustificacion(newHecho.getJustificacion());

        iHechoServiceRemote.agregarHecho(hecho);
    }

    @WebMethod
    public Hecho obtenerHechoPorId(@WebParam(name = "id") int id) {
        return iHechoServiceRemote.obtenerHechoPorId(id);
    }

    @WebMethod
    public List<HechoDto> listarHechos() {
        return iHechoServiceRemote.listarHechosDto();
    }

    @WebMethod
    public List<HechoDto> buscarHechos(@WebParam(name = "query") String query) {
        return iHechoServiceRemote.buscarHechosDto(query);
    }
}
