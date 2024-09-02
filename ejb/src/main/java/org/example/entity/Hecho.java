package org.example.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Hecho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHecho;

    private String descripcion;

    private String estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaVerificacion;

    private String justificacion;

    @Enumerated(EnumType.STRING)
    private TipoCalificacion calificacion;

    private boolean publicado;

    @Enumerated(EnumType.STRING)
    private TipoCategoria areaTematica;
}

