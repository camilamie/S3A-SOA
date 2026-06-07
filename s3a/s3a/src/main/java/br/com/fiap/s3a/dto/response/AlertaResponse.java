package br.com.fiap.s3a.dto.response;

import br.com.fiap.s3a.domain.enums.NivelAlerta;
import br.com.fiap.s3a.domain.enums.TipoAlerta;
import br.com.fiap.s3a.domain.model.Alerta;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class AlertaResponse {
    private Long id;
    private Long miniDroidId;
    private String codigoDroid;
    private TipoAlerta tipo;
    private String mensagem;
    private NivelAlerta nivel;
    private boolean resolvido;
    private LocalDateTime dataAlerta;
    private LocalDateTime dataResolucao;

    public static AlertaResponse from(Alerta a) {
        return AlertaResponse.builder()
                .id(a.getId())
                .miniDroidId(a.getMiniDroid() != null ? a.getMiniDroid().getId() : null)
                .codigoDroid(a.getMiniDroid() != null ? a.getMiniDroid().getCodigo() : null)
                .tipo(a.getTipo()).mensagem(a.getMensagem()).nivel(a.getNivel())
                .resolvido(a.isResolvido())
                .dataAlerta(a.getDataAlerta()).dataResolucao(a.getDataResolucao())
                .build();
    }
}