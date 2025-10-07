package com.igor.biblioteca.service;

import java.util.List;
import java.util.UUID;

import com.igor.biblioteca.dto.UsuarioDto;

public interface UsuarioService {
    UsuarioDto salvar(UsuarioDto dto);
    UsuarioDto atualizar(UUID id, UsuarioDto dto);
    void deletar(UUID id);
    UsuarioDto buscarPorId(UUID id);
    List<UsuarioDto> listarTodos();
}