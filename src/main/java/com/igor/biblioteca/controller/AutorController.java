package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.AutorDto;
import com.igor.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
@Tag(name = "Autor", description = "Endpoints para gerenciamento de autores e suas informações.")
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    @Operation(summary = "Listar Todos os Autores", description = "Retorna a lista completa de autores cadastrados.")
    public ResponseEntity<List<AutorDto>> listar() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Autor por ID", description = "Retorna os detalhes de um autor específico pelo seu ID.")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar Autor", description = "Cria um novo autor no sistema.")
    public AutorDto salvar(@RequestBody @Valid AutorDto dto) {
        return autorService.salvar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Autor", description = "Atualiza as informações de um autor existente.")
    public ResponseEntity<AutorDto> atualizar(@PathVariable UUID id, @RequestBody @Valid AutorDto dto) {
        return ResponseEntity.ok(autorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Autor", description = "Remove um autor do sistema (pode falhar se o autor estiver vinculado a livros).")
    public void deletar(@PathVariable UUID id) {
        autorService.deletar(id);
    }
}