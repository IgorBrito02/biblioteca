package com.igor.biblioteca.dto;

import com.igor.biblioteca.model.Livro;
import lombok.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDto {

    private UUID id;
    private String titulo;
    private String descricao;
    private Integer qtdePaginas; 
    private String isbn;
    private String editora;
    private Integer anoPublicacao;
    private Set<AutorDto> autores;
    private Set<UUID> autoresIds; 

    public static LivroDto fromEntity(Livro livro) {
        return LivroDto.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .descricao(livro.getDescricao())
                .qtdePaginas(livro.getQtdePaginas())
                .isbn(livro.getIsbn())
                .editora(livro.getEditora())
                .anoPublicacao(livro.getAnoPublicacao())
                .autores(livro.getAutores() != null 
                        ? livro.getAutores().stream()
                                .map(AutorDto::fromEntity)
                                .collect(Collectors.toSet())
                        : Collections.emptySet())
                .build();
    }

    public Livro toEntity() {
        return Livro.builder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .qtdePaginas(this.qtdePaginas)
                .isbn(this.isbn)
                .editora(this.editora)
                .anoPublicacao(this.anoPublicacao)
                .build();
    }
}