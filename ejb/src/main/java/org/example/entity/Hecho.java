package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Hecho implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHecho;
    private String descripcion;
    private String estado;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime fechaCreacion;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

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

