package org.example.Repository;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import org.example.entity.Hecho;

import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
@Lock(LockType.READ)
public class HechoRepository {

    private final List<Hecho> hechos = new ArrayList<>();

    @Lock(LockType.WRITE)
    public void agregarHecho(Hecho hecho) {
        hechos.add(hecho);
    }

    public Hecho obtenerHechoPorId(int id) {
        return hechos.stream()
                .filter(h -> h.getIdHecho() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Hecho> listarHechos() {
        return new ArrayList<>(hechos);
    }

    public void modificarHecho(Hecho hechoModificado) {
        Hecho hechoExistente = obtenerHechoPorId(hechoModificado.getIdHecho());
        if (hechoExistente != null) {
            hechoExistente.setDescripcion(hechoModificado.getDescripcion());
            hechoExistente.setCalificacion(hechoModificado.getCalificacion());
            hechoExistente.setAreaTematica(hechoModificado.getAreaTematica());
            hechoExistente.setFechaVerificacion(hechoModificado.getFechaVerificacion());
            hechoExistente.setJustificacion(hechoModificado.getJustificacion());
            hechoExistente.setPublicado(hechoModificado.isPublicado());
        }
    }

    public void eliminarHecho(int id) {
        hechos.removeIf(hecho -> hecho.getIdHecho() == id);
    }
}