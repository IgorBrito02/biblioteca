package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.LivroCountDto;
import com.igor.biblioteca.dto.LivroDto;
import com.igor.biblioteca.model.StatusLivro;
import com.igor.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDto>> listar() {
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroDto> salvar(@RequestBody LivroDto dto) {
        return ResponseEntity.ok(livroService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable UUID id, @RequestBody LivroDto dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Busca por título (utiliza query parameter: ?titulo=exemplo)
    @GetMapping("/search")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }

    // Contagem de exemplares: GET /livros/{id}/exemplares/count?status=DISPONIVEL (status é opcional)
    @GetMapping("/{id}/exemplares/count")
    public ResponseEntity<LivroCountDto> contarExemplares(
        @PathVariable UUID id,
        @RequestParam(required = false) StatusLivro status) {
        return ResponseEntity.ok(livroService.contarExemplares(id, status));
    }
}