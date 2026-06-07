package br.com.fiap.s3a.service.impl;

import br.com.fiap.s3a.domain.model.Alerta;
import br.com.fiap.s3a.domain.model.MiniDroid;
import br.com.fiap.s3a.dto.request.AlertaRequest;
import br.com.fiap.s3a.dto.response.AlertaResponse;
import br.com.fiap.s3a.exception.RecursoNaoEncontradoException;
import br.com.fiap.s3a.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final MiniDroidService droidService;

    public List<AlertaResponse> listarTodos() {
        return alertaRepository.findAll().stream().map(AlertaResponse::from).toList();
    }

    public List<AlertaResponse> listarNaoResolvidos() {
        return alertaRepository.findByResolvidoFalse().stream().map(AlertaResponse::from).toList();
    }

    public List<AlertaResponse> listarPorMissao(Long missaoId) {
        return alertaRepository.findByMiniDroidMissaoId(missaoId).stream().map(AlertaResponse::from).toList();
    }

    public List<AlertaResponse> listarPorDroid(Long droidId) {
        return alertaRepository.findByMiniDroidId(droidId).stream().map(AlertaResponse::from).toList();
    }

    @Transactional
    public AlertaResponse criar(AlertaRequest req) {
        MiniDroid droid = droidService.buscarEntidade(req.getMiniDroidId());
        Alerta alerta = Alerta.builder()
                .miniDroid(droid).tipo(req.getTipo())
                .mensagem(req.getMensagem()).nivel(req.getNivel())
                .build();
        return AlertaResponse.from(alertaRepository.save(alerta));
    }

    @Transactional
    public AlertaResponse resolver(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta", id));
        if (alerta.isResolvido())
            throw new IllegalArgumentException("Alerta " + id + " já foi resolvido.");
        alerta.resolver();
        return AlertaResponse.from(alertaRepository.save(alerta));
    }
}