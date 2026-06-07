package br.com.fiap.s3a.dto.response;

import br.com.fiap.s3a.domain.enums.TipoSensor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data @Builder
public class LeituraResponse {
    private Long sensorId;
    private TipoSensor tipoSensor;
    private double valor;
    private String unidade;
    private String relatorio;
    private LocalDateTime timestamp;
}