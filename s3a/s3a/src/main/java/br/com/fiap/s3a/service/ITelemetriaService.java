package br.com.fiap.s3a.service;

import br.com.fiap.s3a.dto.response.LeituraResponse;
import java.util.List;


public interface ITelemetriaService {
    List<LeituraResponse> coletarLeituras(Long miniDroidId);
    String gerarRelatorio(Long miniDroidId);
    void verificarSaudeDosDroids(Long missaoId);
}