package br.com.fiap.s3a.service.impl;

import br.com.fiap.s3a.domain.enums.StatusDroid;
import br.com.fiap.s3a.domain.model.MiniDroid;
import br.com.fiap.s3a.domain.model.Missao;
import br.com.fiap.s3a.dto.request.MiniDroidRequest;
import br.com.fiap.s3a.dto.response.MiniDroidResponse;
import br.com.fiap.s3a.exception.RecursoNaoEncontradoException;
import br.com.fiap.s3a.repository.MiniDroidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniDroidService {

    private final MiniDroidRepository droidRepository;
    private final MissaoService missaoService;

    public List<MiniDroidResponse> listarTodos() {
        return droidRepository.findAll().stream().map(MiniDroidResponse::from).toList();
    }

    public List<MiniDroidResponse> listarPorMissao(Long missaoId) {
        return droidRepository.findByMissaoId(missaoId).stream().map(MiniDroidResponse::from).toList();
    }

    public MiniDroidResponse buscarPorId(Long id) {
        return MiniDroidResponse.from(buscarEntidade(id));
    }

    public List<MiniDroidResponse> listarComBateriaCritica() {
        return droidRepository.findDroidsComBateriaCritica(15.0).stream()
                .map(MiniDroidResponse::from).toList();
    }

    @Transactional
    public MiniDroidResponse criar(MiniDroidRequest req) {
        Missao missao = missaoService.buscarEntidade(req.getMissaoId());
        MiniDroid droid = MiniDroid.builder()
                .codigo(req.getCodigo()).missao(missao)
                .status(req.getStatus() != null ? req.getStatus() : StatusDroid.AGUARDANDO_LANCAMENTO)
                .latitude(req.getLatitude()).longitude(req.getLongitude())
                .profundidadeMetros(req.getProfundidadeMetros())
                .dataFincagem(req.getDataFincagem())
                .bateriaPercentual(req.getBateriaPercentual() != null ? req.getBateriaPercentual() : 100.0)
                .build();
        return MiniDroidResponse.from(droidRepository.save(droid));
    }

    @Transactional
    public MiniDroidResponse atualizar(Long id, MiniDroidRequest req) {
        MiniDroid droid = buscarEntidade(id);
        Missao missao = missaoService.buscarEntidade(req.getMissaoId());
        droid.setCodigo(req.getCodigo()); droid.setMissao(missao);
        if (req.getStatus() != null) droid.setStatus(req.getStatus());
        droid.setLatitude(req.getLatitude()); droid.setLongitude(req.getLongitude());
        droid.setProfundidadeMetros(req.getProfundidadeMetros());
        droid.setDataFincagem(req.getDataFincagem());
        if (req.getBateriaPercentual() != null) droid.setBateriaPercentual(req.getBateriaPercentual());
        return MiniDroidResponse.from(droidRepository.save(droid));
    }

    @Transactional
    public void deletar(Long id) {
        droidRepository.delete(buscarEntidade(id));
    }

    public MiniDroid buscarEntidade(Long id) {
        return droidRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("MiniDroid", id));
    }
}