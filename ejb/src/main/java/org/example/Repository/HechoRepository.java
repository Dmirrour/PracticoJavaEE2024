package org.example.Repository;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    @PersistenceContext
    private EntityManager entityManager;

    public void agregarHecho(Hecho hecho) {
        entityManager.persist(hecho);
    }

    public Hecho obtenerHechoPorId(int id) {
        return entityManager.find(Hecho.class, id);
    }

    public List<Hecho> listarHechos() {
        return entityManager.createQuery("SELECT h FROM Hecho h", Hecho.class).getResultList();
    }

    public Hecho modificarHecho(Hecho hechoModificado) {
        return entityManager.merge(hechoModificado);
    }

    public void eliminarHecho(int id) {
        Hecho hecho = obtenerHechoPorId(id);
        if (hecho != null) {
            entityManager.remove(hecho);
        }
    }

    public List<Hecho> buscarHechos(String query) {
        String jpql = "SELECT h FROM Hecho h WHERE "
                + "LOWER(h.descripcion) LIKE :query OR "
                + "LOWER(h.justificacion) LIKE :query OR "
                + "CAST(h.idHecho AS string) LIKE :query OR "
                + "LOWER(h.estado) LIKE :query OR "
                + "CAST(h.calificacion AS string ) LIKE :query OR "
                + "CAST(h.areaTematica AS string ) LIKE :query";
        TypedQuery<Hecho> typedQuery = entityManager.createQuery(jpql, Hecho.class)
                .setParameter("query", "%" + query.toLowerCase() + "%");
        return typedQuery.getResultList();
    }

    public void insertarCasosDePrueba() {
        if (contarHechos() == 0) {
            agregarHecho(new Hecho(0, "Activo", "Se decreto Feriado Nacional este Lunes",
                    TipoCalificacion.FALSA, TipoCategoria.POLITICA,
                    LocalDateTime.now(), LocalDateTime.now(), "Justificación 1", true));
            agregarHecho(new Hecho(0, "Inactivo", "Según una encuesta la mayoría de personas prefieren la pizza con piña",
                    TipoCalificacion.INFLADO, TipoCategoria.SOCIEDAD,
                    LocalDateTime.now(), LocalDateTime.now(), "Justificación 2", false));
            agregarHecho(new Hecho(0, "En Espera", "El Dólar baja un 2%",
                    TipoCalificacion.ENGANOSO, TipoCategoria.ECONOMIA,
                    LocalDateTime.now(), LocalDateTime.now(), "Justificación 3", true));
        }
    }

    public long contarHechos() {
        return entityManager.createQuery("SELECT COUNT(h) FROM Hecho h", Long.class).getSingleResult();
    }

//Practico 1 ----------------------------------------------------
    /*private final List<Hecho> hechos = new ArrayList<>();
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
    }*/

}