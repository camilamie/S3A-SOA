package br.com.fiap.s3a.controller;

import br.com.fiap.s3a.dto.request.AlertaRequest;
import br.com.fiap.s3a.dto.response.AlertaResponse;
import br.com.fiap.s3a.service.ITelemetriaService;
import br.com.fiap.s3a.service.impl.AlertaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;
    private final ITelemetriaService telemetriaService;

    @GetMapping
    public ResponseEntity<List<AlertaResponse>> listar() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }

    @GetMapping("/nao-resolvidos")
    public ResponseEntity<List<AlertaResponse>> naoResolvidos() {
        return ResponseEntity.ok(alertaService.listarNaoResolvidos());
    }

    @GetMapping("/missao/{missaoId}")
    public ResponseEntity<List<AlertaResponse>> porMissao(@PathVariable Long missaoId) {
        return ResponseEntity.ok(alertaService.listarPorMissao(missaoId));
    }

    @GetMapping("/droid/{droidId}")
    public ResponseEntity<List<AlertaResponse>> porDroid(@PathVariable Long droidId) {
        return ResponseEntity.ok(alertaService.listarPorDroid(droidId));
    }

    @PostMapping
    public ResponseEntity<AlertaResponse> criar(@Valid @RequestBody AlertaRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.criar(req));
    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<AlertaResponse> resolver(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.resolver(id));
    }

    @PostMapping("/verificar-saude/missao/{missaoId}")
    public ResponseEntity<String> verificarSaude(@PathVariable Long missaoId) {
        telemetriaService.verificarSaudeDosDroids(missaoId);
        return ResponseEntity.ok("Verificação concluída para missão " + missaoId);
    }
}