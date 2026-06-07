package br.com.fiap.s3a.dto.response;

import br.com.fiap.s3a.domain.enums.CorpoCeleste;
import br.com.fiap.s3a.domain.enums.StatusMissao;
import br.com.fiap.s3a.domain.model.Missao;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class MissaoResponse {
    private Long id;
    private String nome;
    private CorpoCeleste corpoCeleste;
    private StatusMissao status;
    private LocalDateTime dataLancamento;
    private String descricao;
    private LocalDateTime dataCriacao;
    private int totalDroids;
    private long droidsAtivos;

    public static MissaoResponse from(Missao m) {
        return MissaoResponse.builder()
                .id(m.getId()).nome(m.getNome())
                .corpoCeleste(m.getCorpoCeleste()).status(m.getStatus())
                .dataLancamento(m.getDataLancamento()).descricao(m.getDescricao())
                .dataCriacao(m.getDataCriacao())
                .totalDroids(m.getMiniDroids().size())
                .droidsAtivos(m.totalDroidsAtivos())
                .build();
    }
}