package org.example.Services.InterfaceService;

import jakarta.ejb.Remote;
import org.example.Dto.HechoDto;
import org.example.entity.Hecho;

import java.util.List;

@Remote
public interface IHechoServiceRemote {
    Hecho obtenerHechoPorId(int id);
    List<Hecho> listarHechos();
    List<Hecho> buscarHechos(String query);
    void agregarHecho(Hecho hecho);
    public List<HechoDto> listarHechosDto();
    List<HechoDto> buscarHechosDto(String query);
    HechoDto obtenerHechoPorIdDto(int id);


}
