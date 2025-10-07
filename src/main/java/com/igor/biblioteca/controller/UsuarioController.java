package com.igor.biblioteca.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.igor.biblioteca.dto.UsuarioDto;
import com.igor.biblioteca.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários da biblioteca.")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar Usuário", description = "Cria um novo usuário na biblioteca.")
    public UsuarioDto salvar(@RequestBody @Valid UsuarioDto dto) {
        return usuarioService.salvar(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Retorna os detalhes de um usuário específico.")
    public UsuarioDto buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar Todos os Usuários", description = "Retorna a lista completa de usuários cadastrados.")
    public List<UsuarioDto> listarTodos() {
        return usuarioService.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuário", description = "Atualiza os dados de um usuário existente.")
    public UsuarioDto atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioDto dto) {
        return usuarioService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar Usuário", description = "Remove um usuário permanentemente (pode falhar se houver empréstimos ativos).")
    public void deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
    }
}