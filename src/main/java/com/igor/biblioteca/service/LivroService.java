package com.igor.biblioteca.service;

import com.igor.biblioteca.dto.LivroCountDto;
import com.igor.biblioteca.dto.LivroDto;
import com.igor.biblioteca.model.StatusLivro;

import java.util.List;
import java.util.UUID;

public interface LivroService {
    
    LivroDto salvar(LivroDto livroDto);
    LivroDto atualizar(UUID id, LivroDto livroDto);
    void deletar(UUID id);
    List<LivroDto> buscarTodos();
    LivroDto buscarPorId(UUID id);

    List<LivroDto> buscarPorTitulo(String titulo);

    LivroCountDto contarExemplares(UUID livroId, StatusLivro status);
}