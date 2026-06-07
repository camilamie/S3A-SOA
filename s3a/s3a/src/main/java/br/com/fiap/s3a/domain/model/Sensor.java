package br.com.fiap.s3a.domain.model;

import br.com.fiap.s3a.domain.enums.TipoSensor;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "sensores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mini_droid_id", nullable = false)
    private MiniDroid miniDroid;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private TipoSensor tipo;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "ultima_leitura")
    private Double ultimaLeitura;

    @Column(name = "data_ultima_leitura")
    private LocalDateTime dataUltimaLeitura;


    public abstract double lerDado();

    public abstract String relatorio();

    protected void registrarLeitura(double valor) {
        this.ultimaLeitura = valor;
        this.dataUltimaLeitura = LocalDateTime.now();
    }
}