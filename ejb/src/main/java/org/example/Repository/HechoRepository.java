package org.example.Repository;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;
import org.example.entity.Hecho;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
@Lock(LockType.READ)
public class HechoRepository {

    private final List<Hecho> hechos = new ArrayList<>();
    private int ultimoId = 0;

    @Lock(LockType.WRITE)
    public void agregarHecho(Hecho hecho) {
        hecho.setIdHecho(++ultimoId);
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

    public Hecho modificarHecho(Hecho hechoModificado) {
        Hecho hechoExistente = obtenerHechoPorId(hechoModificado.getIdHecho());
        if (hechoExistente != null) {
            hechoExistente.setDescripcion(hechoModificado.getDescripcion());
            hechoExistente.setCalificacion(hechoModificado.getCalificacion());
            hechoExistente.setAreaTematica(hechoModificado.getAreaTematica());
            hechoExistente.setFechaVerificacion(hechoModificado.getFechaVerificacion());
            hechoExistente.setJustificacion(hechoModificado.getJustificacion());
            hechoExistente.setPublicado(hechoModificado.isPublicado());
        }
        return hechoExistente;
    }

    public void eliminarHecho(int id) {
        hechos.removeIf(hecho -> hecho.getIdHecho() == id);
    }


    public List<Hecho> buscarHechos(String query) {
        return hechos.stream()
                .filter(h -> filtrarHecho(h, query))
                .collect(Collectors.toList());
    }

    private boolean filtrarHecho(Hecho hecho, String query) {
        String lowerQuery = query.toLowerCase();
        return String.valueOf(hecho.getIdHecho()).contains(query)
                || hecho.getDescripcion().toLowerCase().contains(lowerQuery)
                || hecho.getJustificacion().toLowerCase().contains(lowerQuery)
                || hecho.getEstado().toLowerCase().contains(lowerQuery)
                || hecho.getAreaTematica().toString().toLowerCase().contains(lowerQuery);
    }

    public void insertarCasosDePrueba() {
        if (hechos.isEmpty()) {
            hechos.add(new Hecho(++ultimoId,"Activo", "Se decreto Feriado Nacional este Lunes "+(ultimoId), TipoCalificacion.FALSA, TipoCategoria.POLITICA, LocalDateTime.now(), LocalDateTime.now(), "Justificación "+(ultimoId), true));
            hechos.add(new Hecho(++ultimoId,"Inactivo", "Segun una encuesta la mayoria de personas prefieren la pizza con piña "+(ultimoId), TipoCalificacion.INFLADO, TipoCategoria.SOCIEDAD, LocalDateTime.now(), LocalDateTime.now(), "Justificación "+ (ultimoId), false));
            hechos.add(new Hecho(++ultimoId,"En Espera", "El Dolar baja una 2% "+(ultimoId), TipoCalificacion.ENGANOSO, TipoCategoria.ECONOMIA, LocalDateTime.now(), LocalDateTime.now(), "Justificación "+ (ultimoId), true));
        }
    }

}