package br.com.fiap.s3a.dto.request;

import br.com.fiap.s3a.domain.enums.StatusDroid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MiniDroidRequest {

    @NotBlank(message = "Código do droid é obrigatório")
    private String codigo;

    @NotNull(message = "ID da missão é obrigatório")
    private Long missaoId;

    private StatusDroid status;

    @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
    private Double latitude;

    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
    private Double longitude;

    @PositiveOrZero(message = "Profundidade deve ser >= 0")
    private Double profundidadeMetros;

    private LocalDateTime dataFincagem;

    @DecimalMin("0.0") @DecimalMax("100.0")
    private Double bateriaPercentual;
}