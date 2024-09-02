package org.example.Services;

import org.example.Repository.HechoRepository;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.entity.Hecho;
import jakarta.inject.Inject;
import java.util.List;

public class HechoServiceBean implements IHechoServiceLocal, IHechoServiceRemote {

    @Inject
    private HechoRepository hechoRepository;

    @Override
    public void agregarHecho(Hecho hecho) {
        hechoRepository.agregarHecho(hecho);
    }

    @Override
    public Hecho obtenerHechoPorId(int id) {
        return hechoRepository.obtenerHechoPorId(id);
    }

    @Override
    public List<Hecho> listarHechos() {
        return hechoRepository.listarHechos();
    }

    @Override
    public void modificarHecho(Hecho hecho) {
        hechoRepository.modificarHecho(hecho);
    }

    @Override
    public void eliminarHecho(int id) {
        hechoRepository.eliminarHecho(id);
    }
}
