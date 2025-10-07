package com.igor.biblioteca.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.igor.biblioteca.dto.AutorDto;
import com.igor.biblioteca.model.Autor;
import com.igor.biblioteca.repository.AutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {


    private final AutorRepository autorRepository;


    @Override
    public AutorDto salvar(AutorDto autorDto) {
        Autor autor = autorRepository.save(autorDto.toEntity());
        return AutorDto.fromEntity(autor);
    }


    @Override
    public AutorDto atualizar(UUID id, AutorDto autorDto) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        autor.setNome(autorDto.getNome());
        autor.setNacionalidade(autorDto.getNacionalidade()); 

        return AutorDto.fromEntity(autorRepository.save(autor));
    }


    @Override
    public void deletar(UUID id) {
        autorRepository.deleteById(id);
    }


    @Override
    public AutorDto buscarPorId(UUID id) {
        return autorRepository.findById(id)
                .map(AutorDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }


    @Override
    public List<AutorDto> listarTodos() {
        return autorRepository.findAll().stream()
                .map(AutorDto::fromEntity)
                .collect(Collectors.toList());
    }
}