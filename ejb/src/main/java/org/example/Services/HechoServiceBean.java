package org.example.Services;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import org.example.Dto.HechoDto;
import org.example.Repository.HechoRepository;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.entity.Hecho;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public HechoDto obtenerHechoPorIdDto(int id) {
        return hechoToHechoDto(hechoRepository.obtenerHechoPorId(id));
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

    @Override
    public List<HechoDto> listarHechosDto() {
        List<Hecho> hechos= hechoRepository.listarHechos();
        List<HechoDto> hechoDtos = new ArrayList<>();
        for(Hecho hecho : hechos){
            hechoDtos.add(hechoToHechoDto(hecho));
        }
        return hechoDtos;
    }

    @Override
    public List<HechoDto> buscarHechosDto(String query) {
        List<Hecho> hechos = hechoRepository.buscarHechos(query);
        List<HechoDto> hechoDtos = new ArrayList<>();
        for(Hecho hecho : hechos){
            hechoDtos.add(hechoToHechoDto(hecho));
        }
        return hechoDtos;
    }

    private HechoDto hechoToHechoDto(Hecho hecho) {
        HechoDto hechoDto = new HechoDto();
        hechoDto.setIdHecho(hecho.getIdHecho());
        hechoDto.setDescripcion(hecho.getDescripcion());
        hechoDto.setEstado(hecho.getEstado());
        hechoDto.setAreaTematica(hecho.getAreaTematica());
        hechoDto.setFechaCreacion(hecho.getFechaCreacion());
        hechoDto.setFechaVerificacion(hecho.getFechaVerificacion());
        hechoDto.setJustificacion(hecho.getJustificacion());
        hechoDto.setCalificacion(hecho.getCalificacion());
        hechoDto.setPublicado(hechoDto.isPublicado());

        return hechoDto;
    }


}
