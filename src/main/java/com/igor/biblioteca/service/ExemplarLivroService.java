package com.igor.biblioteca.service;

import com.igor.biblioteca.dto.ExemplarLivroDto;

import java.util.List;
import java.util.UUID;

public interface ExemplarLivroService {

    ExemplarLivroDto salvar(ExemplarLivroDto exemplarDto);

    ExemplarLivroDto atualizar(UUID id, ExemplarLivroDto exemplarDto);

    void deletar(UUID id);

    ExemplarLivroDto buscarPorId(UUID id);

    List<ExemplarLivroDto> listarTodos();
}
