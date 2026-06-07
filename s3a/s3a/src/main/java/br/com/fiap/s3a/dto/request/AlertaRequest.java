package br.com.fiap.s3a.dto.request;

import br.com.fiap.s3a.domain.enums.NivelAlerta;
import br.com.fiap.s3a.domain.enums.TipoAlerta;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlertaRequest {

    @NotNull(message = "ID do mini-droid é obrigatório")
    private Long miniDroidId;

    @NotNull(message = "Tipo do alerta é obrigatório")
    private TipoAlerta tipo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotNull(message = "Nível do alerta é obrigatório")
    private NivelAlerta nivel;
}