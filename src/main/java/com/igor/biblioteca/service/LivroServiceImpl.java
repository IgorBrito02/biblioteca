package com.igor.biblioteca.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.igor.biblioteca.dto.LivroCountDto;
import com.igor.biblioteca.dto.LivroDto;
import com.igor.biblioteca.model.Autor;
import com.igor.biblioteca.model.Livro;
import com.igor.biblioteca.model.StatusLivro;
import com.igor.biblioteca.repository.ExemplarLivroRepository;
import com.igor.biblioteca.repository.LivroRepository;
import com.igor.biblioteca.repository.AutorRepository; 

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final ExemplarLivroRepository exemplarLivroRepository;
    private final AutorRepository autorRepository; 

    //  CRUD 
    @Override
    @Transactional
    public LivroDto salvar(LivroDto livroDto) {
        // Converte o DTO para a entidade Livro.
        Livro livro = livroDto.toEntity();
        
        // Associa autores, se IDs forem fornecidos.
        if (livroDto.getAutoresIds() != null && !livroDto.getAutoresIds().isEmpty()) {
            
            // Busca as entidades Autor pelos IDs
            List<Autor> autores = autorRepository.findAllById(livroDto.getAutoresIds());
            
            // Limpa a coleção e associa os novos autores.
            livro.getAutores().clear();
            livro.getAutores().addAll(autores);
        }

        // Salva a entidade Livro (incluindo o relacionamento Many-to-Many).
        livro = livroRepository.save(livro);
        
        // Retorna o DTO com os dados persistidos e autores carregados.
        return LivroDto.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroDto atualizar(UUID id, LivroDto livroDto) {

        // Garante que o livro exista, senão lança 404.
        livroRepository.findById(id) 
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        Livro livroAtualizado = livroDto.toEntity(); 
        livroAtualizado.setId(id); 
        
        // Atualiza a associação de autores, se IDs forem fornecidos no DTO.
        if (livroDto.getAutoresIds() != null) {
            
            List<Autor> autores = autorRepository.findAllById(livroDto.getAutoresIds());
            
            // Sobrescreve a coleção de autores
            livroAtualizado.getAutores().clear(); 
            livroAtualizado.getAutores().addAll(autores);
        }

        livroAtualizado = livroRepository.save(livroAtualizado);
        return LivroDto.fromEntity(livroAtualizado);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        livroRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDto> buscarTodos() {
        return livroRepository.findAll().stream()
                .map(LivroDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LivroDto buscarPorId(UUID id) {
        return livroRepository.findById(id)
                .map(LivroDto::fromEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(LivroDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Implementa a contagem de exemplares, com opção de filtro por status.
     */
    @Override
    @Transactional(readOnly = true)
    public LivroCountDto contarExemplares(UUID livroId, StatusLivro status) {
        // Verifica se o livro existe para obter o título.
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        // Lógica para contagem por status específico (filtro).
        if (status != null) {
            long count = exemplarLivroRepository.countByLivroIdAndStatus(livroId, status.name());

            // Retorna DTO preenchendo apenas o campo filtrado.
            return LivroCountDto.builder()
                    .titulo(livro.getTitulo())
                    .totalExemplares(count)
                    .disponiveis(status == StatusLivro.DISPONIVEL ? count : 0L)
                    .emprestados(status == StatusLivro.EMPRESTADO ? count : 0L)
                    .reservados(status == StatusLivro.RESERVADO ? count : 0L)
                    .build();
        }

        // Lógica para contagem total (sem filtro).
        long total = exemplarLivroRepository.countByLivroId(livroId);

        // Executa consultas separadas para obter a contagem detalhada por status.
        long disponiveis = exemplarLivroRepository.countByLivroIdAndStatus(livroId, StatusLivro.DISPONIVEL.name());
        long emprestados = exemplarLivroRepository.countByLivroIdAndStatus(livroId, StatusLivro.EMPRESTADO.name());
        long reservados = exemplarLivroRepository.countByLivroIdAndStatus(livroId, StatusLivro.RESERVADO.name());

        // Retorna o DTO com a contagem total e detalhada.
        return LivroCountDto.builder()
                .titulo(livro.getTitulo())
                .totalExemplares(total)
                .disponiveis(disponiveis)
                .emprestados(emprestados)
                .reservados(reservados)
                .build();
    }
}