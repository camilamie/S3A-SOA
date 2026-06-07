package br.com.fiap.s3a.service.impl;

import br.com.fiap.s3a.domain.enums.NivelAlerta;
import br.com.fiap.s3a.domain.enums.TipoAlerta;
import br.com.fiap.s3a.domain.model.*;
import br.com.fiap.s3a.dto.response.LeituraResponse;
import br.com.fiap.s3a.exception.DroidInoperanteException;
import br.com.fiap.s3a.repository.AlertaRepository;
import br.com.fiap.s3a.repository.MiniDroidRepository;
import br.com.fiap.s3a.repository.SensorRepository;
import br.com.fiap.s3a.service.ITelemetriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TelemetriaServiceImpl implements ITelemetriaService {

    private final MiniDroidService droidService;
    private final SensorRepository sensorRepository;
    private final AlertaRepository alertaRepository;
    private final MiniDroidRepository droidRepository;

    @Override
    @Transactional
    public List<LeituraResponse> coletarLeituras(Long miniDroidId) {
        MiniDroid droid = droidService.buscarEntidade(miniDroidId);

        if (!droid.isOperacional())
            throw new DroidInoperanteException(droid.getCodigo());

        List<Sensor> sensores = sensorRepository.findByMiniDroidIdAndAtivo(miniDroidId, true);
        List<LeituraResponse> leituras = new ArrayList<>();

        for (Sensor sensor : sensores) {
            double valor = sensor.lerDado();
            String unidade = sensor instanceof SensorSismico ? "m/s²" : "reflectância";

            leituras.add(LeituraResponse.builder()
                    .sensorId(sensor.getId()).tipoSensor(sensor.getTipo())
                    .valor(valor).unidade(unidade)
                    .relatorio(sensor.relatorio())
                    .timestamp(LocalDateTime.now())
                    .build());

            sensorRepository.save(sensor);

            if (sensor instanceof SensorOptico optico && optico.detectouCavidade())
                gerarAlertaCavidade(droid, valor);
        }

        droid.consumirBateria(0.1);
        droidRepository.save(droid);

        log.info("[TELEMETRIA] Droid {} — {} leituras coletadas", droid.getCodigo(), leituras.size());
        return leituras;
    }

    @Override
    public String gerarRelatorio(Long miniDroidId) {
        MiniDroid droid = droidService.buscarEntidade(miniDroidId);
        List<Sensor> sensores = sensorRepository.findByMiniDroidId(miniDroidId);

        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO — ").append(droid.getCodigo()).append(" ===\n");
        sb.append("Status: ").append(droid.getStatus())
                .append(" | Bateria: ").append(String.format("%.1f%%", droid.getBateriaPercentual())).append("\n");
        sb.append("Posição: ").append(droid.getLatitude()).append(", ").append(droid.getLongitude()).append("\n\n");


        for (Sensor sensor : sensores)
            sb.append(sensor.relatorio()).append("\n");

        return sb.toString();
    }

    @Override
    @Transactional
    public void verificarSaudeDosDroids(Long missaoId) {
        droidRepository.findDroidsComBateriaCritica(15.0).stream()
                .filter(d -> d.getMissao().getId().equals(missaoId))
                .forEach(d -> {
                    boolean jaTemAlerta = alertaRepository.findByMiniDroidId(d.getId()).stream()
                            .anyMatch(a -> a.getTipo() == TipoAlerta.BATERIA_CRITICA && !a.isResolvido());
                    if (!jaTemAlerta) {
                        Alerta alerta = Alerta.builder()
                                .miniDroid(d).tipo(TipoAlerta.BATERIA_CRITICA)
                                .mensagem("Bateria em " + String.format("%.1f%%", d.getBateriaPercentual()))
                                .nivel(NivelAlerta.CRITICO).build();
                        alertaRepository.save(alerta);
                        log.warn("[SAÚDE] Alerta CRITICO gerado para droid {}", d.getCodigo());
                    }
                });
    }

    private void gerarAlertaCavidade(MiniDroid droid, double reflectancia) {
        Alerta alerta = Alerta.builder()
                .miniDroid(droid).tipo(TipoAlerta.CAVIDADE_DETECTADA)
                .mensagem(String.format("Reflectância %.3f sugere cavidade — ativar tomografia", reflectancia))
                .nivel(NivelAlerta.ALTO).build();
        alertaRepository.save(alerta);
    }
}