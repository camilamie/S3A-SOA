package br.com.fiap.s3a.dto.request;

import br.com.fiap.s3a.domain.enums.CorpoCeleste;
import br.com.fiap.s3a.domain.enums.StatusMissao;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MissaoRequest {

    @NotBlank(message = "Nome da missão é obrigatório")
    private String nome;

    @NotNull(message = "Corpo celeste é obrigatório")
    private CorpoCeleste corpoCeleste;

    private StatusMissao status;
    private LocalDateTime dataLancamento;

    private String descricao;
}