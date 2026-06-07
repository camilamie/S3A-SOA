package br.com.fiap.s3a.service.impl;

import br.com.fiap.s3a.domain.enums.StatusMissao;
import br.com.fiap.s3a.domain.model.Missao;
import br.com.fiap.s3a.dto.request.MissaoRequest;
import br.com.fiap.s3a.dto.response.MissaoResponse;
import br.com.fiap.s3a.exception.RecursoNaoEncontradoException;
import br.com.fiap.s3a.repository.MissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissaoService {

    private final MissaoRepository missaoRepository;

    public List<MissaoResponse> listarTodas() {
        return missaoRepository.findAll().stream().map(MissaoResponse::from).toList();
    }

    public MissaoResponse buscarPorId(Long id) {
        return MissaoResponse.from(buscarEntidade(id));
    }

    @Transactional
    public MissaoResponse criar(MissaoRequest req) {
        Missao missao = Missao.builder()
                .nome(req.getNome()).corpoCeleste(req.getCorpoCeleste())
                .status(req.getStatus() != null ? req.getStatus() : StatusMissao.PLANEJADA)
                .dataLancamento(req.getDataLancamento()).descricao(req.getDescricao())
                .build();
        return MissaoResponse.from(missaoRepository.save(missao));
    }

    @Transactional
    public MissaoResponse atualizar(Long id, MissaoRequest req) {
        Missao missao = buscarEntidade(id);
        missao.setNome(req.getNome());
        missao.setCorpoCeleste(req.getCorpoCeleste());
        if (req.getStatus() != null) missao.setStatus(req.getStatus());
        missao.setDataLancamento(req.getDataLancamento());
        missao.setDescricao(req.getDescricao());
        return MissaoResponse.from(missaoRepository.save(missao));
    }

    @Transactional
    public void deletar(Long id) {
        missaoRepository.delete(buscarEntidade(id));
    }

    public Missao buscarEntidade(Long id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Missão", id));
    }
}