package com.igor.biblioteca.repository;

import com.igor.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
