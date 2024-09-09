package org.example.Services;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import org.example.Repository.HechoRepository;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.entity.Hecho;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
@Named("HechoServiceBean")
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
    public Hecho modificarHecho(Hecho hecho) {
        return hechoRepository.modificarHecho(hecho);
    }

    @Override
    public void eliminarHecho(int id) {
        hechoRepository.eliminarHecho(id);
    }

    @Override
    public List<Hecho> buscarHechos(String query) {
        return hechoRepository.buscarHechos(query);
    }

    @Override
    public void insertarCasosDePrueba() {
        hechoRepository.insertarCasosDePrueba();
    }


}
