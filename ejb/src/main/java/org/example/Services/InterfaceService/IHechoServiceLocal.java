package org.example.Services.InterfaceService;

import jakarta.ejb.Local;
import org.example.entity.Hecho;

import java.util.List;

@Local
public interface IHechoServiceLocal {//Solo podran acceder quellos que esten dentro del mismo modulo
    void agregarHecho(Hecho hecho);
    Hecho obtenerHechoPorId(int id);
    List<Hecho> listarHechos();

    Hecho modificarHecho(Hecho hecho);

    void eliminarHecho(int id);

    List<Hecho> buscarHechos(String query);
    void insertarCasosDePrueba();
}
