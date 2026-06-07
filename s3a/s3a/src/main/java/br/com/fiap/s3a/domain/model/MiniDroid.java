package br.com.fiap.s3a.domain.model;

import br.com.fiap.s3a.domain.enums.StatusDroid;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mini_droids")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = {"missao", "sensores", "alertas"})
public class MiniDroid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missao_id", nullable = false)
    private Missao missao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDroid status;

    private Double latitude;
    private Double longitude;

    @Column(name = "profundidade_metros")
    private Double profundidadeMetros;

    @Column(name = "data_fincagem")
    private LocalDateTime dataFincagem;

    @Column(name = "bateria_percentual")
    private Double bateriaPercentual;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;


    @JsonIgnore
    @OneToMany(mappedBy = "miniDroid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Sensor> sensores = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "miniDroid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alerta> alertas = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.status == null) this.status = StatusDroid.AGUARDANDO_LANCAMENTO;
    }

    public boolean bateriaEmNivelCritico() {
        return bateriaPercentual != null && bateriaPercentual < 15.0;
    }

    public boolean isOperacional() {
        return status == StatusDroid.ATIVO && !bateriaEmNivelCritico();
    }

    public void consumirBateria(double percentual) {
        if (bateriaPercentual != null) {
            bateriaPercentual = Math.max(0, bateriaPercentual - percentual);
            if (bateriaPercentual == 0) this.status = StatusDroid.FALHA;
        }
    }
}