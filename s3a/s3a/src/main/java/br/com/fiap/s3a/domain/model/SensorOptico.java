package br.com.fiap.s3a.domain.model;

import br.com.fiap.s3a.domain.enums.TipoSensor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("OPTICO_REFLEXAO")
@NoArgsConstructor
@Setter
public class SensorOptico extends Sensor {

    private static final double LIMIAR_CAVIDADE = 0.70;

    @Enumerated(EnumType.STRING)
    @Column(name = "modo_optico")
    private TipoSensor modoOptico;

    public SensorOptico(MiniDroid miniDroid, TipoSensor modoOptico) {
        super(null, miniDroid, null, true, null, null);
        this.modoOptico = modoOptico;
    }

    @Override
    public double lerDado() {
        double leitura = Math.random();
        registrarLeitura(leitura);
        return leitura;
    }

    @Override
    public String relatorio() {
        if (getUltimaLeitura() == null) return "Sensor óptico sem leituras.";
        String alerta = getUltimaLeitura() > LIMIAR_CAVIDADE ? "POSSÍVEL CAVIDADE" : "Normal";
        String modo = modoOptico != null ? modoOptico.name() : "OPTICO";
        return String.format("[%s | Droid %s] Reflectância: %.3f | %s | %s",
                modo,
                getMiniDroid() != null ? getMiniDroid().getCodigo() : "N/A",
                getUltimaLeitura(), alerta, getDataUltimaLeitura());
    }

    public boolean detectouCavidade() {
        return getUltimaLeitura() != null && getUltimaLeitura() > LIMIAR_CAVIDADE;
    }
}