package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.ExemplarLivroDto;
import com.igor.biblioteca.service.ExemplarLivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exemplares")
@RequiredArgsConstructor
@Tag(name = "Exemplar", description = "Endpoints para gerenciamento de exemplares físicos de livros.")
public class ExemplarLivroController {

    private final ExemplarLivroService exemplarService;

    @GetMapping
    @Operation(summary = "Listar Todos os Exemplares", description = "Retorna todos os exemplares cadastrados na biblioteca.")
    public ResponseEntity<List<ExemplarLivroDto>> listar() {
        return ResponseEntity.ok(exemplarService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Exemplar por ID", description = "Retorna os detalhes de um exemplar específico pelo seu ID.")
    public ResponseEntity<ExemplarLivroDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(exemplarService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar Exemplar", description = "Cria um novo exemplar físico associado a um livro existente.")
    public ExemplarLivroDto salvar(@RequestBody @Valid ExemplarLivroDto dto) {
        return exemplarService.salvar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Exemplar", description = "Atualiza os dados de um exemplar existente (status, conservação, etc.).")
    public ResponseEntity<ExemplarLivroDto> atualizar(@PathVariable UUID id, @RequestBody @Valid ExemplarLivroDto dto) {
        return ResponseEntity.ok(exemplarService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Exemplar", description = "Remove um exemplar do sistema (pode falhar se o exemplar estiver emprestado).")
    public void deletar(@PathVariable UUID id) {
        exemplarService.deletar(id);
    }
}