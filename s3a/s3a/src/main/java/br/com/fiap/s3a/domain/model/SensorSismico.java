package br.com.fiap.s3a.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("SISMOMETRO")
@NoArgsConstructor
public class SensorSismico extends Sensor {

    private static final double LIMIAR_EVENTO = 0.001;

    public SensorSismico(MiniDroid miniDroid) {
        super(null, miniDroid, null, true, null, null);
    }

    @Override
    public double lerDado() {
        // Simula aceleração sísmica em m/s²
        double leitura = Math.random() * 0.005;
        registrarLeitura(leitura);
        return leitura;
    }

    @Override
    public String relatorio() {
        if (getUltimaLeitura() == null) return "Sismômetro sem leituras.";
        String evento = getUltimaLeitura() > LIMIAR_EVENTO
                ? "EVENTO SÍSMICO DETECTADO" : "Sem evento relevante";
        return String.format("[SISMÔMETRO | Droid %s] Aceleração: %.6f m/s² | %s | %s",
                getMiniDroid() != null ? getMiniDroid().getCodigo() : "N/A",
                getUltimaLeitura(), evento, getDataUltimaLeitura());
    }
}