package org.example.Services.InterfaceService;

import jakarta.ejb.Remote;
import org.example.entity.Hecho;

import java.util.List;

@Remote
public interface IHechoServiceRemote {
    Hecho obtenerHechoPorId(int id);
    List<Hecho> listarHechos();
    List<Hecho> buscarHechos(String query);
    void agregarHecho(Hecho hecho);

}
