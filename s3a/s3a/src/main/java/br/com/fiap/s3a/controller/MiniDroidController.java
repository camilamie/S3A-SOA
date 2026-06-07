package br.com.fiap.s3a.controller;

import br.com.fiap.s3a.dto.request.MiniDroidRequest;
import br.com.fiap.s3a.dto.response.LeituraResponse;
import br.com.fiap.s3a.dto.response.MiniDroidResponse;
import br.com.fiap.s3a.service.ITelemetriaService;
import br.com.fiap.s3a.service.impl.MiniDroidService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/droids")
@RequiredArgsConstructor
public class MiniDroidController {

    private final MiniDroidService droidService;
    private final ITelemetriaService telemetriaService;

    @GetMapping
    public ResponseEntity<List<MiniDroidResponse>> listar() {
        return ResponseEntity.ok(droidService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiniDroidResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(droidService.buscarPorId(id));
    }

    @GetMapping("/missao/{missaoId}")
    public ResponseEntity<List<MiniDroidResponse>> listarPorMissao(@PathVariable Long missaoId) {
        return ResponseEntity.ok(droidService.listarPorMissao(missaoId));
    }

    @GetMapping("/bateria-critica")
    public ResponseEntity<List<MiniDroidResponse>> bateriaCritica() {
        return ResponseEntity.ok(droidService.listarComBateriaCritica());
    }

    @PostMapping
    public ResponseEntity<MiniDroidResponse> criar(@Valid @RequestBody MiniDroidRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(droidService.criar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiniDroidResponse> atualizar(@PathVariable Long id,
                                                       @Valid @RequestBody MiniDroidRequest req) {
        return ResponseEntity.ok(droidService.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        droidService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/leituras")
    public ResponseEntity<List<LeituraResponse>> coletarLeituras(@PathVariable Long id) {
        return ResponseEntity.ok(telemetriaService.coletarLeituras(id));
    }

    @GetMapping("/{id}/relatorio")
    public ResponseEntity<String> relatorio(@PathVariable Long id) {
        return ResponseEntity.ok(telemetriaService.gerarRelatorio(id));
    }
}