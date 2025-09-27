package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.AutorDto;
import com.igor.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDto>> listar() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AutorDto> salvar(@RequestBody AutorDto dto) {
        return ResponseEntity.ok(autorService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDto> atualizar(@PathVariable UUID id, @RequestBody AutorDto dto) {
        return ResponseEntity.ok(autorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}