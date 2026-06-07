package br.com.fiap.s3a.domain.model;

import br.com.fiap.s3a.domain.enums.NivelAlerta;
import br.com.fiap.s3a.domain.enums.TipoAlerta;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "alertas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mini_droid_id", nullable = false)
    private MiniDroid miniDroid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAlerta tipo;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAlerta nivel;

    @Column(nullable = false)
    @Builder.Default
    private boolean resolvido = false;

    @Column(name = "data_alerta", updatable = false)
    private LocalDateTime dataAlerta;

    @Column(name = "data_resolucao")
    private LocalDateTime dataResolucao;

    @PrePersist
    protected void onCreate() {
        if (this.dataAlerta == null) this.dataAlerta = LocalDateTime.now();
    }

    public void resolver() {
        this.resolvido = true;
        this.dataResolucao = LocalDateTime.now();
    }
}