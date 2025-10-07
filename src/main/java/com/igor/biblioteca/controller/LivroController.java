package com.igor.biblioteca.controller;

import com.igor.biblioteca.dto.LivroCountDto;
import com.igor.biblioteca.dto.LivroDto;
import com.igor.biblioteca.model.StatusLivro;
import com.igor.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Tag(name = "Livro", description = "Endpoints para gerenciamento de livros e exemplares associados.")
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    @Operation(summary = "Listar Todos os Livros", description = "Retorna a lista completa de livros cadastrados na biblioteca.")
    public ResponseEntity<List<LivroDto>> listar() {
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Livro por ID", description = "Retorna os detalhes de um livro específico pelo seu ID.")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar Livro", description = "Cadastra um novo livro e associa um ou mais autores.")
    public LivroDto salvar(@RequestBody @Valid LivroDto dto) {
        return livroService.salvar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Livro", description = "Atualiza as informações de um livro existente.")
    public ResponseEntity<LivroDto> atualizar(@PathVariable UUID id, @RequestBody @Valid LivroDto dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Livro", description = "Remove um livro do sistema (pode falhar se houver exemplares vinculados).")
    public void deletar(@PathVariable UUID id) {
        livroService.deletar(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar Livro por Título", description = "Busca livros com base no título informado (parâmetro 'titulo').")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(
            @Parameter(description = "Título (ou parte do título) a ser buscado", required = true)
            @RequestParam String titulo) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }

    @Operation(
        summary = "Contar Exemplares de um Livro",
        description = "Retorna o total de exemplares de um livro, incluindo a quantidade de disponíveis, emprestados e reservados. "
                    + "Permite também filtrar por status opcional (DISPONIVEL, EMPRESTADO, RESERVADO)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = LivroCountDto.class))),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @GetMapping("/{id}/exemplares/count")
    public ResponseEntity<LivroCountDto> contarExemplares(
            @Parameter(description = "ID do livro a ser consultado", required = true)
            @PathVariable("id") UUID livroId,

            @Parameter(description = "Status dos exemplares a filtrar (opcional: DISPONIVEL, EMPRESTADO, RESERVADO)")
            @RequestParam(value = "status", required = false) StatusLivro status
    ) {
        LivroCountDto dto = livroService.contarExemplares(livroId, status);
        return ResponseEntity.ok(dto);
    }
}