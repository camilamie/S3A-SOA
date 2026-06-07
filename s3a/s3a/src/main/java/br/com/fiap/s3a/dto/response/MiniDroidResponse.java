package br.com.fiap.s3a.dto.response;

import br.com.fiap.s3a.domain.enums.StatusDroid;
import br.com.fiap.s3a.domain.model.MiniDroid;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class MiniDroidResponse {
    private Long id;
    private String codigo;
    private Long missaoId;
    private String nomeMissao;
    private StatusDroid status;
    private Double latitude;
    private Double longitude;
    private Double profundidadeMetros;
    private LocalDateTime dataFincagem;
    private Double bateriaPercentual;
    private boolean bateriaCritica;
    private boolean operacional;
    private int totalSensores;
    private LocalDateTime dataCriacao;

    public static MiniDroidResponse from(MiniDroid d) {
        return MiniDroidResponse.builder()
                .id(d.getId()).codigo(d.getCodigo())
                .missaoId(d.getMissao() != null ? d.getMissao().getId() : null)
                .nomeMissao(d.getMissao() != null ? d.getMissao().getNome() : null)
                .status(d.getStatus()).latitude(d.getLatitude()).longitude(d.getLongitude())
                .profundidadeMetros(d.getProfundidadeMetros())
                .dataFincagem(d.getDataFincagem())
                .bateriaPercentual(d.getBateriaPercentual())
                .bateriaCritica(d.bateriaEmNivelCritico())
                .operacional(d.isOperacional())
                .totalSensores(d.getSensores().size())
                .dataCriacao(d.getDataCriacao())
                .build();
    }
}