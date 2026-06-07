package br.com.fiap.s3a.controller;

import br.com.fiap.s3a.dto.request.MissaoRequest;
import br.com.fiap.s3a.dto.response.MissaoResponse;
import br.com.fiap.s3a.service.impl.MissaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missoes")
@RequiredArgsConstructor
public class MissaoController {

    private final MissaoService missaoService;

    @GetMapping
    public ResponseEntity<List<MissaoResponse>> listar() {
        return ResponseEntity.ok(missaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissaoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(missaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MissaoResponse> criar(@Valid @RequestBody MissaoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missaoService.criar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissaoResponse> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody MissaoRequest req) {
        return ResponseEntity.ok(missaoService.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        missaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}