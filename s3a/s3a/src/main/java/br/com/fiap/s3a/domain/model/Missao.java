package br.com.fiap.s3a.domain.model;

import br.com.fiap.s3a.domain.enums.CorpoCeleste;
import br.com.fiap.s3a.domain.enums.StatusMissao;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "missoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = "miniDroids")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "corpo_celeste", nullable = false)
    private CorpoCeleste corpoCeleste;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMissao status;

    @Column(name = "data_lancamento")
    private LocalDateTime dataLancamento;

    @Column(length = 500)
    private String descricao;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;


    @JsonIgnore
    @OneToMany(mappedBy = "missao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MiniDroid> miniDroids = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.status == null) this.status = StatusMissao.PLANEJADA;
    }

    public long totalDroidsAtivos() {
        return miniDroids.stream()
                .filter(d -> d.getStatus() == br.com.fiap.s3a.domain.enums.StatusDroid.ATIVO)
                .count();
    }
}