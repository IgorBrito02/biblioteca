package com.igor.biblioteca.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.igor.biblioteca.dto.EmprestimoDto;
import com.igor.biblioteca.service.EmprestimoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
@Tag(name = "Empréstimo", description = "Endpoints para registro e controle de empréstimos.")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realizar Empréstimo", description = "Cria um novo registro de empréstimo. Requer 'usuarioId' e 'exemplaresIds'.")
    public EmprestimoDto salvar(@RequestBody @Valid EmprestimoDto dto) {
        return emprestimoService.salvar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Empréstimo", description = "Atualiza os dados de um empréstimo existente, geralmente para registrar a data de devolução.")
    public EmprestimoDto atualizar(@PathVariable UUID id, @RequestBody @Valid EmprestimoDto dto) {
        return emprestimoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Empréstimo", description = "Remove um registro de empréstimo.")
    public void deletar(@PathVariable UUID id) {
        emprestimoService.deletar(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Empréstimo por ID", description = "Retorna os detalhes de um empréstimo específico.")
    public EmprestimoDto buscarPorId(@PathVariable UUID id) {
        return emprestimoService.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar Empréstimos ou Buscar por Período", description = "Retorna a lista completa de empréstimos ou filtra por um período de 'dataHoraEmprestimo'.")
    public List<EmprestimoDto> listarTodos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(description = "Início do período de empréstimo (formato: yyyy-MM-ddTHH:mm:ss)") LocalDateTime inicio,
            
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(description = "Fim do período de empréstimo (formato: yyyy-MM-ddTHH:mm:ss)") LocalDateTime fim) {

        if (inicio != null && fim != null) {
            return emprestimoService.buscarPorPeriodo(inicio, fim);
        }
        
        return emprestimoService.listarTodos();
    }
}