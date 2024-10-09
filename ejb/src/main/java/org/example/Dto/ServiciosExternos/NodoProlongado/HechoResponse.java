package org.example.Dto.ServiciosExternos.NodoProlongado;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Type.TipoCategoria;
import org.example.Type.TipoEstado;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HechoResponse {
    private long idHecho;
    private String frase;
    private String notasAdicionales;
    private TipoEstado estado;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;
    private TipoCategoria areaTematica;
    private String urlFuente;
    @JsonbTransient
    private List<VerificacionResponse> verificaciones;
}
