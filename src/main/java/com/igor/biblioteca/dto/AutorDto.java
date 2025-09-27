package com.igor.biblioteca.dto;

import com.igor.biblioteca.model.Autor;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDto {

    private UUID id;
    private String nome;
    private String nacionalidade;

    public static AutorDto fromEntity(Autor autor) {
        return AutorDto.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .build();
    }

    public Autor toEntity() {
        return Autor.builder()
                .id(this.id)
                .nome(this.nome)
                .nacionalidade(this.nacionalidade)
                .build();
    }
}