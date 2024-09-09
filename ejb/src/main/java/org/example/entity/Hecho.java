package org.example.entity;

import lombok.Data;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Hecho implements Serializable {
    private int idHecho;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVerificacion;
    private String justificacion;
    private TipoCalificacion calificacion;
    private boolean publicado;
    private TipoCategoria areaTematica;

    public Hecho(){};
    public Hecho(int idHecho,String estado, String descripcion, TipoCalificacion calificacion, TipoCategoria areaTematica, LocalDateTime fechaCreacion, LocalDateTime fechaVerificacion, String justificacion, boolean publicado) {
        this.idHecho = idHecho;
        this.estado = estado;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.areaTematica = areaTematica;
        this.fechaCreacion = fechaCreacion;
        this.fechaVerificacion = fechaVerificacion;
        this.justificacion = justificacion;
        this.publicado = publicado;
    }
}

