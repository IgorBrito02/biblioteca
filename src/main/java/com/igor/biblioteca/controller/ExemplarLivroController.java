package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.ExemplarLivroDto;
import com.igor.biblioteca.service.ExemplarLivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exemplares")
@RequiredArgsConstructor
public class ExemplarLivroController {

    private final ExemplarLivroService exemplarService;

    @GetMapping
    public ResponseEntity<List<ExemplarLivroDto>> listar() {
        return ResponseEntity.ok(exemplarService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemplarLivroDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(exemplarService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ExemplarLivroDto> salvar(@RequestBody ExemplarLivroDto dto) {
        return ResponseEntity.ok(exemplarService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExemplarLivroDto> atualizar(@PathVariable UUID id, @RequestBody ExemplarLivroDto dto) {
        return ResponseEntity.ok(exemplarService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        exemplarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}