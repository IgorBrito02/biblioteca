package com.igor.biblioteca.dto;

import com.igor.biblioteca.model.Emprestimo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EmprestimoDto {
    private UUID id;
    private LocalDateTime dataHoraEmprestimo;
    private LocalDateTime dataHoraDevolucao;
    private UUID usuarioId;
    private Set<UUID> exemplaresIds;

    public static EmprestimoDto fromEntity(Emprestimo emprestimo) {
        return EmprestimoDto.builder()
                .id(emprestimo.getId())
                .dataHoraEmprestimo(emprestimo.getDataHoraEmprestimo())
                .dataHoraDevolucao(emprestimo.getDataHoraDevolucao())
                .usuarioId(emprestimo.getUsuario().getId())
                .exemplaresIds(
                        emprestimo.getExemplares().stream()
                                .map(e -> e.getId())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}