package com.igor.biblioteca.dto;

import com.igor.biblioteca.model.ExemplarLivro;
import com.igor.biblioteca.model.StatusConservacao;
import com.igor.biblioteca.model.StatusLivro;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExemplarLivroDto {

    private UUID id;
    private String codigo;
    private StatusLivro status;
    private StatusConservacao statusConservacao;
    private UUID livroId; 

    public static ExemplarLivroDto fromEntity(ExemplarLivro exemplar) {
        return ExemplarLivroDto.builder()
                .id(exemplar.getId())
                .codigo(exemplar.getCodigo())
                .status(exemplar.getStatus())
                .statusConservacao(exemplar.getStatusConservacao())
                .livroId(exemplar.getLivro() != null ? exemplar.getLivro().getId() : null)
                .build();
    }

    public ExemplarLivro toEntity() {
        return ExemplarLivro.builder()
                .id(this.id)
                .codigo(this.codigo)
                .status(this.status)
                .statusConservacao(this.statusConservacao)
                .build();
    }
}