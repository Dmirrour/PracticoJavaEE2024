package org.example.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Helper.LocalDateTimeAdapter;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;

import java.io.Serializable;
import java.time.LocalDateTime;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HechoDto implements Serializable {
    private int idHecho;
    private String descripcion;
    private String estado;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaCreacion;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaVerificacion;

    private String justificacion;
    private TipoCalificacion calificacion;
    private boolean publicado;
    private TipoCategoria areaTematica;
}
