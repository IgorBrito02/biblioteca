package com.igor.biblioteca.service;

import com.igor.biblioteca.dto.AutorDto;

import java.util.List;
import java.util.UUID;

public interface AutorService {

    AutorDto salvar(AutorDto autorDto);

    AutorDto atualizar(UUID id, AutorDto autorDto);

    void deletar(UUID id);

    AutorDto buscarPorId(UUID id);

    List<AutorDto> listarTodos();
}
