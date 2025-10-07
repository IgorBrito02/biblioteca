package com.igor.biblioteca.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.igor.biblioteca.dto.EmprestimoDto;

public interface EmprestimoService {
    EmprestimoDto salvar(EmprestimoDto dto);
    EmprestimoDto atualizar(UUID id, EmprestimoDto dto);
    void deletar(UUID id);
    EmprestimoDto buscarPorId(UUID id);
    List<EmprestimoDto> listarTodos();
    
    // Busca por período de dataHoraEmprestimo
    List<EmprestimoDto> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
}
