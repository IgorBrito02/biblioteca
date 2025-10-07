package com.igor.biblioteca.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.igor.biblioteca.dto.ExemplarLivroDto;
import com.igor.biblioteca.model.Livro;
import com.igor.biblioteca.model.ExemplarLivro;
import com.igor.biblioteca.repository.ExemplarLivroRepository;
import com.igor.biblioteca.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExemplarLivroServiceImpl implements ExemplarLivroService {


    private final ExemplarLivroRepository exemplarRepository;
    private final LivroRepository livroRepository;


    @Override
    public ExemplarLivroDto salvar(ExemplarLivroDto exemplarDto) {
        
        // Mapeia o DTO para Entidade ExemplarLivro
        ExemplarLivro exemplar = exemplarDto.toEntity();
        
        // Busca a entidade Livro pelo ID fornecido no DTO. Se não existir, lança 404.
        Livro livro = livroRepository.findById(exemplarDto.getLivroId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado com ID: " + exemplarDto.getLivroId()));
        
        // Associa a referência completa do objeto Livro ao Exemplar antes de salvar.
        exemplar.setLivro(livro);
        
        // Salva e persiste o ExemplarLivro
        exemplar = exemplarRepository.save(exemplar);
        
        // Retorna o DTO com o ID do livro corretamente populado
        return ExemplarLivroDto.fromEntity(exemplar);
    }


    @Override
    public ExemplarLivroDto atualizar(UUID id, ExemplarLivroDto exemplarDto) {
        ExemplarLivro exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));

        // Atualiza campos básicos
        exemplar.setCodigo(exemplarDto.getCodigo());
        exemplar.setStatus(exemplarDto.getStatus());
        exemplar.setStatusConservacao(exemplarDto.getStatusConservacao());
        
        // Se o livroId foi alterado/fornecido, atualiza a associação.
        if (exemplarDto.getLivroId() != null) {
            Livro livro = livroRepository.findById(exemplarDto.getLivroId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado com ID: " + exemplarDto.getLivroId()));
            exemplar.setLivro(livro);
        }

        return ExemplarLivroDto.fromEntity(exemplarRepository.save(exemplar));
    }


    @Override
    public void deletar(UUID id) {
        exemplarRepository.deleteById(id);
    }


    @Override
    public ExemplarLivroDto buscarPorId(UUID id) {
        return exemplarRepository.findById(id)
                .map(ExemplarLivroDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));
    }


    @Override
    public List<ExemplarLivroDto> listarTodos() {
        return exemplarRepository.findAll().stream()
                .map(ExemplarLivroDto::fromEntity)
                .collect(Collectors.toList());
    }
}