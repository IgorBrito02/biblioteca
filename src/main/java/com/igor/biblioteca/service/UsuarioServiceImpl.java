package com.igor.biblioteca.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.igor.biblioteca.dto.UsuarioDto;
import com.igor.biblioteca.model.UsuarioBiblioteca;
import com.igor.biblioteca.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto salvar(UsuarioDto dto) {
        // Converte DTO para Entidade
        UsuarioBiblioteca usuario = dto.toEntity();
        // Salva e retorna o DTO da entidade salva
        return UsuarioDto.fromEntity(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto atualizar(UUID id, UsuarioDto dto) {
        // Busca o usuário existente ou lança exceção 404
        UsuarioBiblioteca usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        // Atualiza os campos
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());

        // Salva e retorna
        return UsuarioDto.fromEntity(usuarioRepository.save(usuario));
    }

    @Override
    public void deletar(UUID id) {
        // A exclusão falhará automaticamente se o ID não existir (lança EmptyResultDataAccessException, que será tratada pelo GlobalExceptionHandler)
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDto buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDto::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }

    @Override
    public List<UsuarioDto> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDto::fromEntity)
                .collect(Collectors.toList());
    }
}