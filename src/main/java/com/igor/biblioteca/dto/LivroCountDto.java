package com.igor.biblioteca.dto;

import com.igor.biblioteca.model.StatusLivro;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroCountDto {
    private UUID livroId;
    private String titulo; 
    private Long totalExemplares;
    private Long disponiveis;
    private Long emprestados;
    private Long reservados;
    private StatusLivro status;
    private Long quantidade;
}