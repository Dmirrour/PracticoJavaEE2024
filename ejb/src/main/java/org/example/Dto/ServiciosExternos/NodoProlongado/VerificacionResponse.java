package org.example.Dto.ServiciosExternos.NodoProlongado;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Type.TipoCalificacion;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificacionResponse {
    private long idVerificacion;
    private boolean activo;
    private String justificacion;
    private LocalDateTime fechaComienzoVerificacion;
    private LocalDateTime fechaFinVerificacion;
    @Enumerated(EnumType.STRING)
    private TipoCalificacion calificacion;
}
