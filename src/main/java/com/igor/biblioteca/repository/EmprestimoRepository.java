package com.igor.biblioteca.repository;

import com.igor.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {
    List<Emprestimo> findByDataHoraEmprestimoBetween(LocalDateTime inicio, LocalDateTime fim);
}